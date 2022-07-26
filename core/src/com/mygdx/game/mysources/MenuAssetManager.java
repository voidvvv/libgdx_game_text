package com.mygdx.game.mysources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.mysources.assetloader.MyTexturDrawableLoader;

/**
 * @Classname MenuAssetManager
 * @Description
 * @Date 2022/7/24 19:48
 * @Created by zkj
 */
public class MenuAssetManager extends MainAssetManager{

    public MenuAssetManager() {
        super();
    }

    @Override
    public void setLoaders() {
        super.setLoaders();
        setLoader(TextureRegionDrawable.class,new MyTexturDrawableLoader(getFileHandleResolver()));

    }

    @Override
    public void loadAll() {
        super.loadAll();
        load("png/title.png", Texture.class);
        load("png/start_button.png",TextureRegionDrawable.class);
        load("png/start_button02.png",TextureRegionDrawable.class);
    }

    public Texture titleTexture(){
        Texture texture = get("png/title.png", Texture.class, true);
        if (texture!=null){
            return texture;
        }
        return null;
    }

    public TextureRegionDrawable startButton(){
        TextureRegionDrawable texture = get("png/start_button.png", TextureRegionDrawable.class, true);
        if (texture!=null){
            return texture;
        }
        return null;
    }

    public TextureRegionDrawable startButton02(){
        TextureRegionDrawable texture = get("png/start_button02.png", TextureRegionDrawable.class, true);
        if (texture!=null){
            return texture;
        }
        return null;
    }
}
