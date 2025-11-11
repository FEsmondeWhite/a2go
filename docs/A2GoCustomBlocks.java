// Based on: https://ftc-docs.firstinspires.org/en/latest/programming_resources/shared/myblocks/index.html
package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


public class A2GoCustomBlocks extends BlocksOpModeCompanion {
    
    @ExportToBlocks (
      comment = "Here is a greeting for you.",
      tooltip = "Greet a person or group.",
      parameterLabels = {"Recipient"}
    )
    public static String myGreeting (String greetingRecipient)  {
      return ("Hello " + greetingRecipient + "!");
    }

    // This function takes the name of a color sensor, gets the color information,
    // and then determines what color the ball is. It returns a number with the 
    // ball ID.
    @ExportToBlocks (
        comment = "Get data from a Rev V3 color sensor, and identify the ball."+
        "0: No ball, 1: Green ball, 2: Purple ball, 3: Yellow ball",
        tooltip = "Ball identification using color sensor.",
        parameterLabels = {"Color Sensor Name, such as 'Color_Sensor_1'"}
    )
    public static int IdentifyBall (String colorSensorName)  {
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, colorSensorName);
        double dist = ((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM);
        int r = colorSensor.red();
        int g = colorSensor.green();
        int b = colorSensor.blue();
        int ball_id;

        if (dist > 3) {
          ball_id = 0; // No ball
        } else if (g / r > 3) {
          ball_id = 1; // Green ball
        } else if ((b + r) / g > 1.5) {
          ball_id = 2; // Purple ball
        } else {
          ball_id = 3; // Yellow ball
        }
        return ball_id;
    }
    
    // This is meant to return a text label.
    // The text label can then be converted to speech on the driver station
    // See: https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Driver-Station-Speech-Telemetry
    @ExportToBlocks (
        comment = "Return the text string given a ball ID."+
        "0: No ball, 1: Green ball, 2: Purple ball, 3: Yellow ball",
        tooltip = "Convert ball ID to text.",
        parameterLabels = {"Ball ID"}
    )
    public static String BallIDtoText (int ballID)  {
        String ballIDtext;

        if (ballID == 0) { // No ball
          ballIDtext = "None"; 
        } else if (ballID == 1) { // Green ball
          ballIDtext = "Green"; 
        } else if (ballID == 2) { // Purple ball
          ballIDtext = "Purple"; 
        } else if (ballID == 3) { // Yellow ball
          ballIDtext = "Yellow"; 
        } else { // Error!
          ballIDtext = "Error"; 
        }
        return ballIDtext;
    }
    
}

