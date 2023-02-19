package com.techgames;

public class MoveCommand {

    SpaceVehicle rover;
    RectPlateau plateau;
    public MoveCommand (SpaceVehicle rover, RectPlateau plateau){
        this.rover = rover;
        this.plateau = plateau;
    }
    public void execute(){
        DIRECTION curFace = this.rover.face;
        int boundary[] = this.plateau.getRange();

        int[] targetPosition = {this.rover.position[0], this.rover.position[1] };

        switch (curFace) {
            // get the target position by case of current facing direction
            case NORTH:
                targetPosition[1] += 1;
                break;
            case EAST:
                targetPosition[0] += 1;
                break;
            case SOUTH:
                targetPosition[1] -= 1;
                break;
            case WEST:
                targetPosition[0] -= 1;
                break;
        }
        // if target position is boundary or occupied, do not move
        if (this.plateau.isSafePosition(targetPosition)) {
            //remove in-use state of current plateau location
            plateau.clearInuse(this.rover.position[0],this.rover.position[1]);
            this.rover.position[0] = targetPosition[0];
            this.rover.position[1] = targetPosition[1];
            //set in-use state of location of new position
            this.plateau.setInuse(this.rover.position[0],this.rover.position[1]);
        } else {
            System.out.println("Cannot move - boundary or occupied or hazard!!Stay at position ("+
                    String.valueOf(this.rover.position[0])+","+String.valueOf(this.rover.position[1])+")");
        }
    }
}
