package com.techgames;

public class BumbleBee extends SpaceVehicle {

    public final int POWER=2;
    public BumbleBee (String name, int x, int y, char direction) {
        super(name, x, y, direction);

    }

    public String probeTarget (RectPlateau plateau){
        if ((this.position[0] == plateau.target[0])&& (this.position[1] == plateau.target[1])) {
            return ("found Ore at ("+ String.valueOf(position[0])+","+ String.valueOf(position[1])+ ") by BumbleBee") ;
        } else {
            return "Nil";
        }
    }


}
