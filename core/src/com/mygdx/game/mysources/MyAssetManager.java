package com.mygdx.game.mysources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.mysources.assetloader.MyFontLoader;
import com.mygdx.game.mysources.assetloader.MyTexturDrawableLoader;

/**
 * @Classname MyAssetManager
 * @Description
 * @Date 2022/7/23 17:30
 * @Created by zkj
 */
public class MyAssetManager extends MainAssetManager {
    private Animation<TextureRegion> popAnim;



    public MyAssetManager() {
        super();

    }

    public void setLoaders(){
        setLoader(BitmapFont.class,".ttf",new MyFontLoader(getFileHandleResolver()));
        setLoader(TextureRegionDrawable.class,new MyTexturDrawableLoader(getFileHandleResolver()));

    }

    @Override
    public synchronized boolean update() {
        return super.update();
    }

    public void loadAll(){

        load("badlogic.jpg",Texture.class);
        load("png/pop/ttq01.png", Texture.class);
        load("btn/test_btn01.png",TextureRegionDrawable.class);
        load("btn/test_btn02.png", TextureRegionDrawable.class);
        load("png/zhezhao.png",Texture.class);
        load("font/dgcr_01.ttf",BitmapFont.class);
        load("png/sys/heart.png",Texture.class);
        load("png/sys/emptyheart.png",Texture.class);
        load("png/background.jpeg",Texture.class);
        load("png/gameover.png",Texture.class);
        load("png/restart.png",TextureRegionDrawable.class);
        load("png/restart01.png",TextureRegionDrawable.class);
    }



    public Animation<TextureRegion> getPopAnim() {
        if (popAnim == null){
            synchronized (MyAssetManager.this){
                if (popAnim == null){
                    Texture texture = get("png/pop/ttq01.png", Texture.class);
                    if (texture == null){
                        return null;
                    }else {
                        TextureRegion tr = new TextureRegion(texture);


                        popAnim = new Animation<TextureRegion>(2f,tr);
                    }

                }
            }
        }
        return popAnim;
    }


    public BitmapFont getFont() {
        return get("font/dgcr_01.ttf",BitmapFont.class);
    }
}
