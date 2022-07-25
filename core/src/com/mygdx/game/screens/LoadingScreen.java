package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MainAssetManager;

/**
 * @Classname LoadingScreen
 * @Description
 * @Date 2022/7/24 15:27
 * @Created by zkj
 */
public class LoadingScreen implements Screen {
    private MyFirstGame game;
    private Screen dest;
    private MainAssetManager assetManager;
    SpriteBatch batch;
    BitmapFont bf;
    public LoadingScreen(MyFirstGame game) {
        this.game = game;
    }

    public void setDest(Screen screen, MainAssetManager assetManager){
        this.dest = screen;
        this.assetManager = assetManager;
        this.assetManager.setLoaders();
        this.assetManager.loadAll();
    }

    @Override
    public void show() {
        bf = new BitmapFont();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.RED);
        batch.begin();
        System.out.println("==");
        if (assetManager!=null && !assetManager.update()){
            float progress = game.assetManager().getProgress();

            bf.draw(batch,"loading:"+String.valueOf((progress*100))+"%",200,200);
            batch.end();
        }else {
            bf.draw(batch,"success!",200,200);
            batch.end();

            this.game.setScreen(dest);
            this.dispose();
        }



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
        bf.dispose();
        bf = null;
        batch.dispose();
        batch = null;
        this.assetManager = null;
    }
}
