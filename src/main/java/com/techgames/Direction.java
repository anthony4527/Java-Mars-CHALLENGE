package com.techgames;

enum DIRECTION{
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    public char compass;
    private DIRECTION(char compass) {
        this.compass = compass;
    }

    public static DIRECTION getDirection(char face){
        for (DIRECTION d: DIRECTION.values()){
            if (face == d.compass){
                return d;
            }
        }
        return null;
    }
    public static DIRECTION rotateLeft(DIRECTION curFace){
        return switch (curFace) {
                    case NORTH -> DIRECTION.WEST;
                    case EAST -> DIRECTION.NORTH;
                    case SOUTH -> DIRECTION.EAST;
                    case WEST -> DIRECTION.SOUTH;
                    default -> curFace;
                };
    }

    public static DIRECTION rotateRight(DIRECTION curFace){
         return switch(curFace) {
            case NORTH ->DIRECTION.EAST;
            case EAST -> DIRECTION.SOUTH;
            case SOUTH -> DIRECTION.WEST;
            case WEST -> DIRECTION.NORTH;
            default -> curFace;
         };
    }
}

/*
public class Direction {

    public DIRECTION curFace;


}*/
