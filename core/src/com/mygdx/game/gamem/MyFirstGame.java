package com.mygdx.game.gamem;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Service;
import com.mygdx.game.mysources.MainAssetManager;
import com.mygdx.game.mysources.MenuAssetManager;
import com.mygdx.game.mysources.MyAssetManager;
import com.mygdx.game.screens.*;

/**
 * @Classname MyFirstGame
 * @Description
 * @Date 2022/7/16 10:20
 * @Created by zkj
 */
public class MyFirstGame extends Game {
    MyFirstScreen myFirstScreen;
    TestScreen testScreen;
    MenuScreen menuScreen;

    LoadingScreen loadingScreen;
    MyAssetManager myAssetManager;
    MenuAssetManager menuAssetManager;



    MyTestScreen2 myTestScreen2;
    @Override
    public void create() {
        Service.init();
        menuScreen = new MenuScreen(this);
        menuAssetManager = new MenuAssetManager();
        myAssetManager = new MyAssetManager();
        loadingScreen = new LoadingScreen(this);



        myFirstScreen = new MyFirstScreen(this);
        testScreen = new TestScreen(this);
        setScreen(menuScreen,menuAssetManager());
//        myTestScreen2 = new MyTestScreen2();
//        setScreen(myTestScreen2);
    }


    public void setScreen(Screen screen, MainAssetManager mainAssetManager){
        loadingScreen.setDest(screen,mainAssetManager);
        super.setScreen(loadingScreen);
    }

    public MenuAssetManager menuAssetManager(){
        return this.menuAssetManager;
    }

    public MyAssetManager assetManager(){
        return this.myAssetManager;
    }

    public void convertToTestScreen() {
        setScreen(testScreen);
    }

    public void convertToFirstScreen() {
        setScreen(myFirstScreen,assetManager());
    }
}
