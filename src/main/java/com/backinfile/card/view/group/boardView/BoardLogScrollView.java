package com.backinfile.card.view.group.boardView;

import java.util.LinkedList;

import com.backinfile.card.manager.Res;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Time2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class BoardLogScrollView extends BaseView {
	private TextArea boardLogTextArea;
	private ScrollPane scrollPane;
	private LinkedList<String> addLogQueue = new LinkedList<>();
	private long timer = 0;

	public BoardLogScrollView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		boardLogTextArea = new TextArea("---------",
				new TextFieldStyle(Res.DefaultFontSmallSamll, Color.WHITE, null, null, null));
		boardLogTextArea.setAlignment(Align.left);

		scrollPane = new ScrollPane(boardLogTextArea);
		scrollPane.setScrollingDisabled(true, false);

		Table table = new Table();
		table.setSize(width * 0.15f, height * 0.45f);
		table.setPosition(width * 0.86f, height * 0.486f);
		table.add(scrollPane).fill().expand();
		addActor(table);
	}

	public void addLog(String log) {
		addLogQueue.add(log);
	}

	public void addText(String text) {
		boardLogTextArea.appendText("\n" + text);
		boardLogTextArea.setPrefRows(boardLogTextArea.getLines());
		scrollPane.scrollTo(0, 0, 0, 0);
		scrollPane.updateVisualScroll();
		scrollPane.layout();
	}

	public void clearLog() {
		addLogQueue.clear();
		boardLogTextArea.setText("--------");
		boardLogTextArea.setPrefRows(boardLogTextArea.getLines());
		scrollPane.layout();
	}

	@Override
	public void act(float delta) {
		if (!addLogQueue.isEmpty() && Time2.getCurMillis() >= timer) {
			timer = Time2.getCurMillis() + 100;
			addText(addLogQueue.pollFirst());
		}
	}

}
