package com.example.disign.commandPattern.simple;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Light light = new Light();

        LightOnCommand lightOnCommand = new LightOnCommand(light);

        remote.setSlot(lightOnCommand);
        remote.buttonWasPressed();
    }
}
