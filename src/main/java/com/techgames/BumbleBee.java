package com.techgames;

public class BumbleBee extends SpaceVehicle {

    public final int POWER=2;
    public BumbleBee (String name, int x, int y, char direction) {
        super(name, x, y, direction);

    }

    public void move(int count, RectPlateau plateau){
        DIRECTION curFace = this.face;
        int boundary[] = plateau.getRange();

        int[] targetPosition = {this.position[0], this.position[1] };

        switch (curFace) {
            case NORTH:
                targetPosition[1] += count*POWER;
                break;
            case EAST:
                targetPosition[0] += count*POWER;
                break;
            case SOUTH:
                targetPosition[1] -= count*POWER;
                break;
            case WEST:
                targetPosition[0] -= count*POWER;
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
            System.out.println("Cannot move - boundary or occupied or hazard!!Stay at position (" +
                    String.valueOf(position[0]) + "," + String.valueOf(position[1]) + ")");
        }
    }

    public String probeTarget (RectPlateau plateau){
        if ((this.position[0] == plateau.target[0])&& (this.position[1] == plateau.target[1])) {
            return ("found Ore at ("+ String.valueOf(position[0])+","+ String.valueOf(position[1])+ ") by BumbleBee") ;
        } else {
            return "Nil";
        }
    }


}
