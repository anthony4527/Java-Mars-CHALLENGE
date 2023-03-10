package com.techgames;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @Test
    public void chkRoverNavigation() {
        MarsRover marsRover = new MarsRover("M1", 1, 2,'N');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("1 3 N", marsRover.navigate("LMLMLMLMM", plateau));
    }

    @Test
    public void chk2ndRoverNavigation() {
        MarsRover marsRover = new MarsRover("M2", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("5 1 E", marsRover.navigate("MMRMMRMRRM", plateau));
    }

    @Test
    public void chkCommandOutsideGrid() {
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
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
        RectPlateau plateau = new RectPlateau (5,5, false);
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

        RectPlateau plateau = new RectPlateau (5,5, false);
        //act by navigate and then assert
        assertEquals (expected1, marsRover1.navigate(command1, plateau));
        assertEquals (expected2, marsRover2.navigate(command2, plateau));
    }

    @Test
    public void chkRoversAvoidCollision() {
        RectPlateau plateau = new RectPlateau (5,5, false);
        //land two rovers
        //rover 2 cannot move when rover1 is on its way
        //rover 2 can move when rover 1 has moved
        MarsRover marsRover1 = new MarsRover("M1", 3, 3,'N');
        MarsRover marsRover2 = new MarsRover("M2", 3, 2,'N');
        assertEquals ("3 4 N", marsRover1.navigate("M", plateau));
        //the next test -rover 2 move 1 step north from (3,2) to (3,3);
        // next  step will check rover1 is in (3,4),and rover2 will alert and stay at (3,3) to avoid collision
        assertEquals ("3 3 N", marsRover2.navigate("MMM", plateau));
        //rover 2 can move when rover 1 has moved
        assertEquals ("4 4 E", marsRover1.navigate("RM", plateau));
        assertEquals ("3 5 N", marsRover2.navigate("MM", plateau));
    }

    @Test
    public void chkProbeTarge() {
        RectPlateau plateau = new RectPlateau (1,1,true);

        MarsRover marsRover = new MarsRover("M1",plateau.target[0], plateau.target[1],'N');

        assertEquals ("found", (marsRover.navigate("MRMRMRM", plateau)).substring(0,5));
    }
    @Test
    public void detectIncorrectCommand(){
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("-1", marsRover.navigate("MM@M", plateau));
    }

    // test different type of space vehicle - bumbleBee move 2 grids each command
    @Test
    public void testBumbleeStart() {
        RectPlateau plateau = new RectPlateau (5,5,false);

        BumbleBee bumbleBee = new BumbleBee("BeeA",0, 1 ,'N');

        MoveCommand moveCommand = new MoveCommand(bumbleBee, plateau);
        bumbleBee.navigate("M", plateau);
        String bPos = String.valueOf(bumbleBee.position[0]) + " " + String.valueOf(bumbleBee.position[1]) +" " +
                bumbleBee.face.compass;
        assertEquals ("0 2 N",bPos );
    }

    @Test
    public void testBumbleeNavigateProbe() {
        RectPlateau plateau = new RectPlateau (5,5,false);

        BumbleBee bumbleBee = new BumbleBee("BeeA",1, 0 ,'N');

        assertEquals ("3 1 N",        bumbleBee.navigate("RMMLM",plateau) );
    }


    @Test
    public void checkDirectionCommand (){
//        Direction myDirection = new Direction();
        //direction.curFace = DIRECTION.NORTH;
        assertEquals(DIRECTION.WEST, DIRECTION.rotateLeft(DIRECTION.NORTH) );
        assertEquals(DIRECTION.SOUTH, DIRECTION.rotateLeft(DIRECTION.WEST) );
        assertEquals(DIRECTION.EAST, DIRECTION.rotateLeft(DIRECTION.SOUTH) );
        assertEquals(DIRECTION.NORTH, DIRECTION.rotateLeft(DIRECTION.EAST) );


    }

    @Test
    public void TestLeftTurn(){
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("3 3 N", marsRover.navigate("L", plateau));

    }

    @Test
    public void TestRightTurn(){
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("3 3 S", marsRover.navigate("R", plateau));

    }

    @Test
    public void TestRoverMove(){
        MarsRover marsRover = new MarsRover("M1", 3, 3,'E');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("4 3 E", marsRover.navigate("M", plateau));
    }

    @ParameterizedTest
    @CsvSource({"3 3 S,M,3 2 S","3 3 W,M,2 3 W","3 3 N,M,3 4 N","3 3 E,M,4 3 E"})

    public void TestMoveParameters(String startPos, String action, String expected){
        int x = Character.getNumericValue(startPos.charAt(0));
        int y = Character.getNumericValue(startPos.charAt(2));
        char face = startPos.charAt(4);

        MarsRover marsRover = new MarsRover("M1", x, y, face);
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals (expected, marsRover.navigate(action, plateau));
    }

    @Test
    public void chkRoverNavigationNewFolder() {
        MarsRover marsRover = new MarsRover("M2", 2, 2,'N');
        RectPlateau plateau = new RectPlateau (5,5, false);
        assertEquals ("2 4 E", marsRover.navigate("MMR", plateau));
    }
}
