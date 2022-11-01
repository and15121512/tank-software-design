package ru.mipt.bit.platformer.tank;

public interface ProgressUpdater {
    float updateProgress(float previousProgress, float deltaTime, float speed);
}
