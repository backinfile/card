package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler.CSSelectConfirm;
import com.backinfile.card.gen.GameMessageHandler.SCSelectConfirm;
import com.backinfile.support.Utils;

public class ConfirmOper extends HumanOper {
	private boolean cancel;
	private String tip = "";
	private String confirmTip = "";
	private String cancelTip = "";

	private boolean result = true;

	public ConfirmOper(boolean cancel, String tip) {
		this.cancel = cancel;
		this.tip = tip;
	}

	public ConfirmOper(boolean cancel, String tip, String confirmTip, String cancelTip) {
		this.cancel = cancel;
		this.tip = tip;
		this.confirmTip = confirmTip;
		this.cancelTip = cancelTip;
	}

	@Override
	public void onHumanAttach() {
		SCSelectConfirm msg = new SCSelectConfirm();
		msg.setCancel(cancel);
		msg.setTip(tip);
		msg.setCancelTip(cancelTip);
		msg.setConfirmTip(confirmTip);
		human.sendMessage(msg);
	}

	@Override
	public void onMessage(CSSelectConfirm msg) {
		result = msg.getConfirm();
		setDone();
	}

	@Override
	public void onAIAttach() {
		result = Utils.nextInt(10) >= 3;
		setDone();
	}

	public boolean getResult() {
		return result;
	}

}
