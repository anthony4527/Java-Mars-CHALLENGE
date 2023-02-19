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
        String newPosition, probeResult;

        LeftCommand leftCommand = new LeftCommand(this);
        RightCommand rightCommand = new RightCommand(this);
        MoveCommand moveCommand = new MoveCommand(this, plateau);
        //read each char of command to rotate or move step
        for (int i=0; i< input.length(); i++ ) {
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
            probeResult = probeTarget(plateau);
            if (!probeResult.equals("Nil")){
                return probeResult;
            }
        }
        //get the new position and direction of Rover
        newPosition = String.valueOf(position[0]) + " " + String.valueOf(position[1]) + " " + face.compass;
        return newPosition;
    }
    public abstract String probeTarget(RectPlateau plateau);
}
