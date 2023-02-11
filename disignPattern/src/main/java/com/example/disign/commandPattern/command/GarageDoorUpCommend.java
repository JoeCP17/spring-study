package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.GarageDoor;
import com.example.disign.commandPattern.simple.Command;

public class GarageDoorUpCommend implements Command {

    GarageDoor garageDoor;

    public GarageDoorUpCommend(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
