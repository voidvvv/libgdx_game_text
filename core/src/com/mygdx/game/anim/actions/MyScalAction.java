package com.mygdx.game.anim.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.mygdx.game.anim.DubbleClickPop;
import com.mygdx.game.anim.MyTrashableActor;
import com.mygdx.game.anim.Pot;
import com.mygdx.game.mysources.MyStatusManager;

/**
 * @Classname MyScalAction
 * @Description
 * @Date 2022/7/16 18:49
 * @Created by zkj
 */
public class MyScalAction extends ScaleToAction {

    public MyScalAction() {
        this(3f,2f);
    }

    public MyScalAction(float duration,float scale){
        setDuration(duration);
        setScale(scale);
    }


    @Override
    public boolean act(float delta) {
        return super.act(delta);
    }




    @Override
    protected void end() {
        super.end();
        System.out.println("end!");
        getActor().setVisible(false);
        MyStatusManager.CUR_HEAL--;
    }
}
