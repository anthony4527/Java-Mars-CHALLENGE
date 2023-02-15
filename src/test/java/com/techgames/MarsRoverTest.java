package com.techgames;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @Test
    public void chkRoverNavigation() {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        Plateau plateau = new Plateau (5,5, false);
        assertEquals ("1 3 N", marsRover.navigate("LMLMLMLMM", plateau));
    }

    @Test
    public void chk2ndRoverNavigation() {
        MarsRover marsRover = new MarsRover("M2", 3, 3,'E');
        Plateau plateau = new Plateau (5,5, false);
        assertEquals ("5 1 E", marsRover.navigate("MMRMMRMRRM", plateau));
    }

    @Test
    public void chkCommandOutsideGrid() {
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        Plateau plateau = new Plateau (5,5, false);
        assertEquals ("5 3 E", marsRover.navigate("MMM", plateau));
    }
    //Unit test for one Rover

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    public void chkCommandForOneRover(String expected, String startPosition, String command) {
        //arrange test data
        int x = Character.getNumericValue(startPosition.charAt(0));
        int y = Character.getNumericValue(startPosition.charAt(2));
        char face = startPosition.charAt(4);
        MarsRover marsRover = new MarsRover("M1",x,y,face);
        Plateau plateau = new Plateau (5,5, false);
        //act by navigate and then assert
        assertEquals (expected, marsRover.navigate(command, plateau));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/twoRoverinput.csv", numLinesToSkip = 1)
    public void chkCommandForTwoRovers(String expected1, String startPosition1, String command1,
                                       String expected2, String startPosition2, String command2) {
        //arrange test data
        int x = Character.getNumericValue(startPosition1.charAt(0));
        int y = Character.getNumericValue(startPosition1.charAt(2));
        char face = startPosition1.charAt(4);
        MarsRover marsRover1 = new MarsRover("M1",x,y,face);

        x = Character.getNumericValue(startPosition2.charAt(0));
        y = Character.getNumericValue(startPosition2.charAt(2));
        face = startPosition2.charAt(4);
        MarsRover marsRover2 = new MarsRover("M2",x,y,face);

        Plateau plateau = new Plateau (5,5, false);
        //act by navigate and then assert
        assertEquals (expected1, marsRover1.navigate(command1, plateau));
        assertEquals (expected2, marsRover2.navigate(command2, plateau));
    }

    @Test
    public void chkRoversMoveAfterEachOther() {
        Plateau plateau = new Plateau (5,5, false);
        //land two rovers
        //rover 2 cannot move when rover1 is on its way
        //rover 2 can move when rover 1 has moved
        MarsRover marsRover1 = new MarsRover("M1", 3, 3,'N');
        MarsRover marsRover2 = new MarsRover("M2", 3, 2,'N');
        assertEquals ("3 4 N", marsRover1.navigate("M", plateau));
        assertEquals ("3 3 N", marsRover2.navigate("MMM", plateau));
        //rover 2 can move when rover 1 has moved
        assertEquals ("4 4 E", marsRover1.navigate("RM", plateau));
        assertEquals ("3 5 N", marsRover2.navigate("MM", plateau));
    }

    //@Test
    public void chkProbeTarge() {
        Plateau plateau = new Plateau (1,1,true);

        MarsRover marsRover = new MarsRover("M1",plateau.target[0], plateau.target[1],'N');

        assertEquals ("found", marsRover.probeTarget(plateau));
    }
    // test different type of space vehicle
    @Test
    public void testBumbleeStart() {
        Plateau plateau = new Plateau (5,5,false);

        BumbleBee bumbleBee = new BumbleBee("BeeA",0, 1 ,'N');

        bumbleBee.move(1, plateau);
        String bPos = String.valueOf(bumbleBee.position[0]) + " " + String.valueOf(bumbleBee.position[1]) +" " +
                bumbleBee.face;
        assertEquals ("0 3 N",bPos );
    }
}
