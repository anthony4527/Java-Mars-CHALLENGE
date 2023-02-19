package com.techgames;

public class MarsRover extends SpaceVehicle {

   public MarsRover (String name, int x, int y, char direction) {
        super(name, x, y, direction);
   }

   /*
   public String probeTarget (RectPlateau plateau){
       if ((this.position[0] == plateau.target[0])&& (this.position[1] == plateau.target[1])) {
           return ("found Ore at ("+ String.valueOf(position[0])+","+ String.valueOf(position[1])+ ")") ;
       } else {
           return "Nil";
        }
    }*/

}
