package com.techgames;

public class MarsRover extends SpaceVehicle {


   public MarsRover (String name, int x, int y, char direction) {
        super(name, x, y, direction);

   }

    @Override public void rotate(char leftRight){
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

    @Override public void move(int count, Plateau plateau){
        char curFace = this.face;
        int boundary[] = plateau.getRange();
        switch (curFace) {
            // if step in faced direction is within plateau, then move else stay
            case 'N':
                if ((this.position[1] + count) <= boundary[1]) {
                    this.position[1] += count;
                } else {
                    System.out.println("Cannot move further outside plateau!!");
                }
                break;
            case 'E':
                if ((this.position[0] + count) <= boundary[0]) {
                    this.position[0] += count;
                } else {
                System.out.println("Cannot move further outside plateau!!");
                }
                break;
            case 'S':
                if ((this.position[1] - count) >= 0) {
                    this.position[1] -= count;
                } else {
                    System.out.println("Cannot move further outside plateau!!");
                }
                break;
            case 'W':
                if ((this.position[0] - count) >= 0) {
                    this.position[0] -= count;
                } else {
                    System.out.println("Cannot move further outside plateau!!");
                }
                break;
        }
    }

    public String navigate(String input, Plateau plateau) {

        String newPosition;

       //read each char of command to rotate or move step
        //System.out.println(String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face);
        for (int i=0; i< input.length(); i++ ) {
            switch (input.charAt(i)){
                case 'L','R':
                    rotate(input.charAt(i));
                    break;
                case 'M':
                    move(1, plateau);
                    break;
            }
        }
        //get the new position and direction of Rover
        newPosition = String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face;

        return newPosition;
    }
}
