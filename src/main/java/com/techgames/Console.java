package com.techgames;

import java.util.Scanner;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_CYAN = "\u001B[46m";

    public static final int MaxiumnX =100;
    public static final int MaxiumnY =100;

    private static boolean isValidPlateauSize (String[] data){
        int numOfData = data.length;
        if (numOfData > 3){
            return false;
        }
        try {
            int x = Integer.parseInt(data[0]);
            int y = Integer.parseInt(data[1]);

            if ((x <0 ) || (x > MaxiumnX) ||(y<0) ||(y>MaxiumnY)) {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
        if (numOfData == 3){
            if (!data[numOfData-1].equals("P")) {
            //    System.out.println("err");
                return false;
            }
        }
        return true;
    }
    private static boolean isValidPosition (String input, int maxX, int maxY){
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
            if ((x <0 ) || (x > maxX) ||(y<0) ||(y> maxY)) {
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

    private static String getRoverLandPosition(Scanner scanner, String name, int maxX, int maxY){
        boolean firstInput = false;
        String position;
        do {
            if (name.equals("M1")){
                System.out.println(ANSI_CYAN+"Please enter the position to land the Rover M1:"+ANSI_RESET);
            } else if (name.equals("M2")) {
                System.out.println(ANSI_PURPLE+"Please enter the position to land the Rover M2:"+ANSI_RESET);
            } else if (name.equals("Bumble-A")) {
                System.out.println(ANSI_YELLOW+"Please enter the position to land BumbleBee:"+ANSI_RESET);
            }

            position = scanner.nextLine();
            if (!isValidPosition(position, maxX, maxY)){
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
            } else if (name.equals("M2")){
                System.out.println(ANSI_PURPLE+"Enter navigation command for M2:"+ANSI_RESET);
            } if (name.equals("Bumble-A")) {
                System.out.println(ANSI_YELLOW+"Enter navigation command for BumbleBee:"+ANSI_RESET);
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
        boolean validPlateau = false;
        boolean addOre = false;
        int xRange =0;
        int yRange =0;
        String[] landPosition;
        int landX, landY;

        Scanner scanner = new Scanner(System.in);

        System.out.println("***********************************************");
        System.out.println("* MARS SPECIAL MISSION - PRECIOUS ROCK SEARCH *");
        System.out.println("***********************************************");
        System.out.println("Commander, please enter the plateau range (coorindate) for this mission:");

        do {
            plateauRange = scanner.nextLine();
            if (plateauRange != null) {
                String[] data = plateauRange.split(" ");
                if (isValidPlateauSize(data)) {
                    xRange = Integer.parseInt(data[0]);
                    yRange = Integer.parseInt(data[1]);
                    if ((data.length == 3) && (data[2].equals("P"))) {
                        addOre = true;
                    }
                    validPlateau = true;
                    break;
                } else {
                    System.out.println("?? Invalid plateau size - enter again:");
                }
            }
        } while (!validPlateau);

        Plateau plateau = new Plateau (xRange,yRange, addOre);

        position = getRoverLandPosition(scanner, "M1", xRange, yRange);
        // parse position to get x, y and face direction
        landPosition = position.split(" ");
        landX = Integer.parseInt(landPosition[0]);
        landY = Integer.parseInt(landPosition[1]);
        MarsRover marsRover1 = new MarsRover("M1", landX, landY, landPosition[2].charAt(0) );

        position = getRoverLandPosition(scanner, "M2", xRange, yRange);
        landPosition = position.split(" ");
        landX = Integer.parseInt(landPosition[0]);
        landY = Integer.parseInt(landPosition[1]);
        MarsRover marsRover2 = new MarsRover("M2", landX, landY, landPosition[2].charAt(0)  );

        start = true;
        while (start == true ){

            //send command to Mars Rover & get the output
            inputCommand = getRoverCommand(scanner, "M1");
            newPosition = marsRover1.navigate(inputCommand, plateau);

            if (newPosition.substring(0,5).equals("found")){
                System.out.println(ANSI_RED+"Precious metal !!!!!"+ newPosition + ANSI_RESET);
                break;
            }
            System.out.println(ANSI_CYAN+"Mars Rover M1 has moved to ("+ newPosition + ")" + ANSI_RESET);

            inputCommand = getRoverCommand(scanner, "M2");
            newPosition = marsRover2.navigate(inputCommand, plateau);

            if (newPosition.substring(0,5).equals("found")){
                System.out.println(ANSI_RED+"Precious metal  !!!!! "+ newPosition + ANSI_RESET);
                break;
            }
            System.out.println(ANSI_PURPLE+"Mars Rover M2 has moved to ("+ newPosition + ")" + ANSI_RESET);
            // Wait for next command
            System.out.println("Enter any key to continue with Rover, 'B' to lanuch BumbleBee, or 'X' to stop");
            switch (scanner.nextLine()) {
                case "X":
                    start = false;
                    System.out.println("You will now disconnect with Rovers..bye bye");
                    break;
                case "B":
                    System.out.println("...Calling BumbleBee to land... he has DOUBLE power to move 2 Grid each step!!!");

                    position = getRoverLandPosition(scanner, "Bumble-A",xRange, yRange);
                    landPosition = position.split(" ");
                    landX = Integer.parseInt(landPosition[0]);
                    landY = Integer.parseInt(landPosition[1]);

                    BumbleBee bumbleBee = new BumbleBee("Bumble-A",landX, landY, landPosition[2].charAt(0)  );

                    inputCommand = getRoverCommand(scanner, "Bumble-A");
                    newPosition = bumbleBee.navigate(inputCommand, plateau);
                    if (newPosition.substring(0,5).equals("found")){
                        System.out.println(ANSI_RED+"BumbleBee -> Precious metal !!!!!"+ newPosition + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_YELLOW+"BumbleBee has moved to ("+ newPosition + ")" + ANSI_RESET);
                    System.out.println(ANSI_YELLOW+"**** BumbleBee is leaving.. continue with Rovers ..." + ANSI_RESET);
                    break;
                default:
                    break;
            }

        }

    }
}