package com.techgames;

public class MarsRover extends SpaceVehicle {

   public MarsRover (String name, int x, int y, char direction) {
        super(name, x, y, direction);
    }

    @Override public void rotate(char leftRight){
            this.face = 'E';
        }

    @Override public void move(int count){
        this.position[0] = count;
        this.position[1] = count;
    }

    public String navigate(String input) {

        String newPosition;

       //read each char of command to rotate or move step
        for (int i=0; i< input.length(); i++ ) {
            switch (input.charAt(i)){
                case 'L','R':
                    rotate(input.charAt(i));
                    break;
                case 'M':
                    move(1);
                    break;
            }
        }
        //get the new position and direction of Rover
        newPosition = String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face;

        return newPosition;
    }
}
