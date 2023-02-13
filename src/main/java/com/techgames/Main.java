package com.techgames;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean start;
        String position, newPosition;
        String inputCommand;

        Scanner scanner = new Scanner(System.in);
        Plateau plateau = new Plateau (5,5);

        start = true;
        while (start == true ){
            System.out.println("Please enter the position to land the Rover:");
            position = scanner.nextLine();
            if (position.equals("999")) {
                start = false;
                System.out.println("You will now disconnect with Rover...bye bye");
                break;
            }

            System.out.println("What is your navigation command?");
            inputCommand = scanner.nextLine();

            //send command to Mars Rover & get the output
            MarsRover marsRover = new MarsRover("M1", Character.getNumericValue(position.charAt(0)),
                    Character.getNumericValue(position.charAt(2)), position.charAt(4) );
            newPosition = marsRover.navigate(inputCommand, plateau);

            System.out.println("Mars Rover has moved to "+ newPosition);
            System.out.println("Input next command or enter '999' to exit");
        }




    }
}