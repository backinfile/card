package com.backinfile.card.server.humanOper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectOption;
import com.backinfile.card.gen.GameMessageHandler.SCSelectOption;
import com.backinfile.support.Utils;

public class SelectOptionOper extends HumanOper {
	private String tip;
	private List<String> options = new ArrayList<>();
	private int selectedIndex;

	public SelectOptionOper(String tip, List<String> options) {
		this.tip = tip;
		this.options.addAll(options);
	}

	public SelectOptionOper(String tip, String... options) {
		this.tip = tip;
		this.options.addAll(Arrays.asList(options));
	}

	@Override
	public void onHumanAttach() {
		SCSelectOption msg = new SCSelectOption();
		msg.addAllOptions(options);
		msg.setTip(tip);
		human.sendMessage(msg);
	}

	@Override
	public void onAIAttach() {
		selectedIndex = Utils.nextInt(options.size());
		setDone();
	}

	@Override
	public void onMessage(CSSelectOption data) {
		if (data.getIndex() >= 0 && data.getIndex() < options.size()) {
			selectedIndex = data.getIndex();
			setDone();
		}
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
}
