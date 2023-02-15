package com.techgames;

public class MarsRover extends SpaceVehicle {

   public MarsRover (String name, int x, int y, char direction) {
        super(name, x, y, direction);
   }

    @Override public void move(int count, RectPlateau plateau){
        char curFace = this.face;
        int boundary[] = plateau.getRange();

        int[] targetPosition = {this.position[0], this.position[1] };

        switch (curFace) {
            // get the target position by case of current facing direction
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
        // if target position is boundary or occupied, do not move
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

    public String probeTarget (RectPlateau plateau){
       if ((this.position[0] == plateau.target[0])&& (this.position[1] == plateau.target[1])) {
           return ("found Ore at ("+ String.valueOf(position[0])+","+ String.valueOf(position[1])+ ")") ;
       } else {
           return "Nil";
        }
    }

}
