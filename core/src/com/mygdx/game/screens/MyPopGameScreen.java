package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Service;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MyStatusManager;
import com.mygdx.game.util.threads.GeneratePotRunable;
import org.w3c.dom.Text;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname MyPopGameScreen
 * @Description
 * @Date 2022/7/27 21:04
 * @Created by zkj
 */
public class MyPopGameScreen implements Screen {
    private ReentrantLock reentrantLock;

    private MyFirstGame myFirstGame;
    // draw 相关
    SpriteBatch sysBatch; // 血条，分数，等系统相关画笔
    OrthographicCamera sysCamera; // 系统相机，位置固定

    Texture heart; // 爱心
    Texture emptyHeart;// 空爱心
    BitmapFont scoreFont;// 分数字体
    Texture backGround;// 背景

    Label gameOverTip;
//    Image gameOverImg;
    ImageTextButton restartBtn;

    Texture zhezhao;
    Stage stage; // 主舞台，监听玩家点击事件
    Stage gameOverStage; //游戏结束舞台
    OrthographicCamera stageCamera; // 舞台相机



    public MyPopGameScreen(MyFirstGame myFirstGame) {
        this.myFirstGame = myFirstGame;
    }

    @Override
    public void show() {
        MyStatusManager.init();
        Skin skin = new Skin(Gdx.files.internal("skin/skin_punk/quantum-horizon-ui.json"));
        reentrantLock = new ReentrantLock();
        sysBatch = new SpriteBatch();
        sysCamera = new OrthographicCamera();
        gameOverTip = new Label("GAME OVER",skin,"title");
//        gameOverTip.
        sysCamera.setToOrtho(false,640,480);

        stage = new Stage();
        stageCamera = new OrthographicCamera();
        stageCamera.setToOrtho(false,640,480);
        stage.getViewport().setCamera(stageCamera);
        stage.getViewport().setWorldWidth(640);
        stage.getViewport().setWorldHeight(480);

        gameOverStage = new Stage();
        gameOverStage.getViewport().setCamera(sysCamera);

        heart = myFirstGame.assetManager().get("png/sys/heart.png");
        emptyHeart = myFirstGame.assetManager().get("png/sys/emptyheart.png");

        scoreFont = myFirstGame.assetManager().getFont();
        backGround = myFirstGame.assetManager().get("png/background.jpeg");

//        gameOverImg = new Image(myFirstGame.assetManager().get("png/gameover.png",Texture.class));
        restartBtn = new ImageTextButton("重新开始",skin,"default");
        restartBtn.setLabel(new Label("restart",skin.get("title", Label.LabelStyle.class)));
        restartBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                MyPopGameScreen.this.show();
            }
        });
//        restartBtn.setSkin();
        restartBtn.setBounds(50,50,300,100);
        float imageWidth = sysCamera.viewportWidth*0.75f;
        float imageHeight = sysCamera.viewportHeight*0.3f;
        gameOverTip.setBounds((sysCamera.viewportWidth*0.25f)/2f,sysCamera.viewportHeight*0.4f,imageWidth,imageHeight);
//        gameOverImg.setBounds((sysCamera.viewportWidth*0.25f)/2f,sysCamera.viewportHeight*0.4f,imageWidth,imageHeight);
        gameOverStage.addActor(gameOverTip);
        gameOverStage.addActor(restartBtn);
        zhezhao = myFirstGame.assetManager().get("png/zhezhao.png",Texture.class);
        Gdx.input.setInputProcessor(stage);
        Service.submit(new GeneratePotRunable(myFirstGame,stage,reentrantLock));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        if (MyStatusManager.GAME_OVER){
            renderGameOver(delta);
        }else {
            renderNormalGame(delta);
            stage.act();
            if (MyStatusManager.totalTime<=0){
                Gdx.input.setInputProcessor(gameOverStage);
                MyStatusManager.GAME_OVER = true;
            }
            MyStatusManager.totalTime-=delta;
            cleanTrash(stage);
        }


    }

    private void renderGameOver(float delta) {
        sysBatch.setColor(1,1,1,1);
        sysCamera.update();
        sysBatch.setProjectionMatrix(sysCamera.combined);
        sysBatch.begin();
        drawSystem();
        sysBatch.setColor(1,1,1,0.5f);
        stage.draw();
        sysBatch.draw(zhezhao,0,0,sysCamera.viewportWidth,sysCamera.viewportHeight);
        sysBatch.end();
        gameOverStage.draw();
    }

    private void renderNormalGame(float delta) {
        sysCamera.update();
        sysBatch.setProjectionMatrix(sysCamera.combined);
        sysBatch.begin();
        drawSystem();
        sysBatch.end();
        stage.draw();

    }

    private void cleanTrash(Stage stage) {
        Array<Actor> actors = stage.getActors();

        Array.ArrayIterator<Actor> iterator = actors.iterator();


        while (iterator!=null && iterator.hasNext()){
            Pot next = (Pot) iterator.next();
            if (next.trash){
                iterator.remove();
            }
        }

    }

    private void drawSystem() {
        sysBatch.draw(backGround,0,0,sysCamera.viewportWidth,sysCamera.viewportHeight);


        for(int x=0,y=0;x<MyStatusManager.TOTAL_HEAL;x++){
            if (y<MyStatusManager.CUR_HEAL){
                y++;
                sysBatch.draw(heart,20+((x)*40),420,30,30);
            }else {
                sysBatch.draw(emptyHeart,20+((x)*40),420,30,30);
            }
        }

        scoreFont.draw(sysBatch,String.valueOf(MyStatusManager.TOTAL_SCORE),sysCamera.viewportWidth*0.9f,sysCamera.viewportHeight*0.1f,0, Align.top|Align.right,false);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
        gameOverStage.getViewport().update(width,height,true);
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
