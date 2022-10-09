package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.gridpoint.GridPoint;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.impl.LevelImpl;
import ru.mipt.bit.platformer.obstacle.impl.ObstacleImpl;
import ru.mipt.bit.platformer.obstacle.impl.ObstacleRendererImpl;
import ru.mipt.bit.platformer.tank.impl.TankImpl;
import ru.mipt.bit.platformer.tank.impl.TankRendererImpl;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.obstacle.ObstacleRenderer;
import ru.mipt.bit.platformer.tank.Tank;
import ru.mipt.bit.platformer.tank.TankRenderer;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    TiledMap tiledMap;
    TiledMapTileLayer groundLayer;

    private Texture blueTankTexture;
    private ArrayList<TankRenderer> tankRenderers;
    private Texture greenTreeTexture;
    private ArrayList<ObstacleRenderer> obstacleRenderers;

    private Level level;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        tiledMap = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        groundLayer = getSingleLayer(tiledMap);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        blueTankTexture = new Texture("images/tank_blue.png");
        greenTreeTexture = new Texture("images/greenTree.png");

        tankRenderers = new ArrayList<>();
        obstacleRenderers = new ArrayList<>();
        level = extractLevelFromFile("src/main/resources/level.conf");
    }

    private LevelImpl extractLevelFromFile(String path) {
        ArrayList<Tank> tanks = new ArrayList<>();
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        int linesCnt = 0;
        try {
            linesCnt = (int)Files.lines(Paths.get(path)).count();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
            for (int i = 0; sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    var currPt = new GridPoint(j, linesCnt - i);
                    switch (line.charAt(j)) {
                        case 'T':
                            var newObstacle = new ObstacleImpl(currPt);
                            obstacles.add(newObstacle);
                            obstacleRenderers.add(new ObstacleRendererImpl(
                                    greenTreeTexture,
                                    groundLayer,
                                    newObstacle
                            ));
                            break;
                        case 'X':
                            var newTank = new TankImpl(
                                    currPt,
                                    Direction.RIGHT,
                                    GdxGameUtils::continueProgress,
                                    MathUtils::isEqual
                            );
                            tanks.add(newTank);
                            tankRenderers.add(new TankRendererImpl(
                                    blueTankTexture,
                                    tileMovement,
                                    newTank
                            ));
                            break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new LevelImpl(tanks, obstacles);
    }

    @Override
    public void render() {
        clearScreen();
        float deltaTime = Gdx.graphics.getDeltaTime();
        live(deltaTime);
        draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    private Direction getDesiredDirection() {
        Direction desiredDirection = Direction.NODIRECTION;
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            desiredDirection = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            desiredDirection = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            desiredDirection = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            desiredDirection = Direction.RIGHT;
        }
        return desiredDirection;
    }

    private void live(float deltaTime) {
        for (var tank : level.getTanks()) {
            var desiredDirection = getDesiredDirection();
            var desiredCoordinates = desiredDirection.getMovedPoint(tank.getDepartureCoordinates());
            if (!level.isOccupied(desiredCoordinates)) {
                tank.move(desiredDirection, deltaTime, MOVEMENT_SPEED);
            }
        }
    }

    private void draw() {
        levelRenderer.render();
        batch.begin();

        for (var tankRenderer : tankRenderers) {
            tankRenderer.draw(batch);
        }
        for (var obstacleRenderer : obstacleRenderers) {
            obstacleRenderer.draw(batch);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        greenTreeTexture.dispose();
        blueTankTexture.dispose();
        tiledMap.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
