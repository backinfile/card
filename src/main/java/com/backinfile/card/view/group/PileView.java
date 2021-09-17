package com.backinfile.card.view.group;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.actor.BoardButton;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;

// 管理一个牌堆
public class PileView extends BaseView {
	private PileButton pileButton; // 查看牌库按钮
	private LocalUIString uiString;
	private ECardPileType pileType;
	private PilePosition pilePosition;

//	public PileView(GameStage gameStage, float stageWidth, float stageHeight, ECardPileType pileType, boolean self) {
//		super(gameStage, Res.PILE_ICON_WIDTH, Res.PILE_ICON_HEIGHT);
//	}

	public static enum PilePosition {
		Self, Opponent,
	}

	public PileView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		pileButton = new PileButton();
		pileButton.setSize(Res.PILE_ICON_WIDTH, Res.PILE_ICON_HEIGHT);
		addActor(pileButton);
	}

	public void setPileType(ECardPileType type, PilePosition pilePosition) {
		this.uiString = LocalString.getUIString("Pile" + type.name());
		this.pilePosition = pilePosition;

		pileButton.setBackground(Res.getTexture(uiString.image));
	}

	public class PileButton extends BoardButton {
		@Override
		protected void onClick() {
			Log.game.info("open card pile view screen {},{}", pileType.name(), pilePosition.name());
		}
	}
}
