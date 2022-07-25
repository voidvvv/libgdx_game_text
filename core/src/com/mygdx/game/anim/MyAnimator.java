package com.mygdx.game.anim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.TouchableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;


/**
 * @Classname MyAnimator
 * @Description
 * @Date 2022/7/16 11:17
 * @Created by zkj
 */
public class MyAnimator extends Actor {
    private Circle polygon;
    private Animation<Texture> animation;

    public MyAnimator(float duration,Array<Texture> array){
        animation = new Animation<Texture>(duration,array);

        Texture t = new Texture("badlogic.jpg");
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MyAnimator clicked x:"+x+" y:"+y);
                super.clicked(event, x, y);
            }
        });
        setX(0);
        setY(0);
        setWidth(200);
        setHeight(200);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Texture keyFrame = animation.getKeyFrame(Gdx.app.getGraphics().getDeltaTime());
        TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion());
        ScaleByAction scaleByAction = new ScaleByAction();
        scaleByAction.setAmount(0.5f);
        scaleByAction.act(0.5f);

        Image i;

        batch.draw(keyFrame,20,20);
    }

    /**
     * 这个是判断当前事件是否会触发当前actor的fire方法的
     *
     * @param x
     * @param y
     * @param touchable
     * @return
     */
    @Override
    public Actor hit(float x, float y, boolean touchable) {

        return this;
    }

    @Override
    public void act(float delta) {
        System.out.println("act!");
        super.act(delta);
    }

    @Override
    public boolean fire(Event event) {
        System.out.println("fire!"+event.getStage());
        return super.fire(event);
    }

    @Override
    public Color getColor() {
        Rectangle r = new Rectangle();

        return super.getColor();
    }
}
