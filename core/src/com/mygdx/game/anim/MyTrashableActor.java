package com.mygdx.game.anim;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @Classname MyTrashableActor
 * @Description
 * @Date 2022/7/30 11:24
 * @Created by zkj
 */
public class MyTrashableActor extends Actor {
    protected boolean trash;

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public void setTrash(){
        this.trash = true;
    }
}
