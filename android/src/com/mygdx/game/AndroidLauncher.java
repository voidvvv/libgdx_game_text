package com.mygdx.game;

import android.content.Context;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidAudio;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamem.MyFirstGame;

public class AndroidLauncher extends AndroidApplication {
//	@Override
//	public AndroidAudio createAudio(Context context, AndroidApplicationConfiguration config) {
//		return new OboeAudio();
//	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new MyFirstGame(), config);
	}
}
