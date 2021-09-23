package com.backinfile.card.view.viewActions;

import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.IAction;

/**
 * 用于客户端控制动画显示和用户输入
 */
public abstract class ViewAction implements IAction {
	public GameStage gameStage;
	private boolean isDone = false;

	public ViewAction() {
	}

	@Override
	public void init() {
	}

	public void begin() {
	}

	@Override
	public void pulse() {
	}

	public final void setDone() {
		isDone = true;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
