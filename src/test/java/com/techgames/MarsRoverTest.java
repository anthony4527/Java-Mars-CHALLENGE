package com.techgames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @Test
    public void chkRoverNavigation() {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("1 3 N", marsRover.navigate("LMLMLMMM"));
    }
}
