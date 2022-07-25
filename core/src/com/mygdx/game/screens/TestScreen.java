package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamem.MyFirstGame;
import com.mygdx.game.mysources.MySourceManager;
import com.mygdx.game.stages.MyStage;

/**
 * @Classname TestScreen
 * @Description
 * @Date 2022/7/17 10:46
 * @Created by zkj
 */
public class TestScreen implements Screen {
    private MyFirstGame game;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Polygon p1;
    private Polygon p2;


    private Vector2 point;
    private Polyline pl1;
    private Polyline pl2;

    ImageButton ib ;

    MyStage myStage;

    SpriteBatch batch;

    public TestScreen(MyFirstGame game) {
        this.game = game;


    }

    public void init(){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        shapeRenderer.setAutoShapeType(true);
        pl1 = new Polyline();
        p1 = new Polygon();
        p1.setVertices(new float[]{10,10,50,10,50,50,10,50});
        p1.setOrigin(35,35);
        Rectangle r = new Rectangle();

        point = new Vector2(20,20);

        ib = new ImageButton(game.assetManager().<Drawable>get("btn/test_btn01.png"),game.assetManager().<Drawable>get("btn/test_btn02.png"));
        ib.setBounds(50,50,30,30);
        ib.setColor(2,2,2,1);
        myStage = new MyStage();
        myStage.addActor(ib);
        ib.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TestScreen.this.game.convertToFirstScreen();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void show() {
        System.out.println("test screen show!");
        Gdx.input.setInputProcessor(myStage);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLUE);
        camera.update();
        if (p1.contains(point)){
            shapeRenderer.setColor(Color.RED);
        }else {
            shapeRenderer.setColor(Color.GREEN);
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        BitmapFont font = new BitmapFont();

        shapeRenderer.polygon(p1.getTransformedVertices());
        shapeRenderer.end();
        batch.begin();;
        ib.draw(batch,1);
        batch.end();

        p1.rotate(1);
//        System.out.println(String.format("x:%s,y:%s",p1.getX(),p1.getY()));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
