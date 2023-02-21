package com.techgames;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_CYAN = "\u001B[46m";

    public static final int MaxiumnX =100;
    public static final int MaxiumnY =100;
    public static final int NumOfRovers = 2;

/*
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
*/
    private static boolean isValidCommand (String input){
        if (input == null) {
            return false;
        }
        for (int i=0; i< input.length(); i++){
            //if a command is not in enum list, return error
            if (COMMAND.getCommand(input.charAt(i)) == null) {
                return false;
            }
/*            switch (input.charAt(i)){
                case 'M','L','R': break;
                default: return false;
            }*/
        }
        return true;
    }

    private static SpaceVehicle getRoverLandPosition(Scanner scanner, RectPlateau p, String name) {
        boolean secondInput = false;
        String position;
        int x = -1;
        int y = -1;
        char face = ' ';

        int maxP[] = p.getRange();
        do {
            System.out.println(ANSI_CYAN+"Rover ("+ name + ") - please enter the position to land: "+ANSI_RESET);
            position = scanner.nextLine();
            //check valid input of rover position and face direction

            String[ ] data = position.split(" ");
            if (data.length == 3) {
                try {
                    x = Integer.parseInt(data[0]);
                    y = Integer.parseInt(data[1]);
                    face =data[2].charAt(0);
                    if ((x >0 ) && (x <= maxP[0]) &&
                            (y>0) && (y<= maxP[1]) &&
                            (data[2].length() == 1) && (DIRECTION.getDirection(face) != null)) {
                        secondInput =true;
                        break;
                    }
                } catch(NumberFormatException nfe) {// do not break and continue reqyest input
                }
            }
            System.out.println("?? Invalid Rover landing position - enter again:");
        } while (!secondInput);

        SpaceVehicle rover = new MarsRover(name, x, y, face);
        return rover;
    }

    private static String getRoverCommand(Scanner scanner, String name) {
        boolean secondInput = false;
        String inputCommand;
        do {
            System.out.println(ANSI_CYAN +"Enter navigation command for " + name+ ":"+ANSI_RESET);
            /*
            if (name.equals("M1")){
                System.out.println(ANSI_CYAN +"Enter navigation command for M1:"+ANSI_RESET);
            } else if (name.equals("M2")){
                System.out.println(ANSI_PURPLE+"Enter navigation command for M2:"+ANSI_RESET);
            } if (name.equals("Bumble-A")) {
                System.out.println(ANSI_YELLOW+"Enter navigation command for BumbleBee:"+ANSI_RESET);
            }*/
            inputCommand = scanner.nextLine();
            if (!isValidCommand(inputCommand)){
                System.out.println ("??? Not valid input");
            }  else {
                secondInput = true;
            }
        } while (!secondInput);

        return inputCommand;
    }

    private static String[] getPlateauInput(Scanner scanner) {
        boolean validPlateau = false;
        String plateauRange;
        String[] data;

        //prompt user input of the x & y dimenstion and perform validation
        do {
            plateauRange = scanner.nextLine();
            data = plateauRange.split(" ");
            int numOfData = data.length;
            //if input is valid, break and return data
            if ((numOfData == 2) || (numOfData == 3)){
                try {
                    int x = Integer.parseInt(data[0]);
                    int y = Integer.parseInt(data[1]);
                    if ((x >0 ) && (x <= MaxiumnX) && (y>0) && (y<=MaxiumnY)) {
                        if (numOfData == 2){
                            validPlateau = true;
                            break;
                        } else {
                            if (data[numOfData-1].equals("P")){
                                validPlateau = true;
                                break;
                            }
                        }
                    }
                } catch(NumberFormatException nfe) {
                    System.out.println("?? Invalid plateau size - enter again:");
                }
            }
            System.out.println("?? Invalid plateau size - enter again:");
        } while (!validPlateau);

        return data;
    }

    public static void main(String[] args) {
        boolean start;
        String position, newPosition, plateauRange;
        String inputCommand;
        int xRange =0;
        int yRange =0;
        boolean addOre = false;
        String[] landPosition;
        int landX, landY;
        String[] roverName = {"M1", "M2"};

        ArrayList<SpaceVehicle> listOfRovers = new ArrayList<SpaceVehicle>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("***********************************************");
        System.out.println("* MARS SPECIAL MISSION - PRECIOUS ROCK SEARCH *");
        System.out.println("***********************************************");
        System.out.println("Commander, please enter the plateau range (coorindate) for this mission:");

        //get the dimension of plateau for constructor
        String[] plateauInput = getPlateauInput(scanner);
        xRange = Integer.parseInt(plateauInput[0]);
        yRange = Integer.parseInt(plateauInput[1]);
        int num = plateauInput.length;
        if ((num == 3) && plateauInput[num-1].equals("P")){
            addOre = true;
        }
        RectPlateau plateau = new RectPlateau (xRange,yRange, addOre);

        //get user input of rover land positions and create rovers
        for (int i =0; i<NumOfRovers; i++){
            SpaceVehicle rover  = getRoverLandPosition(scanner, plateau, roverName[i] );
            listOfRovers.add(rover);
            /* parse position to get x, y and face direction
            landPosition = position.split(" ");
            landX = Integer.parseInt(landPosition[0]);
            landY = Integer.parseInt(landPosition[1]);
            //DIRECTION face = DIRECTION.getDirection(landPosition[2].charAt(0));
            MarsRover marsRover1 = new MarsRover("M1", landX, landY, landPosition[2].charAt(0) );*/

        }

/*
        position = getRoverLandPosition(scanner, "M2", xRange, yRange);
        landPosition = position.split(" ");
        landX = Integer.parseInt(landPosition[0]);
        landY = Integer.parseInt(landPosition[1]);
        //DIRECTION face2 = DIRECTION.getDirection(landPosition[2].charAt(0));

        MarsRover marsRover2 = new MarsRover("M2", landX, landY, landPosition[2].charAt(0) );
*/

        start = true;
        while (start == true ){

            for (int i=0; i < listOfRovers.size(); i++) {
                SpaceVehicle rover = listOfRovers.get(i);
                inputCommand = getRoverCommand(scanner, rover.name);
                newPosition = rover.navigate(inputCommand, plateau);
                if (newPosition.substring(0,5).equals("found")){
                    System.out.println(ANSI_RED+"Precious metal !!!!!"+ newPosition + ANSI_RESET);
                    break;
                }
                System.out.println(ANSI_CYAN+"Mars Rover" + rover.name + " has moved to ("+ newPosition + ")" + ANSI_RESET);
            }
            //send command to Mars Rover & get the output
            /*
            inputCommand = getRoverCommand(scanner, "M1");
            newPosition = marsRover1.navigate(inputCommand, plateau);


            inputCommand = getRoverCommand(scanner, "M2");
            newPosition = marsRover2.navigate(inputCommand, plateau);

            if (newPosition.substring(0,5).equals("found")){
                System.out.println(ANSI_RED+"Precious metal  !!!!! "+ newPosition + ANSI_RESET);
                break;
            }
            System.out.println(ANSI_PURPLE+"Mars Rover M2 has moved to ("+ newPosition + ")" + ANSI_RESET);*/

            // Wait for next command
            System.out.println("Enter any key to continue with Rover, 'B' to lanuch BumbleBee, or 'X' to stop");
            switch (scanner.nextLine()) {
                case "X":
                    start = false;
                    System.out.println("You will now disconnect with Rovers..bye bye");
                    break;
                case "B":
                    System.out.println("...Calling BumbleBee to land... he has DOUBLE power to move 2 Grid each step!!!");
/*
                    position = getRoverLandPosition(scanner, "Bumble-A",xRange, yRange);
                    landPosition = position.split(" ");
                    landX = Integer.parseInt(landPosition[0]);
                    landY = Integer.parseInt(landPosition[1]);

                    //DIRECTION face3 = DIRECTION.getDirection(landPosition[2].charAt(0));
                    BumbleBee bumbleBee = new BumbleBee("Bumble-A",landX, landY, landPosition[2].charAt(0)  );

                    inputCommand = getRoverCommand(scanner, "Bumble-A");
                    newPosition = bumbleBee.navigate(inputCommand, plateau);
                    if (newPosition.substring(0,5).equals("found")){
                        System.out.println(ANSI_RED+"BumbleBee -> Precious metal !!!!!"+ newPosition + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_YELLOW+"BumbleBee has moved to ("+ newPosition + ")" + ANSI_RESET);
                    System.out.println(ANSI_YELLOW+"**** BumbleBee is leaving.. continue with Rovers ..." + ANSI_RESET);
                    break;*/
                default:
                    break;
            }

        }

    }
}