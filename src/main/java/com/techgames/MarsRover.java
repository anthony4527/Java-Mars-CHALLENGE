package com.techgames;

public class MarsRover extends SpaceVehicle {


   public MarsRover (String name, int x, int y, char direction) {
        super(name, x, y, direction);
   }
   /*
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
   }*/

    @Override public void move(int count, Plateau plateau){
        char curFace = this.face;
        int boundary[] = plateau.getRange();

        int[] targetPosition = {this.position[0], this.position[1] };

        switch (curFace) {
            // if step in faced direction is within plateau, then move else stay
            // get the target position by case of current facing direction
            // if target position is boundary or occupied, do not move
            case 'N':
                targetPosition[1] += count;
                break;
            case 'E':
                targetPosition[0] += count;
                break;
            case 'S':
                targetPosition[1] -= count;
                break;
            case 'W':
                targetPosition[0] -= count;
                break;
        }
        if (plateau.isSafePosition(targetPosition)) {
            //remove in-use state of current plateau location
            plateau.clearInuse(this.position[0],this.position[1]);
            this.position[0] = targetPosition[0];
            this.position[1] = targetPosition[1];
            //set in-use state of location of new position
            plateau.setInuse(this.position[0],this.position[1]);
        } else {
            System.out.println("Cannot move - boundary or occupied or hazard!!Stay at position ("+
                    String.valueOf(position[0])+","+String.valueOf(position[1])+")");
        }
    }

    public String probeTarget (Plateau plateau){
       if ((this.position[0] == plateau.target[0])&& (this.position[1] == plateau.target[1])) {
           return ("found Ore at ("+ String.valueOf(position[0])+","+ String.valueOf(position[1])+ ")") ;
       } else {
           return "Nil";
        }
    }
    /*
    public boolean isSafePosition(int[] position, Plateau plateau){
       int tmp[] = {-1,-1};

       for (int i= 0; i< plateau.gridInuse.size(); i++) {
           tmp = plateau.gridInuse.get(i);
           if ((position[0]== tmp[0]) && (position[1] == tmp[1])){
               return false;
           }
       }
       tmp = plateau.getRange();
       //if positon is outside plateau range, return not safe
        if ((position[0] <0 ) || (position[0]> tmp[0]) || (position[1] <0 ) || (position[1]> tmp[1])) {
            return false;
        }
        return true;
    }*/

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
}
