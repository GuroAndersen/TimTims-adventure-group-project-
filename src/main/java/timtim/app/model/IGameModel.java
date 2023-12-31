package timtim.app.model;

import java.util.List;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import timtim.app.model.objects.GameEntity;
import timtim.app.model.objects.Player;
import timtim.app.model.sound.SoundEffect;


/**
 * A class to represent the model of this game.
 * Contains a world, player, other objects, maps etc.
 *
 */
public interface IGameModel {

	///////////////////// PLAYER METHODS
	
	/**
	 * get the models Player object
	 * @return player
	 */
	public Player getPlayer();
	
	/**
     * Move the player left and or right.
     * If both left and right is pressed, nothing happens.
     * @param left
     * @param right
     */
    public void playerMove(boolean left, boolean right);

    /**
     * Make the player jump
     */
    public void playerJump();
	
	
	///////////////////// OTHER
	
	/**
	 * get the current maps renderer
	 * @return renderer
	 */
	public OrthogonalTiledMapRenderer getMapRenderer();
	
	/**
	 * Update the player, the world and its contents
	 * @param delta, the time since last update in seconds
	 */
	public void update(float delta);
	
	/**
	 * Get a list of all entities
	 * contained in this model. 
	 * This includes friends, enemies
	 * and player.
	 * @return list of entities
	 */
	public List<GameEntity> getEntities();

	/**
	 * Swap to the given level.
	 * @param level
	 */
	void swapLevel(String level);

	Iterable<String> getMapNames();

	void playSound(SoundEffect sound);
}
