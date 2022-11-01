package ru.mipt.bit.platformer.aiadapter.impl;

import org.awesome.ai.strategy.NotRecommendingAI;
import org.awesome.ai.state.GameState;
import ru.mipt.bit.platformer.aiadapter.AIAdapter;
import ru.mipt.bit.platformer.aiadapter.Action;
import ru.mipt.bit.platformer.aiadapter.Hint;
import ru.mipt.bit.platformer.direction.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.obstacle.Obstacle;
import ru.mipt.bit.platformer.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class AIAdapterImpl implements AIAdapter {
    private final NotRecommendingAI ai;
    private final Level level;

    public AIAdapterImpl(NotRecommendingAI ai, Level level) {
        this.ai = ai;
        this.level = level;
    }

    public ArrayList<Hint> recommendations() {
        var builder = new GameState.GameStateBuilder();
        var gs = builder
                .obstacles(requiredObstacles(level.getObstacles()))
                .bots(requiredBots(level.getBots()))
                .player(requiredPlayer(level.getPlayer()))
                .levelWidth(level.getWidth())
                .levelHeight(level.getHeight())
                .build();

        var recs = ai.recommend(gs);
        ArrayList<Hint> hints = new ArrayList<>();
        for (var rec : recs) {
            switch (rec.getAction()) {
                case Shoot -> hints.add(new Hint((Tank) rec.getActor().getSource(), Action.SHOOT));
                case MoveNorth -> hints.add(new Hint((Tank) rec.getActor().getSource(), Action.MOVE_NORTH));
                case MoveEast -> hints.add(new Hint((Tank) rec.getActor().getSource(), Action.MOVE_EAST));
                case MoveSouth -> hints.add(new Hint((Tank) rec.getActor().getSource(), Action.MOVE_SOUTH));
                case MoveWest -> hints.add(new Hint((Tank) rec.getActor().getSource(), Action.MOVE_WEST));
            }
        }

        return hints;
    }

    private List<org.awesome.ai.state.immovable.Obstacle> requiredObstacles(ArrayList<Obstacle> srcObstacles) {
        List<org.awesome.ai.state.immovable.Obstacle> dstObstacles
                = new ArrayList<org.awesome.ai.state.immovable.Obstacle>();

        for (var srcObstacle : srcObstacles) {
            var dstObstacle = new org.awesome.ai.state.immovable.Obstacle(
                    srcObstacle.getCoordinates().x,
                    srcObstacle.getCoordinates().y
            );
            dstObstacles.add(dstObstacle);
        }

        return dstObstacles;
    }

    private List<org.awesome.ai.state.movable.Bot> requiredBots(List<Tank> srcBots) {
        List<org.awesome.ai.state.movable.Bot> dstBots
                = new ArrayList<org.awesome.ai.state.movable.Bot>();

        for (var srcBot : srcBots) {
            var builder = new org.awesome.ai.state.movable.Bot.BotBuilder();
            var dstBot = builder
                    .source(srcBot)
                    .x(srcBot.getDepartureCoordinates().x)
                    .y(srcBot.getDepartureCoordinates().y)
                    .destX(srcBot.getDestinationCoordinates().x)
                    .destY(srcBot.getDestinationCoordinates().y)
                    .orientation(requiredOrientation(srcBot.getCurrentDirection()))
                    .build();
            dstBots.add(dstBot);
        }

        return dstBots;
    }

    private org.awesome.ai.state.movable.Player requiredPlayer(Tank srcPlayer) {
        var builder = new org.awesome.ai.state.movable.Player.PlayerBuilder();
        var dstPlayer = builder
                .source(srcPlayer)
                .x(srcPlayer.getDepartureCoordinates().x)
                .y(srcPlayer.getDepartureCoordinates().y)
                .destX(srcPlayer.getDestinationCoordinates().x)
                .destY(srcPlayer.getDestinationCoordinates().y)
                .orientation(requiredOrientation(srcPlayer.getCurrentDirection()))
                .build();

        return dstPlayer;
    }

    private org.awesome.ai.state.movable.Orientation requiredOrientation(Direction direction) {
        org.awesome.ai.state.movable.Orientation orientation = org.awesome.ai.state.movable.Orientation.E;
        switch (direction) {
            case RIGHT, NODIRECTION:
                orientation = org.awesome.ai.state.movable.Orientation.E;
            case UP:
                orientation = org.awesome.ai.state.movable.Orientation.N;
            case DOWN:
                orientation = org.awesome.ai.state.movable.Orientation.S;
            case LEFT:
                orientation = org.awesome.ai.state.movable.Orientation.W;
        }
        return orientation;
    }
}
