package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.GarageDoor;
import com.example.disign.commandPattern.simple.Command;

public class GarageDoorDownCommend implements Command {

    GarageDoor garageDoor;

    public GarageDoorDownCommend(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }
}
