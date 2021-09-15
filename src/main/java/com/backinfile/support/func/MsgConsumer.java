package com.backinfile.support.func;

import java.util.LinkedList;

public abstract class MsgConsumer<T> {
	private LinkedList<T> msgQueue = new LinkedList<T>();

	public final void putMsg(T msg) {
		msgQueue.addLast(msg);
	}

	protected T pollMsg() {
		return msgQueue.pollFirst();
	}

	protected boolean hasMsg() {
		return !msgQueue.isEmpty();
	}
}
