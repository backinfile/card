package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.func.Terminal;

//进行一局本地游戏
public class LocalGameClient extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	private LocalGameServer gameServer;
	private GameMessageHandler gameMessageHandler;
	private String localToken;

	public LocalGameClient(GameStage gameStage) {
		localToken = LocalData.instance().token;

		gameMessageHandler = new GameMessageHandler();
		gameMessageHandler.addListener(new LocalGameClientMessageHandler(this, gameStage));
		gameServer = new LocalGameServer();
		gameServer.addOutputListener(this::putMsg); // server产生的消息直接转入client
		gameServer.init();
		gameServer.gameStart();
	}

	public void sendMessage(DSyncBase msg) {
		var messageWarpper = new MessageWarpper();
		messageWarpper.token = localToken;
		messageWarpper.content = msg.toMessage();
		gameServer.putMsg(messageWarpper);
	}

	@Override
	public void pulse() {
		gameServer.pulse();

		// 处理消息
		while (hasMsg()) {
			var msg = pollMsg();
			// 只接受自己的
			if (localToken.equals(msg.token)) {
				// 转到client的handler去处理消息
				gameMessageHandler.onMessage(msg.content);
			}
		}
	}

}
