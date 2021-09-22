package com.backinfile.card.server.humanOper;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectEmptySlot;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;

public class SelectEmptySlotOper extends HumanOper {
	private List<Integer> toSelectFrom = new ArrayList<>();
	private ETargetSlotAimType aimType;
	private String tip;
	private boolean opponent;

	private int selected;

	public SelectEmptySlotOper(List<Integer> toSelectFrom, ETargetSlotAimType aimType, boolean opponent, String tip) {
		this.toSelectFrom = toSelectFrom;
		this.aimType = aimType;
		this.tip = tip;
	}

	@Override
	public void onHumanAttach() {
		SCSelectEmptySlot msg = new SCSelectEmptySlot();
		msg.setAimType(aimType);
		msg.setSelectFromList(toSelectFrom);
		msg.setTip(tip);
		msg.setOpponent(opponent);
		human.sendMessage(msg);
	}

	@Override
	public void onMessage(CSSelectEmptySlot data) {
		selected = data.getSelected();
		setDone();
	}

	@Override
	public void onAIAttach() {
		selected = toSelectFrom.get(0);
		setDone();
	}

	public int getSelected() {
		return selected;
	}

}
