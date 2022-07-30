package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
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
//        camera.setToOrtho(false);
        stage = new Stage();
        camera.setToOrtho(false,640,480);
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

            }
        });
        stage.getViewport().setWorldHeight(1);
        stage.getViewport().setWorldWidth(1);
        stage.addActor(startButton);

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Gdx.app.log("touch!",String.format("x:%s, y:%s",x,y));
            Vector3 unproject = camera.unproject(new Vector3(x, y, 0));
            Gdx.app.log("touch convert!",String.format("x:%s, y:%s",unproject.x,unproject.y));
        }

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
        float worldHeight = stage.getViewport().getWorldHeight();
        float worldWidth = stage.getViewport().getWorldWidth();
        Gdx.app.log("resize pre",String.format("worldHeight:%s  worldWidth:%s",worldHeight,worldWidth));
        stage.getViewport().update(width,height);

        camera.viewportHeight = 480;
        camera.viewportWidth = 640;
//        camera.translate(200,20);
//        stage.getViewport().setScreenWidth(10);
//        stage.getViewport().setScreenWidth(10);

        Gdx.app.log("resize after",String.format("worldHeight:%s  worldWidth:%s",stage.getViewport().getWorldHeight(),stage.getViewport().getWorldWidth()));

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
