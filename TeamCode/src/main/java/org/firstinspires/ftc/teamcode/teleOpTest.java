package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by setht on 9/24/2017.
 */

@TeleOp(name = "TeleOp Test")
//@Disabled
public class teleOpTest extends LinearOpMode{

    paternosterHardware robot   = new paternosterHardware();

    @Override
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap);
        robot.collectorInit();

        waitForStart();

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        int speedReduction = 3;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        while(opModeIsActive()){

            //Control for motors
            robot.leftFrontMotor.setPower((gamepad1.right_stick_y-gamepad1.right_stick_x)/speedReduction);
            robot.rightFrontMotor.setPower((gamepad1.right_stick_y+gamepad1.right_stick_x)/speedReduction);
            robot.leftRearMotor.setPower((gamepad1.right_stick_y-gamepad1.right_stick_x)/speedReduction);
            robot.rightRearMotor.setPower((gamepad1.right_stick_y+gamepad1.right_stick_x)/speedReduction);

            //Control for collectors
            if (gamepad2.dpad_up == true){
                robot.guidanceControl(1,0,1, 0, 1, 0);
            }else if (gamepad2.dpad_down == true){
                robot.guidanceControl(0,1, 0, 1, 0, 1);
            }else if (gamepad2.dpad_left == true){
                robot.guidanceControl(0.5, 0.5, 0.5, 0.5, 1, 1);
            }else if (gamepad2.dpad_right == true){
                robot.guidanceControl(0.5, 0.5, 0.5, 0.5, 0, 0);
            }else{
                robot.collectorInit();
            }
            // Strafing
            if (gamepad1.left_bumper == true) {
                robot.leftFrontMotor.setPower(-1);
                robot.rightFrontMotor.setPower(1);
                robot.leftRearMotor.setPower(1);
                robot.rightRearMotor.setPower(-1);
            }else if (gamepad1.right_bumper == true) {
                robot.leftFrontMotor.setPower(1);
                robot.rightFrontMotor.setPower(-1);
                robot.leftRearMotor.setPower(-1);
                robot.rightRearMotor.setPower(1);
            }
            // Elevator
            if (gamepad2.y == true);
            robot.winchMotor


             //
            Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);

            telemetry.addData("Red :", robot.colorSensor.red());
            telemetry.addData("Blue:", robot.colorSensor.blue());

            idle();
        }
    }
}
