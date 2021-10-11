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
	private boolean opponent = false;
	private boolean cancel = false;
	private String cancelTip = "";

	private int selected;

	public SelectEmptySlotOper(List<Integer> toSelectFrom, ETargetSlotAimType aimType, boolean opponent, String tip,
			boolean cancel, String cancelTip) {
		this.toSelectFrom = toSelectFrom;
		this.aimType = aimType;
		this.tip = tip;
		this.opponent = opponent;
		this.cancel = cancel;
		this.cancelTip = cancelTip;
	}

	public SelectEmptySlotOper(List<Integer> toSelectFrom, ETargetSlotAimType aimType, boolean opponent, String tip,
			boolean cancel) {
		this(toSelectFrom, aimType, opponent, tip, cancel, "");
	}

	public SelectEmptySlotOper(List<Integer> toSelectFrom, ETargetSlotAimType aimType, boolean opponent, String tip) {
		this(toSelectFrom, aimType, opponent, tip, false, "");
	}

	@Override
	public void onHumanAttach() {
		SCSelectEmptySlot msg = new SCSelectEmptySlot();
		msg.setAimType(aimType);
		msg.setSelectFromList(toSelectFrom);
		msg.setTip(tip);
		msg.setOpponent(opponent);
		msg.setCancel(cancel);
		msg.setCancelTip(cancelTip);
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
