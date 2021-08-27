package com.backinfile.card.server.net;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.backinfile.support.Log;
import com.backinfile.support.Time2;
import com.backinfile.support.Utils;

import io.netty.channel.Channel;

public class ChannelConnection implements Delayed, Connection {
	public static final String TAG = ChannelConnection.class.getSimpleName();

	private Channel channel;
	private ConcurrentLinkedQueue<GameMessage> sendList = new ConcurrentLinkedQueue<GameMessage>();
	private ConcurrentLinkedQueue<byte[]> inputList = new ConcurrentLinkedQueue<>();
	private long time;
	public static final int HZ = 1;

	public String name;
	private long id;

	public ChannelConnection(long id, Channel channel) {
		this.channel = channel;
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void pulse() {
		time = Time2.getCurMillis();

		if (!isAlive())
			return;

		pulseSend();
		pulseInput();
	}

	@Override
	public GameMessage getGameMessage() {
		if (!inputList.isEmpty()) {
			var data = inputList.poll();
			var gameMessage = GameMessage.buildGameMessage(data, 0, data.length);
			return gameMessage;
		}
		return null;
	}

	@Override
	public void sendGameMessage(GameMessage gameMessage) {
		sendList.add(gameMessage);
	}

	private void pulseSend() {
		while (!sendList.isEmpty()) {
			GameMessage sendMsg = sendList.poll();
			channel.writeAndFlush(sendMsg.getBytes());
			Log.net.info("Connection {} send {}", this.toString(), sendMsg.toString());
		}
	}

	private void pulseInput() {
		while (!inputList.isEmpty()) {
			byte[] data = inputList.poll();
			if (data == null)
				break;
			// TODO
//			GameMessage gameMessage = GameMessage.buildGameMessage(data, 0, data.length);
//			if (gameMessage != null) {
//				Proxy.request(HumanGlobalService.PORT_NAME, RequestKey.HUMAN_GLOBAL_HANDLE_MSG,
//						new Params("id", getId(), "msg", gameMessage));
//			}
		}
	}

	/**
	 * 添加输入
	 * 
	 * @param data
	 */
	public void addInput(byte[] data) {
		inputList.add(data);
	}

	public boolean isAlive() {
		return channel.isActive();
	}

	@Override
	public String toString() {
		if (Utils.isNullOrEmpty(name)) {
			return String.valueOf(id);
		}
		return name + "(" + id + ")";
	}

	@Override
	public int compareTo(Delayed o) {
		ChannelConnection connection = (ChannelConnection) o;
		return Long.compare(time, connection.time);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return time + 1000 / HZ - Time2.getCurMillis();
	}

	public void close() {
		channel.close();
	}

}
