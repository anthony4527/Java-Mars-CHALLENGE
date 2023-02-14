package com.techgames;

import java.util.Scanner;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    private  static boolean isValidPosition (String input){
        if (input == null) {
            return false;
        }

        String[ ] data = input.split(" ");
        if (data.length != 3){
            return false;
        }
        try {
            int x = Integer.parseInt(data[0]);
            int y = Integer.parseInt(data[1]);
            if ((x <0 ) || (x >9) ||(y<0) ||(y>9)) {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
        switch (data[2]){
            case "N","E","W","S": break;
            default: return false;
        }
        return true;
    }

    private  static boolean isValidCommand (String input){
        if (input == null) {
            return false;
        }
        for (int i=0; i< input.length(); i++){
            switch (input.charAt(i)){
                case 'M','L','R': break;
                default: return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean start;
        String position, newPosition, plateauRange;
        String inputCommand;
        boolean firstInput = false;
        boolean secondInput = false;
        int x,y;

        Scanner scanner = new Scanner(System.in);


        System.out.println("***********************************************");
        System.out.println("* MARS SPECIAL MISSION - PRECIOUS ROCK SEARCH *");
        System.out.println("***********************************************");
        System.out.println("Commander, please enter the plateau range (coorindate) for this mission:");
        plateauRange = scanner.nextLine();

        x = Character.getNumericValue(plateauRange.charAt(0));
        y = Character.getNumericValue(plateauRange.charAt(2));
        Plateau plateau = new Plateau (x,y);

        start = true;
        while (start == true ){
            firstInput = false;
            secondInput = false;

            do {
                System.out.println(ANSI_GREEN+"Please enter the position to land the Rover M1:"+ANSI_RESET);
                position = scanner.nextLine();
                if (!isValidPosition(position)){
                   System.out.println ("??? Not valid input");
                }  else {
                    firstInput = true;
                }
            } while (!firstInput);

            do {
                System.out.println(ANSI_GREEN +"Enter navigation command for M1:"+ANSI_RESET);
                inputCommand = scanner.nextLine();

                if (!isValidCommand(inputCommand)){
                    System.out.println ("??? Not valid input");
                }  else {
                    secondInput = true;
                }
            } while (!secondInput);

            //send command to Mars Rover & get the output
            MarsRover marsRover1 = new MarsRover("M1", Character.getNumericValue(position.charAt(0)),
                    Character.getNumericValue(position.charAt(2)), position.charAt(4) );
            newPosition = marsRover1.navigate(inputCommand, plateau);

            System.out.println(ANSI_GREEN+"Mars Rover M1 has moved to ("+ newPosition + ")" + ANSI_RESET);

// Launch M2
            firstInput = false;
            secondInput = false;

            do {
                System.out.println(ANSI_PURPLE+"Please enter the position to land the Rover M2:"+ANSI_RESET);
                position = scanner.nextLine();
                if (!isValidPosition(position)){
                    System.out.println ("??? Not valid input");
                }  else {
                    firstInput = true;
                }
            } while (!firstInput);

            do {
                System.out.println(ANSI_PURPLE+"Enter navigation command for M2:"+ANSI_RESET);
                inputCommand = scanner.nextLine();

                if (!isValidCommand(inputCommand)){
                    System.out.println ("??? Not valid input");
                }  else {
                    secondInput = true;
                }
            } while (!secondInput);

            //send command to Mars Rover & get the output
            MarsRover marsRover2 = new MarsRover("M2", Character.getNumericValue(position.charAt(0)),
                    Character.getNumericValue(position.charAt(2)), position.charAt(4) );
            newPosition = marsRover2.navigate(inputCommand, plateau);

            System.out.println(ANSI_PURPLE+"Mars Rover M2 has moved to ("+ newPosition + ")" + ANSI_RESET);

            // Wait for next command
            System.out.println("Enter any key to continue or 'X' to stop");
            if (scanner.nextLine().equals("X")) {
                start = false;
                System.out.println("You will now disconnect with Rovers..bye bye");
                break;
            }
        }

    }
}