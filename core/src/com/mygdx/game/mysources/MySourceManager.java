package com.mygdx.game.mysources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;

/**
 * @Classname MySourceManager
 * @Description
 * @Date 2022/7/16 18:32
 * @Created by zkj
 */
public class MySourceManager {
    public static long score;

    public static final float POT_WIDTH=75;
    public static final float POT_HEIGHT=75;
    public static Texture potImage;
    public static Array<TextureRegion> trs;
    public static Animation<TextureRegion> imageAnimation;



    BitmapFont b;

    public  void init(){



        potImage = new Texture("badlogic.jpg");
        trs = new Array<>();

        TextureRegion tr1 = new TextureRegion(MySourceManager.potImage,MySourceManager.potImage.getWidth(),MySourceManager.potImage.getHeight());

        potImage.dispose();

        MySourceManager.trs.add(tr1);

        imageAnimation = new Animation<TextureRegion>(3f,MySourceManager.trs);
    }
}
