package com.mygdx.game.mysources.assetloader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * @Classname MyFontLoader
 * @Description
 * @Date 2022/7/24 11:28
 * @Created by zkj
 */
public class MyFontLoader extends AsynchronousAssetLoader<BitmapFont, MyFontLoader.MyFontLoaderParameters> {

    public MyFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }


    FreeTypeFontGenerator.FreeTypeFontParameter myParameter ;
    boolean finish = true;

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, MyFontLoaderParameters parameter) {
        finish = false;
        myParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
         new FreeTypeFontGenerator.FreeTypeFontParameter();

        if (parameter == null){
            myParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS+"测试测试测试测试";
            myParameter.size = 150;
            myParameter.color = Color.WHITE;
        }else {
            myParameter.characters = parameter.charset;
            myParameter.size = parameter.size;
            myParameter.color = parameter.color;
        }
//        generator = null;
        finish = true;
    }

    @Override
    public BitmapFont loadSync(AssetManager manager, String fileName, FileHandle file, MyFontLoaderParameters parameter) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);

        if ( finish){
            BitmapFont bitmapFont = generator.generateFont(myParameter);
            myParameter = null;
            finish = false;
            generator.dispose();
            return bitmapFont;
        }else {
            return null;
        }
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, MyFontLoaderParameters parameter) {
        return null;
    }

    static public class MyFontLoaderParameters  extends AssetLoaderParameters<BitmapFont> {
        public String charset;
        public Color color;
        private int size;
    }
}
