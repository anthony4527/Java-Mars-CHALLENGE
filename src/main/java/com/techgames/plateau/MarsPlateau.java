package com.techgames;

public interface MarsPlateau {

    public int[] getRange();
    public void setInuse(int x, int y);

    public void clearInuse(int x, int y);

    public boolean isSafePosition (int[] position);

}
