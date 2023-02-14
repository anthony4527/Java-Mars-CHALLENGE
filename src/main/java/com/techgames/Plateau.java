package com.techgames;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {

    private int[] origin = {0,0};
    private int[] range = {0,0};
    protected int[] target = {-1,-1};
    ArrayList<int[]> gridInuse = new ArrayList<int[]>();
    public Plateau(int x, int y) {
        this.range[0] =x;
        this.range[1] = y;
        //generate random no. where special material is kept
        Random ran = new Random();
        this.target[0] = ran.nextInt(x+1);
        this.target[1] = ran.nextInt(y+1);
        System.out.println ("target material is at"+String.valueOf(this.target[0])+ " "+ String.valueOf(this.target[1]));
    }

    public int[] getRange(){
        return this.range;
    }

    public void setInuse(int x, int y) {
        int[] location = {x, y};
        gridInuse.add(location);
    }

    public void clearInuse(int x, int y) {
        int[] tmp = {-1, -1};
        int i =0;
        if (gridInuse.size() > 0){
            do {
                tmp = gridInuse.get(i);
                if ((tmp[0] == x ) && (tmp[1] == y)){
                    gridInuse.remove(i);
                    break;
                } else {
                    i++;
                }
            } while(i < gridInuse.size());
        }
    }
}
