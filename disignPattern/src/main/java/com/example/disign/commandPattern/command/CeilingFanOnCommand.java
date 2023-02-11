package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.CeilingFan;
import com.example.disign.commandPattern.simple.Command;

public class CeilingFanOnCommand implements Command {

    CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.high();
    }
}
