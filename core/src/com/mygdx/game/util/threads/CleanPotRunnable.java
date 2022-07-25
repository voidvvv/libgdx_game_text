package com.mygdx.game.util.threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.mysources.MyStatusManager;

/**
 * @Classname CleanPotRunnable
 * @Description
 * @Date 2022/7/16 19:10
 * @Created by zkj
 */
public class CleanPotRunnable implements Runnable {
    private final Stage stage;

    public CleanPotRunnable(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        while (true) {
            if (MyStatusManager.PAUSE){
                continue;
            }

//            Gdx.app.addLifecycleListener();

        }
    }
}
