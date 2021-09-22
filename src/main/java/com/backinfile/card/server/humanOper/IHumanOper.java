package com.backinfile.card.server.humanOper;

import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;

public interface IHumanOper {
	/*
	 * 刚添加到human上时
	 */
	void onAttach();

	void onDetach();

	boolean isDone();

	boolean onMessage(String token, DSyncBase msg);
}