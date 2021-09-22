package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;
import com.backinfile.card.view.group.CardView;

public class SelectEmptySlotViewAction extends ViewAction {
	private SCSelectEmptySlot data;
	private List<CardView> cardViews = new ArrayList<>();

	public SelectEmptySlotViewAction(SCSelectEmptySlot data) {
		this.data = data;
	}

	@Override
	public void begin() {
		for (var index : data.getSelectFromList()) {
			
		}
	}

}
