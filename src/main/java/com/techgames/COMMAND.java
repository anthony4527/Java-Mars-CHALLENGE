package com.techgames;

public enum COMMAND {
    LEFT('L'), RIGHT('R'), MOVE('M');

    char value;
    private COMMAND(char value){
        this.value = value;
    }

    public static COMMAND getCommand (char command){
        for (COMMAND c: COMMAND.values()){
            if (command == c.value){
                return c;
            }
        }
        return null;
    }
}
