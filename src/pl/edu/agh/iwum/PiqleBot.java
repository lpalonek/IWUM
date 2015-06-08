package pl.edu.agh.iwum;

import java.awt.Color;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class PiqleBot extends Robot {

    private PiqleConfiguration configuration = PiqleConfiguration.getInstance();

    private double lastKnownPosition;

    private double radarTurn;

    public void run() {
        setAllColors(Color.ORANGE);
        configuration.getEnvironment().setBot(this);
        radarTurn = 10;
        while (true) {
            turnRadarRight(radarTurn/2);
            turnRadarLeft( radarTurn);
            System.err.println(getRadarHeading());
            turnRadarRight(radarTurn/2);
            if(radarTurn <= 360) {
                radarTurn *= 2;
            }

        }
    }

    public BotState getState() {
        return new BotState(configuration.getEnvironment(), this);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        play(10);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        play(10);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        radarTurn = 10;
        lastKnownPosition = getRadarHeading();

        turnGunToSpecificHeading(lastKnownPosition);
        System.err.println("Radar base heading "+ getRadarHeading());
        fire(1);
    }

    private void play(int numberOfActions) {
        configuration.getReferee().setMaxIter(numberOfActions);
        configuration.getReferee().episode(getState());
    }

    private void turnGunToSpecificHeading(double heading) {
        double currentGunHeading = getGunHeading();
        double difference = currentGunHeading - heading;
        if (difference > 0) {
            turnLeft(Math.abs(difference));
        } else {
            turnRight(Math.abs(difference));
        }
        System.err.println("difference " + Math.abs(difference));
    }

}
