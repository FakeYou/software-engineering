package model.robot;

import com.sun.swing.internal.plaf.metal.resources.metal_sv;
import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Title    :   The Mobile Robot Explorer Simulation Environment v2.0
 * Copyright:   GNU General Public License as published by the Free Software Foundation
 * Company  :   Hanze University of Applied Sciences
 *
 * @author Dustin Meijer        (2012)
 * @author Alexander Jeurissen  (2012)
 * @author Davide Brugali       (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

	private final OccupancyMap map;
	private final MobileRobot robot;

	private boolean running;

    private PipedInputStream pipeIn;
    private BufferedReader input;
    private PrintWriter output;
    private char[][] grid;

    private double[] position;
    private double[] measures;

	public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
		this.map = map;
		this.robot = robot;
	}

	/**
	 * In this method the gui.controller sends commands to the robot and its devices.
	 * At the moment all the commands are hardcoded.
	 * The exercise is to let the gui.controller make intelligent decisions based on
	 * what has been discovered so far. This information is contained in the OccupancyMap.
	 */
	public void run() {
		String result;
		this.running = true;

        position = new double[3];
        measures = new double[360];

		while (running) {
            try {
                pipeIn = new PipedInputStream();
                input = new BufferedReader(new InputStreamReader(pipeIn));
                output = new PrintWriter(new PipedOutputStream(pipeIn), true);
                grid = map.getGrid();

                robot.setOutput(output);

                scan();
                getPosition();

                double front = measurement(0, 5);
                double right = measurement(110, 20);
                double left = measurement(-110, 20);
                double back = measurement(180, 20);

                System.out.println("front: " + front + ", right: " + right + ", back: " + back + ", left: " + left);

                if(right > 60 && left > 60 && front > 60) {
                    moveForward(10);
                }

                if(right >= 45) {
                    turnTo(position[2] + 45);
                }
                else if(front >= 70) {
                    moveForward(20);
                }
                else if(front >= 20) {
                    moveForward(10);
                }
                else {
                    turnTo(position[2] - 45);
                }
            }
            catch (Exception e) {
                System.err.println(e);
                System.err.println("execution stopped");
                running = false;
            }
		}
	}

    private double measurement(int direction, int cone) {
        int min = direction - cone;
        int max = direction + cone;

        int total = 0;
        int amount = 0;
        int lowest = Integer.MAX_VALUE;

        for(int i = min; i < max; i++) {
            double measurement;
            int measure = i;

            while(measure < 0) {
                measure += 360;
            }

            while(measure >= 360) {
                measure -= 360;
            }

            measurement = measures[measure];

            total += measurement;
            amount += 1;

            if(measurement < lowest) {
                lowest = (int) measurement;
            }
        }

        return total / amount;
    }

    private void moveForward(int units) throws IOException {
        robot.sendCommand("P1.MOVEFW " + units);
        input.readLine();
    }

    private void moveBackward(int units) throws IOException {
        robot.sendCommand("P1.MOVEBW " + units);
        input.readLine();
    }

    private void getPosition() throws IOException {
        String result;

        robot.sendCommand("R1.GETPOS");
        result = input.readLine();
        parsePosition(result, position);
    }

    private void scan() throws IOException {
        String result;

        getPosition();

        robot.sendCommand("L1.SCAN");
        result = input.readLine();
        parseMeasures(result, measures);
        map.drawLaserScan(position, measures);
    }

    private void moveTo(double targetX, double targetY) throws IOException {
//        System.out.println("MoveTo [" + targetX + ", " + targetY + "]");

        String result;

        robot.sendCommand("R1.GETPOS");
        result = input.readLine();
        parsePosition(result, position);

        double currentX = position[0];
        double currentY = position[1];

        double diffX = targetX - currentX;
        double diffY = targetY - currentY;
        double diffAngle = Math.toDegrees(Math.atan2(diffY, diffX));

        turnTo(diffAngle);

        double distance = Math.sqrt(diffX * diffX + diffY * diffY);

        robot.sendCommand("P1.MOVEFW " + (int) distance);
        input.readLine();
    }

    private void turnTo(double angle) throws IOException {
//        System.out.println("TurnTo [" + angle + "]");

        getPosition();

        double currentAngle = position[2];

        if(angle > currentAngle) {
            int moveAngle = (int) Math.round(360 + angle - currentAngle);

            while(moveAngle > 360) {
                moveAngle -= 360;
            }

            robot.sendCommand("P1.ROTATERIGHT " + moveAngle);
            input.readLine();
        }
        else {
            int moveAngle = (int) Math.round(currentAngle - angle);

            while(moveAngle > 360) {
                moveAngle -= 360;
            }

            robot.sendCommand("P1.ROTATELEFT " + moveAngle);
            input.readLine();
        }
    }

    private boolean isFacing(String direction) throws IOException {
        getPosition();

        int min = 0;
        int max = 0;

        if(direction.equals("north")) {
            min = 260;
            max = 280;
        }
        else if(direction.equals("east")) {
            min = 350;
            max = 10;
        }
        else if(direction.equals("south")) {
            min = 80;
            max = 100;
        }
        else if(direction.equals("west")) {
            min = 170;
            max = 190;
        }

        if(position[2] > min && position[2] < max) {
            return true;
        }

        return false;
    }

	private void parsePosition(String value, double position[]) {
		int indexInit;
		int indexEnd;
		String parameter;

		indexInit = value.indexOf("X=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("Y=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("DIR=");
		parameter = value.substring(indexInit + 4);
		position[2] = Double.parseDouble(parameter);
	}

	private void parseMeasures(String value, double measures[]) {
		for (int i = 0; i < 360; i++) {
			measures[i] = 100.0;
		}
		if (value.length() >= 5) {
			value = value.substring(5);  // removes the "SCAN " keyword

			StringTokenizer tokenizer = new StringTokenizer(value, " ");

			double distance;
			int direction;
			while (tokenizer.hasMoreTokens()) {
				distance = Double.parseDouble(tokenizer.nextToken().substring(2));
				direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
				if (direction == 360) {
					direction = 0;
				}
				measures[direction] = distance;
				// Printing out all the degrees and what it encountered.
				// System.out.println("direction = " + direction + " distance = " + distance);
			}
		}
	}


}
