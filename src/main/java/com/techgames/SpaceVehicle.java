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
    public void rotate(char leftRight) {
        char curFace = this.face;
        switch (curFace) {
            case 'N':
                if (leftRight == 'R'){
                    this.face = 'E';
                }else {
                    this.face = 'W';
                }
                break;
            case 'E':
                if (leftRight == 'R'){
                    this.face = 'S';
                }else {
                    this.face = 'N';
                }
                break;
            case 'S':
                if (leftRight == 'R'){
                    this.face = 'W';
                }else {
                    this.face = 'E';
                }
                break;
            case 'W':
                if (leftRight == 'R'){
                    this.face = 'N';
                }else {
                    this.face = 'S';
                }
                break;
        }
    }
    public abstract void move(int count, Plateau plateau);  //method to move on a plateau object

    public String navigate(String input, Plateau plateau) {
        String newPosition, probeResult;

        //read each char of command to rotate or move step
        for (int i=0; i< input.length(); i++ ) {
            switch (input.charAt(i)){
                case 'L','R':
                    rotate(input.charAt(i));
                    break;
                case 'M':
                    move(1, plateau);
                    break;
            }
            probeResult = probeTarget(plateau);
            if (!probeResult.equals("Nil")){
                return probeResult;
            }
        }
        //get the new position and direction of Rover
        newPosition = String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face;
        return newPosition;
    }
    public abstract String probeTarget(Plateau plateau);
}
