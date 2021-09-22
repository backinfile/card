package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Human;
import com.backinfile.support.func.Action0;

// 等待玩家执行一个操作
public abstract class HumanOper extends GameMessageHandler.DSyncListener {
	public Human human;
	private Action0 callback;
	private boolean isDone = false;

	public void onAttach() {
		if (GameUtils.isAI(human)) {
			onAIAttach();
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
