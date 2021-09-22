package com.backinfile.card.server.humanOper;

import com.backinfile.card.model.Human;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;

public abstract class AbstractHumanOper {
	public Human human;

	/**
	 * 操作初始化
	 */
	public void init() {
	}

	/**
	 * 操作结束
	 */
	public void dispose() {

	}

	/**
	 * 是否已经操作结束
	 */
	public abstract boolean isDone();

	/**
	 * 刚被附加到Human上时执行
	 */
	public abstract void onAttach();

	/**
	 * 从Human上移除时执行
	 */
	public abstract void onDetach();

	/**
	 * 处理来则客户端的消息
	 */
	public abstract boolean onMessage(DSyncBase msg);

	/**
	 * 刚刚被附加到AI上时执行
	 */
	public abstract void onAIAttach();

}
