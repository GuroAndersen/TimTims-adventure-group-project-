package timtim.app.manager;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

import timtim.app.core.GameScreen;

public class TileMapManager {

	private TiledMap tiledMap;
	private GameScreen screen;
	
	public TileMapManager(GameScreen screen) {
		this.screen = screen;
	}
	
	public OrthogonalTiledMapRenderer mapSetup() {
		tiledMap = new TmxMapLoader().load("testMap.tmx");
		parseMapObjects(tiledMap.getLayers().get("objects").getObjects()); //gets objects in the "objects" layer of the tiledmap.
		return new OrthogonalTiledMapRenderer(tiledMap);
	}
	
	private void parseMapObjects(MapObjects objects) {
		for (MapObject o : objects) {
			
			if (o instanceof PolygonMapObject) {
				createStaticBody((PolygonMapObject) o);
			}
		}
	}
	
	private void createStaticBody(PolygonMapObject o) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyDef.BodyType.StaticBody;
		Body body = screen.getWorld().createBody(bodydef);
		Shape shape = createPolyShape(o);
		body.createFixture(shape, 1000);
		shape.dispose();
	}

	private Shape createPolyShape(PolygonMapObject o) {
		float[] vertices = o.getPolygon().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length/2];
		
		for (int i = 0; i < vertices.length / 2; i++) {
			Vector2 current = new Vector2(vertices[i*2] / Const.PPM, vertices[i*2 + 1] / Const.PPM); //Calculations to match BOX2D world
			worldVertices[i] = current;
		}
		
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		return shape;
	}
}
