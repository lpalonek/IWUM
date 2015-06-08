package pl.edu.agh.iwum;

/**
 * Created by lpalonek on 08/06/15.
 */

//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

import robocode.*;

/**
 * Projekt - a robot by (your name here)
 */
public class DummyBot
        extends AdvancedRobot
{
    /**
     * run: Projekt's default behavior
     */
    public void run() {
        while (true) {
            // Tell the game that when we take move,
            // we'll also want to turn right... a lot.
            setTurnRight(10000);
            // Limit our speed to 5
            setMaxVelocity(5);
            // Start moving (and turning)
            ahead(10000);
            // Repeat.
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        // fire(1);
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        // back(10);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        // back(20);
    }
}

