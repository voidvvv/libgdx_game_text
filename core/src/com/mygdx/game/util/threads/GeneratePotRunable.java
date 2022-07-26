package com.mygdx.game.util.threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MyStatusManager;

/**
 * @Classname GeneratePotRunable
 * @Description
 * @Date 2022/7/16 18:58
 * @Created by zkj
 */
public class GeneratePotRunable implements Runnable {
    long timeLow = 500;
    long timeHigh = 1500;
    private Stage stage;
    private MyFirstGame myFirstGame;

    public GeneratePotRunable(MyFirstGame myFirstGame,Stage stage) {
        this.myFirstGame = myFirstGame;
        this.stage = stage;
    }

    @Override
    public void run() {

            while (true){
                if (MyStatusManager.GAME_OVER){
                    break;
                }
                if (MyStatusManager.PAUSE ){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Gdx.app.error("GeneratePotRunable",e.getMessage());
                    }
                    continue;
                }
                Gdx.app.log("GeneratePotRunable","Compare:"+String.format("{ worldWidth:%s,worldHeight:%s , screenWidth:%s,screenHeight:%s, graphicWidth:%s,graphicHeight:%s}",
                        stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight(),
                        stage.getViewport().getScreenWidth(),stage.getViewport().getScreenHeight(),
                        Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
                long random = MathUtils.random(500, 2500);
                float height = stage.getViewport().getWorldHeight();
                float width = stage.getViewport().getWorldWidth();
                final float x = MathUtils.random(20f, (float) (width-20));
                final float y = MathUtils.random(20f, (float) (height-20));
                Gdx.graphics.getHeight();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        stage.addActor(new Pot(myFirstGame,x,y,0));
                    }
                });
                try {
                    Thread.sleep(random);
                } catch (InterruptedException e) {
                    System.out.println("!!!!!!!!");
                }
            }
            Gdx.app.log("GeneratePotRunable","end");

    }
}
