package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * @Classname MyTestScreen2
 * @Description
 * @Date 2022/7/25 21:53
 * @Created by zkj
 */
public class MyTestScreen2 implements Screen {

    private SpriteBatch batch;
    private Texture texture;
    private OrthographicCamera camera;

    private ShapeRenderer shapeRenderer;
    Circle circle;

    private Stage stage;
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        circle = new Circle(100,100,100);
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,200,200);
        texture = new Texture("badlogic.jpg");

        stage.getViewport().setWorldWidth(640);
        stage.getViewport().setWorldHeight(480);
        stage.getViewport().setCamera(camera);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            Vector3 v3 = camera.unproject(new Vector3(new Vector2(x, y), 0f));
            if (circle.contains(v3.x,v3.y)){
//                System.out.println("yes");
                shapeRenderer.setColor(Color.RED);
            }else {
//                System.out.println("no!");
                shapeRenderer.setColor(Color.BLUE);
            }
//            System.out.printf("input:{x:%s,y:%s}%n",x,y);

        }else {
            shapeRenderer.setColor(Color.WHITE);
        }
        ScreenUtils.clear(Color.CLEAR);
        stage.getViewport().getCamera().update();
        batch.setProjectionMatrix(stage.getViewport().getCamera().projection);
//        shapeRenderer
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(circle.x,circle.y,circle.radius);
        shapeRenderer.end();
//        camera.position.x--;
//        camera.position.y--;
//        batch.begin();
//        batch.draw(texture,0,0, stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight());
//        batch.end();
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
