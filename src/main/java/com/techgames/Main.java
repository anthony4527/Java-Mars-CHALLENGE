package com.techgames;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the position to land the Rover");
        String position = scanner.nextLine();
        System.out.println("What is your navigation command?");
        String inputCommand = scanner.nextLine();
    }
}