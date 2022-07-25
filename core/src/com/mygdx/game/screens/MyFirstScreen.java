package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Service;
import com.mygdx.game.anim.MyAnimator;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MySourceManager;
import com.mygdx.game.mysources.MyStatusManager;
import com.mygdx.game.mysources.PopGameManager;
import com.mygdx.game.stages.MyStage;
import com.mygdx.game.util.threads.CleanPotRunnable;
import com.mygdx.game.util.threads.GeneratePotRunable;
import com.sun.tools.sjavac.Log;


/**
 * @Classname MyFirstScreen
 * @Description
 * @Date 2022/7/16 10:21
 * @Created by zkj
 */
public class MyFirstScreen implements Screen {
    MyFirstGame myGame;
    SpriteBatch batch;
    SpriteBatch menuBatch;
    OrthographicCamera camera;
    MyStage myStage ;

    ImageButton ib ;
    BitmapFont bf;
    PolygonBatch polygonBatch;

    PopGameManager popGameManager;
    Texture heat;
    Texture emptyHeart;
    Texture background;

    Texture zhezhao;

    Texture gameOverImg;
    ImageButton restart;

    MyStage gameOverStage;



    public MyFirstScreen(final MyFirstGame myGame) {
//        bf.dispose();
        this.myGame = myGame;

    }

    public void init(){
        gameOverStage = new MyStage();
        MyStatusManager.init();
        time = 0;
        restart= new ImageButton(myGame.assetManager().<Drawable>get("png/restart.png"),myGame.assetManager().<Drawable>get("png/restart01.png"));
        gameOverImg = myGame.assetManager().get("png/gameover.png",Texture.class);
        batch = new SpriteBatch();
        menuBatch = new SpriteBatch();
        myStage= new MyStage();
        myStage.getBatch().setColor(0.5f,0.5f,0.5f,0.5f);
        polygonBatch = new PolygonSpriteBatch();
        camera = new OrthographicCamera();
//        TextureRegionDrawable t = new TextureRegionDrawable(myGame.assetManager().<Drawable>get("btn/test_btn01.png"),myGame.assetManager().<Drawable>get("btn/test_btn02.png"));

//        batch.d
        ib = new ImageButton(myGame.assetManager().<Drawable>get("btn/test_btn01.png"),myGame.assetManager().<Drawable>get("btn/test_btn02.png"));
        ib.setBounds(20,20,30,30);
        ib.setColor(2,2,2,1);

        ib.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                myGame.convertToTestScreen();
//                dispose();
//                myGame.assetManager().dispose();
                Gdx.app.log("popIn:","touch{ x:"+x+" y:"+y+"} " + "self: { x:"+ib.getX()+" y:"+ib.getY()+" }");

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        ib.setVisible(true);
        restart.setVisible(true);
        restart.setBounds(100,50,200,100);
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MyFirstScreen.this.show();
            }
        });
        gameOverStage.addActor(restart);
        zhezhao = myGame.assetManager().get("png/zhezhao.png",Texture.class);
        popGameManager = new PopGameManager(myStage,myGame);
        camera.setToOrtho(false,320,240);

        myStage.getViewport().setCamera(camera);
        myStage.getViewport().setWorldWidth(640);
        myStage.getViewport().setWorldHeight(480);

        batch.setProjectionMatrix(myStage.getViewport().getCamera().combined);
    }

    @Override
    public void show() {
        this.init();

        heat = myGame.assetManager().<Texture>get("png/sys/heart.png");
        emptyHeart = myGame.assetManager().<Texture>get("png/sys/emptyheart.png");
        background = myGame.assetManager().get("png/background.jpeg");
//        Ray pickRay = myStage.getViewport().getCamera().getPickRay();
        myStage.addActor(ib);

        Service.submit(new GeneratePotRunable(myGame,myStage));
//        Service.submit(new CleanPotRunnable(myStage));

        Gdx.input.setInputProcessor(myStage);
        MyStatusManager.PAUSE = false;
//        myStage.getViewport().setCamera(camera);

        bf = myGame.assetManager().getFont();
        System.out.println("camera.position: "+camera.position);
        System.out.println("MyFirstScreen show!!");
    }
    float s = 0;
    float time = 0;

    @Override
    public void render(final float delta) {

        ScreenUtils.clear(Color.CLEAR);
        batch.setColor(1,1,1,1);

//        myStage.getViewport().getCamera().position.x++;
        myStage.getViewport().getCamera().update();

        batch.setProjectionMatrix(myStage.getViewport().getCamera().combined);

        batch.begin();
        batch.draw(background,0,0,myStage.getViewport().getWorldWidth(),myStage.getViewport().getWorldHeight());

        int y = 0;
        for(int x=0;x<MyStatusManager.TOTAL_HEAL;x++){
            if (y<MyStatusManager.CUR_HEAL){
                y++;
                batch.draw(heat,20+(30+5)*x,430,30,30);
            }else {
                batch.draw(emptyHeart,20+(30+5)*x,430,30,30);
            }
        }

        bf.getData().setScale(5);
        bf.draw(batch,String.valueOf(MyStatusManager.TOTAL_SCORE),50,50,500,0,true);

        if (!MyStatusManager.GAME_OVER){
            myStage.draw();
        }
        if (MyStatusManager.GAME_OVER){
            batch.setColor(1,1,1,0.5f);
            batch.draw(zhezhao,0,0,myStage.getViewport().getWorldWidth(),myStage.getViewport().getWorldHeight());
            menuBatch.begin();

            menuBatch.draw(gameOverImg,50,140,350,200);
            // 重新开始按钮
            gameOverStage.draw();
//            gameOverStage.act();
            menuBatch.end();
        }else {
            time+=delta;

            myStage.act(delta);

            if (MyStatusManager.CUR_HEAL<=0 || time>= 60){
                MyStatusManager.GAME_OVER = true;
                restart.setVisible(true);
                Gdx.input.setInputProcessor(gameOverStage);
            }
        }

        batch.end();
        popGameManager.cleanPop();



    }

    @Override
    public void resize(int width, int height) {
        myStage.getViewport().update(width,height,true);
        camera.setToOrtho(false,(float) width/(float)2,(float) height/(float)2);
        camera.update();
        gameOverStage.getViewport().update(width,height,true);
        System.out.println("MyFirstScreen resize!!"+String.format("{width:%s,height:%s}",width,height));
    }

    @Override
    public void pause() {
        MyStatusManager.PAUSE = true;
        System.out.println("MyFirstScreen pause!!");
    }

    @Override
    public void resume() {
        MyStatusManager.PAUSE = false;
        System.out.println("MyFirstScreen resume!!");

    }

    @Override
    public void hide() {
        System.out.println("MyFirstScreen hide!!");

    }

    @Override
    public void dispose() {
//        myStage.clear();
//        myStage.dispose();
//        this.myStage = null;
//        Service.clear();
        MyStatusManager.GAME_OVER = true;
        myGame.assetManager().clear();
        myStage.clear();
        System.out.println("MyFirstScreen dispose!!");
    }
}
