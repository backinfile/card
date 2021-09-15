package com.backinfile.support;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.backinfile.support.func.Function;

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
