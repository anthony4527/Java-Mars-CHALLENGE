package com.techgames;

public class RoverProbe {
    SpaceVehicle rover;
    RectPlateau  plateau;
    public RoverProbe(SpaceVehicle rover, RectPlateau plateau){
        this.rover = rover;
        this.plateau = plateau;
    }

    public String probeTarget() {
        if ((this.rover.position[0] == this.plateau.target[0])&& (this.rover.position[1] == this.plateau.target[1])) {
            return ("found Ore ");
        } else {
            return "";
        }
    }

}
