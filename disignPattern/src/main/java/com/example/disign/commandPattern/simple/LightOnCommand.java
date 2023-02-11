package com.example.disign.commandPattern.simple;

public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand() {}

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
