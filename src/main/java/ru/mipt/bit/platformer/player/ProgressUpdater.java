package ru.mipt.bit.platformer.player;

public interface ProgressUpdater {
    float updateProgress(float previousProgress, float deltaTime, float speed);
}
