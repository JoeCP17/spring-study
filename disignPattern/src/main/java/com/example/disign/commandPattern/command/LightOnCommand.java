package com.example.disign.commandPattern.command;

import com.example.disign.commandPattern.simple.Command;
import com.example.disign.commandPattern.simple.Light;

public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
