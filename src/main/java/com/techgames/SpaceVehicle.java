package com.techgames;

public abstract class SpaceVehicle {
    protected String name;
    protected int[] position = {0,0};

    public char face = ' ';

    public SpaceVehicle(String name, int x, int y, char face) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.face = face;
    }
    public int[] getPosition() {
        return this.position;
    }

    public char getFace() {
        return this.face;
    }
    // empty method for implementation
    public abstract void rotate(char indicator);
    public abstract void move(int count, Plateau plateau);  //method to move on a plateau object

    public abstract String probeTarget(Plateau plateau);
}
