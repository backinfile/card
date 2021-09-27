package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class OptionalAction extends WaitAction {
	private boolean cancel;
	private String tip = "";
	private String confirmTip = "";
	private String cancelTip = "";

	private Action confirmAction;
	private Action cancelAction;

	public OptionalAction(Human human, Action confirmAction, boolean cancel, String tip) {
		this(human, confirmAction, null, cancel, tip, "", "");
	}

	public OptionalAction(Human human, Action confirmAction, Action cancelAction, boolean cancel, String tip) {
		this(human, confirmAction, cancelAction, cancel, tip, "", "");
	}

	public OptionalAction(Human human, Action confirmAction, Action cancelAction, boolean cancel, String tip,
			String confirmTip, String cancelTip) {
		this.human = human;
		this.confirmAction = confirmAction;
		this.cancelAction = cancelAction;
		this.cancel = cancel;
		this.tip = tip;
		this.confirmTip = confirmTip;
		this.cancelTip = cancelTip;
	}

	@Override
	public void init() {
		var humanOper = new ConfirmOper(cancel, tip, confirmTip, cancelTip);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				if (confirmAction != null) {
					addFirst(confirmAction);
				}
			} else {
				if (cancelAction != null) {
					addFirst(cancelAction);
				}
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
