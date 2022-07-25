package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MenuAssetManager;

/**
 * @Classname MenuScreen
 * @Description
 * @Date 2022/7/24 19:45
 * @Created by zkj
 */
public class MenuScreen implements Screen {
    private MyFirstGame game;

    // draw
    SpriteBatch batch;
    ImageButton startButton;
    OrthographicCamera camera;
    Texture title;

    // stage
    Stage stage;

    public MenuScreen(MyFirstGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        MenuAssetManager menuAssetManager = game.menuAssetManager();
        title = menuAssetManager.titleTexture();
        stage.getViewport().setCamera(camera);
        startButton = new ImageButton(menuAssetManager.startButton(),menuAssetManager.startButton02());
        startButton.setWidth(120);
        startButton.setHeight(50);
        startButton.setPosition((camera.viewportWidth-startButton.getWidth())/2,(camera.viewportHeight-startButton.getHeight()-200-50-50)/2);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.convertToFirstScreen();
                MenuScreen.this.dispose();
            }
        });
        stage.addActor(startButton);
    }

    @Override
    public void render(float delta) {
        camera.update();
        ScreenUtils.clear(Color.BLUE);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.draw();
        batch.draw(title,(camera.viewportWidth-300)/2,(camera.viewportHeight-200-50),300,200);
        startButton.draw(batch,1);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
