package model.device;

import model.environment.Environment;
import model.robot.MobileRobot;
import model.environment.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by FakeYou on 3/20/14.
 */
public class Sonar extends Device {
    private final int range = 100;

    private boolean detect;
    private boolean ping;

    private LaserMeasurement detectMeasure;
    private final ArrayList<LaserMeasurement> pingMeasurements;

    public Sonar(String name, MobileRobot robot, Position localPos, Environment environment) {
        super(name, robot, localPos, environment);

        this.detect = false;
        this.ping = false;

        this.pingMeasurements = new ArrayList<LaserMeasurement>();

        backgroundColor = Color.pink;
        this.addPoint(0, 0);
    }

    protected void executeCommand(String command) {

    }

    protected void nextStep() {

    }
}
