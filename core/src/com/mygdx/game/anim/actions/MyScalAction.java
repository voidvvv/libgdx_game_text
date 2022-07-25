package com.mygdx.game.anim.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
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
    public void setActor(Actor actor) {
        if (actor == null){
            super.setActor(actor);
        }else if (actor instanceof Pot){
            super.setActor(actor);
        }else {
            throw new RuntimeException("err type!");
        }
    }

    @Override
    public boolean act(float delta) {
        return super.act(delta);
    }

    public void setActor(Pot actor) {
        super.setActor(actor);
    }

    @Override
    public Pot getActor() {
        return (Pot) super.getActor();
    }

    @Override
    protected void end() {
        super.end();
        System.out.println("end!");
        getActor().trash = true;
        MyStatusManager.CUR_HEAL--;
    }
}
