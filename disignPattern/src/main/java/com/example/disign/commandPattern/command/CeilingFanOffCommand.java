package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.CeilingFan;
import com.example.disign.commandPattern.simple.Command;

public class CeilingFanOffCommand implements Command {

    CeilingFan ceilingFan;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.off();
    }
}
