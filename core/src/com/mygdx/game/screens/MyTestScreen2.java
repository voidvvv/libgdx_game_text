package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @Classname MyTestScreen2
 * @Description
 * @Date 2022/7/25 21:53
 * @Created by zkj
 */
public class MyTestScreen2 implements Screen {

    private SpriteBatch batch;
    private Texture texture;
    private OrthographicCamera camera;


    private Stage stage;
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,320,240);
        texture = new Texture("png/background.jpeg");

        stage.getViewport().setWorldWidth(640);
        stage.getViewport().setWorldHeight(480);
        stage.getViewport().setCamera(camera);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        batch.begin();
        batch.draw(texture,0,0, stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
