package com.mygdx.game.anim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.anim.actions.MyScalAction;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MySourceManager;
import com.mygdx.game.mysources.MyStatusManager;


/**
 * @Classname Pot
 * @Description
 * @Date 2022/7/16 18:14
 * @Created by zkj
 */
public class Pot extends Actor {


    private Animation<TextureRegion> anim;
    private float time;

    private float width,height;
    Circle touchRegion;

    public boolean trash;

    private int score;

    Sound sound;

    public Pot(MyFirstGame myFirstGame,float x,float y,int type) {
        super();
        this.anim = myFirstGame.assetManager().getPopAnim();
//        System.out.println("Pot!!!");
        sound = myFirstGame.assetManager().get("music/press_sound.mp3");
        width = 20;
        height = 20;
        touchRegion = new Circle(x,y,width);
        score = 2;
        setBounds(x,y,width,height);
        addAction(new MyScalAction());

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                Pot.this.checkPress();
                Gdx.app.log("popIn:","touch{ x:"+x+" y:"+y+"} " + "self: { x:"+getX()+" y:"+getY()+" }");

//                System.out.println("clickde!!");
                super.clicked(event, x, y);
            }
        });
    }

    @Override
    public void act(float delta) {
        if (trash){
            return;
        }
        super.act(delta);
        time+=delta;

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
//        System.out.println("touchRegion.radius: "+touchRegion.radius);
        x = getWidth()/2-x;
        y = getHeight()/2-y;

        return x*x+y*y<getWidth()*getWidth()?this:null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        if (trash){
            return;
        }

        TextureRegion image =  this.anim.getKeyFrame(time);

        batch.setColor(getColor());

        // 这里的originx 和 originY 都是相对于当前actor的
        batch.draw(image,getX(),getY(),getWidth()/2,getHeight()/2,getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());

    }

    public void checkPress(){
        setVisible(false);
        MyStatusManager.TOTAL_SCORE+=this.score;
    }
}
