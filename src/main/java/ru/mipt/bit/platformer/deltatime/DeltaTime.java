package ru.mipt.bit.platformer.deltatime;

public class DeltaTime {
    float value;
    public static final DeltaTime INSTANCE = new DeltaTime();

    private DeltaTime() {
        value = 0.f;
    }

    public void setValue(float value) {
        this.value = value;
    }
    public float getValue() {
        return value;
    }
}
