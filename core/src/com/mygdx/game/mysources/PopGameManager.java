package com.mygdx.game.mysources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.stages.MyStage;
import com.mygdx.game.util.threads.GeneratePotRunable;

/**
 * @Classname PopGameManager
 * @Description
 * @Date 2022/7/17 19:37
 * @Created by zkj
 */
public class PopGameManager {
    public MyStage myStage;
    public MyFirstGame myFirstGame;

    private long popGameTime = 10; // 游戏时长默认1000毫秒

    public PopGameManager(MyStage myStage, MyFirstGame myFirstGame) {
        this.myStage = myStage;
        this.myFirstGame = myFirstGame;
    }

    public MyStage getMyStage() {
        return myStage;
    }

    public void setMyStage(MyStage myStage) {
        this.myStage = myStage;
    }

    public MyFirstGame getMyFirstGame() {
        return myFirstGame;
    }

    public void setMyFirstGame(MyFirstGame myFirstGame) {
        this.myFirstGame = myFirstGame;
    }

    public long getPopGameTime() {
        return popGameTime;
    }

    public void setPopGameTime(long popGameTime) {
        this.popGameTime = popGameTime;
    }

    public void cleanPop(){
        if (MyStatusManager.PAUSE){
            return;
        }
        Array<Actor> actors = myStage.getActors();
        Array.ArrayIterator<Actor> iterator = actors.iterator();

        while (iterator.hasNext()) {
            Actor next = iterator.next();
            if (next instanceof Pot) {
                Pot p = (Pot) next;
                if (p.trash) {
                    iterator.remove();
                }
            }
        }
    }

    public void generatePop(){
        if (MyStatusManager.PAUSE){
            return;
        }
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        float x = MathUtils.random(20f, (float) (width-20));
        float y = MathUtils.random(20f, (float) (height-20));
        myStage.addActor(new Pot(myFirstGame,x,y,0));

    }

    public Runnable generatePopAsyc(){
        return new GeneratePotRunable(myFirstGame,myStage);
    }
}
