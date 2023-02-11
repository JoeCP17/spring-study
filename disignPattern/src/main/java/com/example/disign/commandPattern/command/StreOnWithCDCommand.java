package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.Streo;
import com.example.disign.commandPattern.simple.Command;

public class StreOnWithCDCommand implements Command {
    Streo stero;

    public StreOnWithCDCommand(Streo stero) {
        this.stero = stero;
    }

    @Override
    public void execute() {
        stero.on();
        stero.setCD();
        stero.setVolume(11);
    }
}
