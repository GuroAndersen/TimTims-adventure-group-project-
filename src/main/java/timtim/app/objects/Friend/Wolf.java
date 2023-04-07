package timtim.app.objects.Friend;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import timtim.app.core.GameScreen;
import timtim.app.manager.Const;
import timtim.app.objects.Inventory.Item;
import timtim.app.objects.Inventory.ItemFactory;

public class Wolf extends Friend {
	GameScreen game;

	/**
	 * Creates a wolf that wants the given item.
	 * 
	 * @param item
	 */
	public Wolf(GameScreen game) {
		super(ItemFactory.newItem("ball"));
		this.game = game;
		//this.sprite = new Sprite(game.getAtlas().findRegion("skeleton"));
		//setupAnimation();
	}

	/**
	 * Testing constructor using the given item
	 * 
	 * @param item
	 */
	public Wolf(Item item) {
		super(item);
	}
	
	private void setupAnimation() {
		stateTimer = 0;
		// setup run animation
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for (int r = 0; r < 1; r++) {
			for (int c = 0; c < 4; c++) {
				frames.add(new TextureRegion(sprite.getTexture(), c * 32 * 2, r * 32, 32, 32));
			}
		}
		idleAnimation = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
	}

}
