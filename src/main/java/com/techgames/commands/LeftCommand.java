package com.techgames;

public class LeftCommand implements ICommand {
    SpaceVehicle rover;
    public LeftCommand(SpaceVehicle rover){
        this.rover = rover;
    }

    public void execute(){
        this.rover.face = DIRECTION.rotateLeft(this.rover.face);
    }
}
