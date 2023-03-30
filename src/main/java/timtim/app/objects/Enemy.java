package timtim.app.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends CombatEntity {

	private EnemyType type;

	public Enemy(EnemyType type) {
		this.type = type;
	}
	
	@Override
	public void update() {
		doRandomXMovement();
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	
}
