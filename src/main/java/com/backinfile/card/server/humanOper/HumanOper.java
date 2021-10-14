package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Human;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.func.Action0;

// 等待玩家执行一个操作
public abstract class HumanOper extends GameMessageHandler.DSyncListener implements IAlive {
	public Human human;
	private Action0 callback;
	private boolean isDone = false;
	private long aiTimer = 0;

	public void onAttach() {
		if (GameUtils.isAI(human)) {
			aiTimer = ConstGame.AI_WAIT_TIME + Time2.getCurMillis();
		} else {
			onHumanAttach();
		}
	}

	/**
	 * 刚被附加到Human上时执行
	 */
	public abstract void onHumanAttach();

	/**
	 * 刚刚被附加到AI上时执行
	 */
	public abstract void onAIAttach();

	public void pulse() {
		if (aiTimer > 0 && Time2.getCurMillis() > aiTimer) {
			aiTimer = -1;
			onAIAttach();
		}
	}

	/**
	 * 从Human上移除时执行
	 */
	public void onDetach() {
		if (callback != null) {
			callback.invoke();
		}
	}

	public void setDone() {
		this.isDone = true;
	}

	/**
	 * 是否已经操作结束
	 */
	public boolean isDone() {
		return isDone;
	}

	public final void setDetachCallback(Action0 callback) {
		this.callback = callback;
	}
}
