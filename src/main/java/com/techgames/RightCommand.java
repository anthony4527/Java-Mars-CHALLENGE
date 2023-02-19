package com.techgames;

public class RightCommand {

    SpaceVehicle rover;
    public RightCommand(SpaceVehicle rover){
        this.rover = rover;
    }
    public void execute(){
        this.rover.face = DIRECTION.rotateRight(this.rover.face);
    }
}
