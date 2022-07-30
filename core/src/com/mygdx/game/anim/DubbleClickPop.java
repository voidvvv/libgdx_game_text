package com.mygdx.game.anim;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.anim.actions.MyScalAction;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MyAssetManager;
import com.mygdx.game.mysources.MyStatusManager;

/**
 * @Classname DubbleClickPop
 * @Description
 * @Date 2022/7/30 10:02
 * @Created by zkj
 */
public class DubbleClickPop extends MyTrashableActor {
    int score;

    float time;
    int remainClick;

    MyAssetManager assetManager;
    Sound sound;
    Animation<Sprite> [] anims;
//    Animation<TextureRegion> anim02;


    public DubbleClickPop(MyFirstGame game,float x,float y) {
        this(game,5,x,y);
    }
    public DubbleClickPop(MyFirstGame game,int i,float px,float py) {
        assetManager = game.assetManager();
        remainClick = i;
        score = 10;
        Texture iceCream = assetManager.get("png/ice_cream01.png", Texture.class);
        anims = (Animation<Sprite>[])new Animation[i];

        setBounds(px,py,20,20);
        for(int x=0;x<i;x++){
            Sprite s = new Sprite(iceCream);
            s.setPosition(getX(),getY());
            s.setAlpha((float) (x+1)/(float) i);
            s.setBounds(getX(),getY(),getWidth(),getHeight());
            anims[x] = new Animation<Sprite>(1f,s);
        }
        sound = assetManager.get("music/bite.mp3",Sound.class);
        addAction(new MyScalAction());
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);

                DubbleClickPop.this.checkClick();
            }
        });
    }

    private void checkClick() {

        if (isVisible()){

            sound.play();
            --remainClick;
            if (remainClick<=0){
                setVisible(false);
                MyStatusManager.TOTAL_SCORE+=this.score;
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time+=delta;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        Sprite keyFrame = anims[remainClick - 1].getKeyFrame(time);
        keyFrame.setScale(getScaleX(),getScaleY());
        keyFrame.setOriginCenter();
        keyFrame.setRotation(getRotation());
        keyFrame.draw(batch);
//        batch.draw(anims[remainClick-1].getKeyFrame(time),getX(),getY(),getWidth()/2,getHeight()/2,getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }
}
