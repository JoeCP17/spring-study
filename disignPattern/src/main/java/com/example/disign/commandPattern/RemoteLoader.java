package com.example.disign.commandPattern;

import com.example.disign.commandPattern.command.*;
import com.example.disign.commandPattern.reciver.CeilingFan;
import com.example.disign.commandPattern.reciver.GarageDoor;
import com.example.disign.commandPattern.reciver.Streo;
import com.example.disign.commandPattern.remote.RemoteControl;
import com.example.disign.commandPattern.simple.Light;
import com.example.disign.commandPattern.simple.LightOnCommand;

public class RemoteLoader {

    public static void main(String[] args) {
        // Invoker
        RemoteControl remoteControl = new RemoteControl();

        // Receiver
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor("Garage");
        Streo stereo = new Streo("Living Room");

        // Command
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanOnCommand ceilingFanOn = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        GarageDoorUpCommend garageDoorUp = new GarageDoorUpCommend(garageDoor);
        GarageDoorDownCommend garageDoorDown = new GarageDoorDownCommend(garageDoor);

        StreOnWithCDCommand stereoOnWithCD = new StreOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        // set command(버튼 할당) only to slot 0 ~ 2
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);


    }
}
