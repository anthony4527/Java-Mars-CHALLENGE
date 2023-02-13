package com.techgames;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @Test
    public void chkRoverNavigation() {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("1 3 N", marsRover.navigate("LMLMLMLMM", plateau));
    }

    @Test
    public void chk2ndRoverNavigation() {
        MarsRover marsRover = new MarsRover("M2", 3, 3,'E');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("5 1 E", marsRover.navigate("MMRMMRMRRM", plateau));
    }

    @Test
    public void chkCommandOutsideGrid() {
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        Plateau plateau = new Plateau (5,5);
        assertEquals ("5 3 E", marsRover.navigate("MMM", plateau));
    }
    //Unit test for one Rover
    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    public void chkCommandForOneRover(String expected, String command) {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        Plateau plateau = new Plateau (5,5);
        assertEquals (expected, marsRover.navigate(command, plateau));
    }
}
