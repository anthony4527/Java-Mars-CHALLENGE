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
        switch (curFace) {
            case NORTH: return DIRECTION.WEST;
            case EAST: return DIRECTION.NORTH;
            case SOUTH: return DIRECTION.EAST;
            case WEST: return DIRECTION.SOUTH;
            default:return curFace;
        }
    }

    public static DIRECTION rotateRight(DIRECTION curFace){
        switch (curFace) {
            case NORTH: return DIRECTION.EAST;
            case EAST: return DIRECTION.SOUTH;
            case SOUTH: return DIRECTION.WEST;
            case WEST: return DIRECTION.NORTH;
            default:return curFace;
        }
    }
}

/*
public class Direction {

    public DIRECTION curFace;


}*/
