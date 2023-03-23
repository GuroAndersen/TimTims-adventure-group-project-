package timtim.app.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import timtim.app.model.GameModel;

public class TileMapManager {

	private String currentMap;
	private GameModel model;
	private Map<String, GameMap> maps;

	public TileMapManager(GameModel model) {
		this.model = model;
		this.maps = new HashMap<String, GameMap>();
		this.currentMap = "level1";
		loadMaps();

	}

	private void loadMaps() {
		BufferedReader reader;
		try {
			InputStream is = TileMapManager.class.getResourceAsStream("/maps.txt");
			reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			String mapName;
			while ((mapName = reader.readLine()) != null) {
				maps.put(mapName, new GameMap(mapName, model.getPlayer()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Update the current map.
	 */
	public void update() {
		maps.get(currentMap).update();
	}

	/**
	 * Returns the current map.
	 * 
	 * @return
	 */
	public GameMap getCurrentMap() {
		return maps.get(currentMap);
	}
}
