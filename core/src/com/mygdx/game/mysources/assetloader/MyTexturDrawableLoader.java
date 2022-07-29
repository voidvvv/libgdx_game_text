package com.mygdx.game.mysources.assetloader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;

/**
 * @Classname MyTexturDrawableLoader
 * @Description
 * @Date 2022/7/23 18:31
 * @Created by zkj
 */
public class MyTexturDrawableLoader extends AsynchronousAssetLoader<TextureRegionDrawable, AssetLoaderParameters<TextureRegionDrawable>> {
    /**
     * Constructor, sets the {@link FileHandleResolver} to use to resolve the file associated with the asset name.
     *
     * @param resolver
     */
    String f;
    //    TextureRegionDrawable trd;
    TextureData textureData;

    public MyTexturDrawableLoader(FileHandleResolver resolver) {
        super(resolver);
        Gdx.app.log("MyTexturDrawableLoader", "cons!!");

    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<TextureRegionDrawable> parameter) {
        textureData = null;
        textureData =TextureData.Factory.loadFromFile(file, null, false);


//        trd = new TextureRegionDrawable(re);
    }

    @Override
    public TextureRegionDrawable loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<TextureRegionDrawable> parameter) {

        if (textureData!=null){
            TextureRegion re = new TextureRegion(new Texture(textureData));
            return new TextureRegionDrawable(re);
        }else {
            return null;
        }
    }


    @Override
    public FileHandle resolve(String fileName) {
        return super.resolve(fileName);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters parameter) {
        return null;
    }
}
