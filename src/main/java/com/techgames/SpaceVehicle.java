package com.techgames;

public abstract class SpaceVehicle {

    protected String name;
    protected int[] position = {0,0};

    protected DIRECTION face;

    public SpaceVehicle(String name, int x, int y, char face) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.face = DIRECTION.getDirection(face);
        //this.face = face;
    }
    public int[] getPosition() {
        return this.position;
    }

    public DIRECTION getFace() {
        return this.face;
    }

//    public abstract void move(int count, RectPlateau plateau);  //method to move on a plateau object

    public String navigate(String input, RectPlateau plateau) {
        String newPosition;
        String probeResult = "";

        LeftCommand leftCommand = new LeftCommand(this);
        RightCommand rightCommand = new RightCommand(this);
        MoveCommand moveCommand = new MoveCommand(this, plateau);
        RoverProbe roverProbe = new RoverProbe(this, plateau);

        //read each char of command to rotate or move step
        int i = 0;
        do {
            switch (input.charAt(i)){
                case 'L':
                    leftCommand.execute();
                    break;
                case 'R':
                    rightCommand.execute();
                    break;
                case 'M':
                    moveCommand.execute();
                    break;
                default: // if not above character, reject the command
                    System.out.println("invalid command!!");
                    return  "-1";
            }
            //detect if the plateau new position has special material and return "found message" if yes
            probeResult = roverProbe.probeTarget();
            //System.out.println ("probe is "+ probeResult);
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
//    public abstract String probeTarget(RectPlateau plateau);
}
