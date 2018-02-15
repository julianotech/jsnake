package uk.jtech.game.jsnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by jtech on 15/02/2018.
 * Code from the course developing android game with libGDX by Daniel Ciolfi.
 */

public class GameScreen implements Screen {

    private Game game;

    private Viewport viewport;
    private SpriteBatch batch;

    private Texture bodyTexture;
    private Texture bgTexture;
    private Texture pointTexture;

    private boolean[][] body;
    private Array<Vector2> parts;

    private int direction; //1=up, 2=right, 3=down, 4=left

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new FitViewport( 100, 100 );
        viewport.apply();

        genTexture();

        init();

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix( viewport.getCamera().combined );

        Gdx.gl.glClearColor( 0.29f, 0.894f, 0.373f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();

        batch.draw( bgTexture, 0, 0, 100, 100 );

        for (Vector2 part:parts){
            batch.draw( bodyTexture, part.x*5 , part.y*5, 5, 5);
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update( width, height, true );
    }

    private void init() {
        body = new boolean[20][20];
        parts = new Array<Vector2>();

        parts.add( new Vector2( 6, 5 ) );
        body[6][5] = true;

        parts.add( new Vector2( 5, 5 ) );
        body[5][5] = true;

        direction = 2;
    }

    private void genTexture() {
        Pixmap pixmap = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap.setColor( 1f, 1f, 1f, 1f );
        pixmap.fillRectangle( 0, 0, 64, 64 );
        bodyTexture = new Texture( pixmap );
        pixmap.dispose();

        Pixmap pixmap2 = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap2.setColor( 0.29f, 0.784f, 0.373f, 0.5f );
        pixmap2.fillRectangle( 0, 0, 64, 64 );
        bgTexture = new Texture( pixmap2 );
        pixmap2.dispose();

        Pixmap pixmap3 = new Pixmap( 64, 64, Pixmap.Format.RGB888 );
        pixmap3.setColor( 0.29f, 1f, 1f, 1f );
        pixmap3.fillCircle( 32, 32, 32 );
        pointTexture = new Texture( pixmap3 );
        pixmap3.dispose();
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
