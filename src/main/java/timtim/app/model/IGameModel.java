package timtim.app.model;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

import timtim.app.objects.Timtim;


/**
 * A class to represent the model of this game.
 * Contains a world, player, other objects, maps etc.
 * @author Hilde Jordal
 *
 */
public interface IGameModel {

	/**
	 * get the models Player object
	 * @return player
	 */
	public Timtim getPlayer();
	
	/**
	 * get the current maps renderer
	 * @return renderer
	 */
	public OrthogonalTiledMapRenderer getMapRenderer();
	
	/**
	 * Update the player, the world and its contents
	 */
	public void update();

	/**
	 * Get the world of the current game map
	 * @return world
	 */
	public World getCurrentWorld();
}
