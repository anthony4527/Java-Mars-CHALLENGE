package com.techgames;

import java.util.HashMap;

public class CommandList {
    HashMap<COMMAND, ICommand> commands = new HashMap<COMMAND, ICommand>();

    public CommandList( SpaceVehicle rover, RectPlateau plateau){
        LeftCommand leftCommand = new LeftCommand(rover);
        RightCommand rightCommand = new RightCommand(rover);
        MoveCommand moveCommand = new MoveCommand(rover, plateau);
        this.commands.put(COMMAND.LEFT, leftCommand);
        this.commands.put(COMMAND.RIGHT, rightCommand);
        this.commands.put(COMMAND.MOVE, moveCommand);
    }


}
