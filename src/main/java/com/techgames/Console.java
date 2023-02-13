package com.techgames;

import java.util.Scanner;

public class Console {

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
        String position, newPosition;
        String inputCommand;
        boolean firstInput = false;
        boolean secondInput = false;

        Scanner scanner = new Scanner(System.in);
        Plateau plateau = new Plateau (5,5);

        start = true;
        while (start == true ){
            firstInput = false;
            secondInput = false;
            position = null;
            inputCommand = null;

            do {
                System.out.println("Please enter the position to land the Rover:");
                position = scanner.nextLine();
                if (!isValidPosition(position)){
                   System.out.println ("??? Not valid input");
                }  else {
                    firstInput = true;
                }
            } while (!firstInput);

            do {
                System.out.println("What is your navigation command?");
                inputCommand = scanner.nextLine();

                if (!isValidCommand(inputCommand)){
                    System.out.println ("??? Not valid input");
                }  else {
                    secondInput = true;
                }
            } while (!secondInput);

            System.out.println("Call method now with command "+inputCommand);
            //send command to Mars Rover & get the output
            MarsRover marsRover = new MarsRover("M1", Character.getNumericValue(position.charAt(0)),
                    Character.getNumericValue(position.charAt(2)), position.charAt(4) );
            newPosition = marsRover.navigate(inputCommand, plateau);

            System.out.println("Mars Rover has moved to "+ newPosition);

            System.out.println("Enter any key to continue or 'X' to stop");
            if (scanner.nextLine().equals("X")) {
                start = false;
                System.out.println("You will now disconnect with Rover...bye bye");
                break;
            }
        }

    }
}