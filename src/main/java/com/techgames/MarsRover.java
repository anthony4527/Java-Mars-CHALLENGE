package com.techgames;

import java.util.HashMap;
import java.util.Map;

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

    @Override public void move(int count){
        char curFace = this.face;
        switch (curFace) {
            case 'N':
                this.position[1] += count;
                break;
            case 'E':
                this.position[0] += count;
                break;
            case 'S':
                this.position[1] -= count;
                break;
            case 'W':
                this.position[0] -= count;
                break;
        }
    }

    public String navigate(String input) {

        String newPosition;

       //read each char of command to rotate or move step
        System.out.println(String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face);
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
