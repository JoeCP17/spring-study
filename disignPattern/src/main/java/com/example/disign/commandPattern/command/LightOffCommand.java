package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.simple.Command;
import com.example.disign.commandPattern.simple.Light;

public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
