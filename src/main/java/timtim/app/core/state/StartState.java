package timtim.app.core.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import timtim.app.core.AccessibleGame;
import timtim.app.core.StateHandler;

import java.util.*;

public class StartState implements StateHandler {

    SpriteBatch batch;
    BitmapFont font;
    private final AccessibleGame game;

    private List<String> mapList;
    private List<Boolean> mapSelection;
    private final int selectionSpace = 50;


    public StartState (AccessibleGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        setupMapSelection();
    }

    public void setupMapSelection() {
        this.mapSelection = new ArrayList<>();
        this.mapList = new ArrayList<>();
        boolean selected = true;
        for (String map : game.getModel().getMapNames()) {
            mapSelection.add(selected);
            mapList.add(map);
            mapList.sort(Comparator.naturalOrder());
            if (selected) selected = false;
        }

    }

    @Override
    public void render(float delta) {
        // render game in start state
        handleInput();

        batch.begin();

        // Load the image
        Texture imgage = new Texture(Gdx.files.internal("timtimArt.png"));
        Sprite imgSprite = new Sprite(imgage);

        // Calculate the center position of the screen
        float centerXtext = game.getCamera().viewportWidth / 2f;
        float centerYtext = game.getCamera().viewportHeight / 2f ;
        float imageX = game.getCamera().viewportWidth / 5f;
        float imageY = game.getCamera().viewportHeight / 2f;

        // Set the position of the image to be centered on the screen
        imgSprite.setPosition(imageX - imgSprite.getWidth() / 2f -20,
                imageY - imgSprite.getHeight() / 2f );

        // Set the scale of the image
        imgSprite.setScale(0.2f); // Scale the image by half
        
        //clears the screen 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the image and the text

        imgSprite.draw(batch);
        // Change the color of the level selection text based on which level is currently selected
        drawMapSelection(centerXtext, centerYtext);

        font.setColor(Color.WHITE);
        font.draw(batch, "Press ENTER to start TimTim's adventure!", centerXtext + 35, centerYtext + 50, 0, Align.center, false);
        font.draw(batch, "Press 'H' to find out how to play Timtim's adeventure!", centerXtext + 55, centerYtext, 0, Align.center, false);

        batch.end();
    }

    private void drawMapSelection(float centerX, float centerY) {
        for (int i = 0; i < mapSelection.size(); i++) {
            boolean selected = mapSelection.get(i);
            String map = mapList.get(i);
            if (selected) font.setColor(Color.RED);
            else font.setColor(Color.WHITE);
            font.draw(batch, map, centerX + 35, centerY - (i+1) * selectionSpace, 0, Align.center, false);
        }
    }


    @Override
    public State getState() {
        return State.START;
    }

    public void handleInput() {

        // Exit
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { // Closes game if escape is pressed
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
        	switchMap();
            startGame();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
            getInstructions();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            cycle(mapSelection, 1);
            switchMap();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            cycle(mapSelection, -1);
            switchMap();
        }
    }

    public void switchMap() {
        for (int i = 0; i < mapSelection.size(); i++) {
            if (mapSelection.get(i))
                game.getModel().swapLevel(mapList.get(i));
        }
    }

    public static void cycle(List<Boolean> list, int direction) {
        int index = list.indexOf(true);
        list.set(index, false);
        index = (index + direction + list.size()) % list.size();
        list.set(index, true);
        for (int i = (index + direction + list.size()) % list.size(); i != index; i = (i + direction + list.size()) % list.size()) {
            list.set(i, false);
        }
    }



    public void startGame(){
        game.switchState(State.PLAY);

    }

    public void getInstructions(){
        game.switchState(State.INSTRUCTIONS);

    }
}


