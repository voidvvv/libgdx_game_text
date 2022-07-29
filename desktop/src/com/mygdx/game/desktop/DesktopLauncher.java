package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.controllers.desktop.JamepadControllerManager;
import com.badlogic.gdx.controllers.desktop.support.CompositeControllerListener;
import com.badlogic.gdx.controllers.desktop.support.JamepadController;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamem.MyFirstGame;
import com.studiohartman.jamepad.ControllerIndex;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		TexturePacker.Settings settings = new TexturePacker.Settings();
//		settings.maxWidth = 2048;
//		settings.maxHeight = 2048;
//		settings.edgePadding = false;
//		TexturePacker.process(settings,"D:\\resource\\png","D:\\resource\\png2","test");
		new LwjglApplication(new MyFirstGame(), config);
		

	}
}
