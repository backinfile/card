package com.backinfile.card.view.stage;

import com.backinfile.card.server.GameClient;
import com.backinfile.card.server.GameClientStandalone;
import com.backinfile.card.view.group.CardGroupView;
import com.backinfile.card.view.group.ShowCardView;
import com.backinfile.card.view.group.UIView;
import com.backinfile.card.view.group.UseCardSkillView;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

	private CardGroupView cardGroupView;
	private UIView uiView;
	private ShowCardView showCardView;
	private UseCardSkillView useCardSkillView;

	private GameClient gameClient;

	public GameStage(Viewport viewport) {
		super(viewport);

		initLogic();
		initView();
	}

	private void initLogic() {
		gameClient = new GameClientStandalone();
	}

	private void initView() {

		cardGroupView = new CardGroupView(getWidth(), getHeight());

		uiView = new UIView(getWidth(), getHeight());

		showCardView = new ShowCardView(getWidth(), getHeight());
		useCardSkillView = new UseCardSkillView(getWidth(), getHeight());

		addActor(cardGroupView);
		addActor(uiView);
		addActor(showCardView);
		addActor(useCardSkillView);

//		useCardSkillView.show(LocalString.getCardString("attack").frontImages[0]);
	}

	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		gameClient.pulse();
	}

}
