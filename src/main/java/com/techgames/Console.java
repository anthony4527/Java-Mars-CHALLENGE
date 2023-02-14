package com.techgames;

import java.util.Scanner;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_CYAN = "\u001B[46m";
    private static boolean isValidPosition (String input){
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

    private static boolean isValidCommand (String input){
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

    private static String getRoverLandPosition(Scanner scanner, String name){
        boolean firstInput = false;
        String position;
        do {
            if (name.equals("M1")){
                System.out.println(ANSI_CYAN+"Please enter the position to land the Rover M1:"+ANSI_RESET);
            } else {
                System.out.println(ANSI_PURPLE+"Please enter the position to land the Rover M2:"+ANSI_RESET);
            }

            position = scanner.nextLine();
            if (!isValidPosition(position)){
                System.out.println ("??? Not valid input");
            }  else {
                firstInput = true;
            }
        } while (!firstInput);
        return position;
    }

    private static String getRoverCommand(Scanner scanner, String name) {
        boolean secondInput = false;
        String inputCommand;
        do {
            if (name.equals("M1")){
                System.out.println(ANSI_CYAN +"Enter navigation command for M1:"+ANSI_RESET);
            } else {
                System.out.println(ANSI_PURPLE+"Enter navigation command for M2:"+ANSI_RESET);
            }
            inputCommand = scanner.nextLine();
            if (!isValidCommand(inputCommand)){
                System.out.println ("??? Not valid input");
            }  else {
                secondInput = true;
            }
        } while (!secondInput);
        return inputCommand;
    }

    public static void main(String[] args) {
        boolean start;
        String position, newPosition, plateauRange;
        String inputCommand;
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


        position = getRoverLandPosition(scanner, "M1");
        MarsRover marsRover1 = new MarsRover("M1", Character.getNumericValue(position.charAt(0)),
                Character.getNumericValue(position.charAt(2)), position.charAt(4) );

        position = getRoverLandPosition(scanner, "M2");
        MarsRover marsRover2 = new MarsRover("M2", Character.getNumericValue(position.charAt(0)),
                Character.getNumericValue(position.charAt(2)), position.charAt(4) );

        start = true;
        while (start == true ){

            //send command to Mars Rover & get the output
            inputCommand = getRoverCommand(scanner, "M1");
            newPosition = marsRover1.navigate(inputCommand, plateau);
            System.out.println(ANSI_CYAN+"Mars Rover M1 has moved to ("+ newPosition + ")" + ANSI_RESET);

            inputCommand = getRoverCommand(scanner, "M2");
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