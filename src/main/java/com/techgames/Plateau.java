package com.techgames;

import java.util.ArrayList;

public class Plateau {

    private int[] origin = {0,0};
    private int[] range = {0,0};
    ArrayList<int[]> gridInuse = new ArrayList<int[]>();

    public Plateau(int x, int y) {
        this.range[0] =x;
        this.range[1] = y;
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
