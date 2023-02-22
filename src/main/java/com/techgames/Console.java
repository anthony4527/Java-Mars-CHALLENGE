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
    //public static final int NumOfRovers = 2;

    private static boolean isValidCommand (String input){
        if (input == null) {
            return false;
        }
        for (int i=0; i< input.length(); i++){
            //if a command is not in enum list, return error
            if (COMMAND.getCommand(input.charAt(i)) == null) {
                return false;
            }
        }
        return true;
    }

    private static SpaceVehicle getRoverLandPosition(Scanner scanner, RectPlateau p, int type, String name) {
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
                    if ((x >= 0 ) && (x <= maxP[0]) &&
                            (y>= 0) && (y<= maxP[1]) &&
                            (data[2].length() == 1) && (DIRECTION.getDirection(face) != null)) {
                        secondInput =true;
                        break;
                    }
                } catch(NumberFormatException nfe) {// do not break and continue reqyest input
                }
            }
            System.out.println("?? Invalid Rover landing position - enter again:");
        } while (!secondInput);

        return (
                switch (type){
                    case 1-> (new MarsRover(name, x, y, face));
                    case 2 -> (new BumbleBee(name, x,y, face));
                    default -> null;
                });
    }

    private static String getRoverCommand(Scanner scanner, String name) {
        boolean secondInput = false;
        String inputCommand;
        do {
            System.out.println(ANSI_YELLOW +"Enter navigation command for " + name+ ":"+ANSI_RESET);
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
        String  newPosition;
        String inputCommand;
        int xRange =0;
        int yRange =0;
        boolean addOre = false;
        //String[] roverName = {"M1", "M2"};

        ArrayList<SpaceVehicle> listOfRovers = new ArrayList<SpaceVehicle>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("***********************************************");
        System.out.println("* MARS SPECIAL MISSION - PRECIOUS ROCK SEARCH *");
        System.out.println("***********************************************");
        System.out.println("STEP 1 - please enter the plateau range (coorindate) for this mission:");

        //get the dimension of plateau for constructor
        String[] plateauInput = getPlateauInput(scanner);
        xRange = Integer.parseInt(plateauInput[0]);
        yRange = Integer.parseInt(plateauInput[1]);
        int num = plateauInput.length;
        if ((num == 3) && plateauInput[num-1].equals("P")){
            addOre = true;
        }
        RectPlateau plateau = new RectPlateau (xRange,yRange, addOre);

        System.out.println("***********************************************");
        System.out.println("STEP 2 - Let's mobilize Mars Rover and other Space Vehicles for the mission...");
        ArrayList<String[]> readyCars = new ArrayList<>();
        boolean moreCar = true;
        // console can accept user to mobilize as many rovers or bumbleBee transformers as they like
        do {
            System.out.println("Enter the type ('1' for Mars Rover ; '2' for Transformer) with space separated Vehicle name; enter 'X' to finish: ");
            String input = scanner.nextLine();
            if (!input.equals("X")){
                String[] data = input.split(" ");
                //if type is valid and name is Alphanmeric, add to list
                if (((!data[0].equals("1")) && (!data[0].equals("2")))
                    || (data.length > 2)){
                    System.out.println("??Incorrect space vehicle");
                } else {
                    readyCars.add(data);
                }
            } else {
                moreCar = false;
                break;
            }
        } while (moreCar);

        System.out.println("***********************************************");
        System.out.println("STEP 3 - Launch the Rovers... ");

        int numOfRovers = readyCars.size();
                //get user input of rover land positions and create rovers
        for (int i =0; i<numOfRovers; i++){
            String[] vInfo = readyCars.get(i); //vehicle info
            int vType = Integer.parseInt(vInfo[0]);
            SpaceVehicle vehicle  = getRoverLandPosition(scanner, plateau, vType, vInfo[1] );
            listOfRovers.add(vehicle);
        }

        System.out.println("***********************************************");
        System.out.println("STEP 4 - Fire Commands to the Rovers and Space Vehicles... ");

        start = true;
        while (start == true ){

            for (int i=0; i < listOfRovers.size(); i++) {
                SpaceVehicle rover = listOfRovers.get(i);
                inputCommand = getRoverCommand(scanner, rover.name);
                //send command to Mars Rover & get the output
                newPosition = rover.navigate(inputCommand, plateau);

                if (newPosition.substring(0,5).equals("found")){
                    System.out.println(ANSI_RED+"Precious metal !!!!!"+ newPosition + ANSI_RESET);
                    break;
                }
                System.out.println(ANSI_PURPLE+"Mars Rover" + rover.name + " has moved to ("+ newPosition + ")" + ANSI_RESET);
            }

            System.out.println("Enter any key to continue commanding the mobilized Rovers, or 'X' to stop");
            switch (scanner.nextLine()) {
                case "X":
                    start = false;
                    System.out.println("You will now disconnect with Rovers..bye bye");
                    break;

                default:
                    break;
            }
        }
    }
}