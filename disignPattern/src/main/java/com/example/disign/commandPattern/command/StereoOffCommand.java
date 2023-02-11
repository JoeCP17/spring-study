package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.reciver.Streo;
import com.example.disign.commandPattern.simple.Command;

public class StereoOffCommand implements Command {

    Streo streo;

    public StereoOffCommand(Streo streo) {
        this.streo = streo;
    }

    @Override
    public void execute() {
        streo.off();
    }
}
