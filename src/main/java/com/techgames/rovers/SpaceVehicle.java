package com.techgames;

import java.util.HashMap;

public abstract class SpaceVehicle {
    protected String name;
    protected int[] position = {0,0};

    protected DIRECTION face;

    public SpaceVehicle(String name, int x, int y, char face) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.face = DIRECTION.getDirection(face);
    }
    public int[] getPosition() {
        return this.position;
    }

    public DIRECTION getFace() {
        return this.face;
    }

    public String navigate(String input, RectPlateau plateau) {
        String newPosition;
        String probeResult = "";

        //get list oc commands to check the ones for executing
        CommandList commandList = new CommandList(this, plateau);
        RoverProbe roverProbe = new RoverProbe(this, plateau);

        int i = 0;
        do {
            //read each char of command to rotate or move step, and execute
            ICommand command = commandList.commands.get(COMMAND.getCommand(input.charAt(i)));
            if (command == null){
                return "-1";
            } else {
                command.execute();
            }
            //detect if the plateau new position has special material and return "found message" if yes
            probeResult = roverProbe.probeTarget();
            if (!probeResult.equals("")) {
                break;
            }
            i++;
        } while (i< input.length());

        //get the new position and direction of Rover
        newPosition = String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face.compass;
        if (!probeResult.equals(null)) {
            return (probeResult + newPosition) ;
        } else {
            return newPosition;
        }
    }

}
