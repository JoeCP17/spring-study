package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.Streo;
import com.example.disign.commandPattern.simple.Command;

public class StereoOnCommand implements Command {

    Streo streo;

    public StereoOnCommand(Streo streo) {
        this.streo = streo;
    }

    @Override
    public void execute() {
        streo.on();
    }
}
