package com.techgames;

public abstract class SpaceVehicle {
    public String name;
    public int[] position = {0,0};

    public char face = ' ';

    public SpaceVehicle(String name, int x, int y, char face) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.face = face;
    }
    // empty method for implementation
    public abstract void rotate(char indicator);
    public abstract void move(int count);

}
