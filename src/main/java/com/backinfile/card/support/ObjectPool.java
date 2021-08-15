package com.backinfile.card.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ObjectPool<T> {
	private LinkedList<T> objs = new LinkedList<>();
	private Function<T> objCreator;
	private List<T> maintainedObjs = new ArrayList<>();

	public ObjectPool(Function<T> objCreator) {
		this.objCreator = objCreator;
	}

	public int getNumCount() {
		return objs.size();
	}

	public T apply() {
		T obj;
		if (objs.isEmpty()) {
			obj = objCreator.invoke();
			maintainedObjs.add(obj);
		} else {
			obj = objs.pollLast();
		}
		return obj;
	}

	public void putBack(T t) {
		objs.add(t);
	}

	public List<T> getMaintainedObjs() {
		return maintainedObjs;
	}

}
