package com.techgames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @Test
    public void chkRoverNavigation() {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("1 3 N", marsRover.navigate("LMLMLMLMM"));
    }

    @Test
    public void chk2ndRoverNavigation() {
        MarsRover marsRover = new MarsRover("M2", 3, 3,'E');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("5 1 E", marsRover.navigate("MMRMMRMRRM"));
    }
}
