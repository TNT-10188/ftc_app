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

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        while(opModeIsActive()){

            //Control for motors
            robot.leftFrontMotor.setPower(gamepad1.left_stick_y);
            robot.rightFrontMotor.setPower(gamepad1.right_stick_y);
            robot.leftRearMotor.setPower(gamepad1.left_stick_y);
            robot.rightRearMotor.setPower(gamepad1.right_stick_y);


            //Control for collectors
            if (gamepad2.dpad_up == true){
                robot.leftFrontGuidance.setPosition(1);
                robot.rightFrontGuidance.setPosition(0);
                robot.leftRearGuidance.setPosition(1);
                robot.rightRearGuidance.setPosition(0);
                robot.leftCollector.setPosition(1);
                robot.rightCollector.setPosition(0);
            }else if (gamepad2.dpad_down == true){
                robot.leftFrontGuidance.setPosition(0);
                robot.rightFrontGuidance.setPosition(1);
                robot.leftRearGuidance.setPosition(0);
                robot.rightRearGuidance.setPosition(1);
                robot.leftCollector.setPosition(0);
                robot.rightCollector.setPosition(1);
            }else if (gamepad2.dpad_left == true){
                robot.leftCollector.setPosition(1);
                robot.rightCollector.setPosition(1);
            }else if (gamepad2.dpad_right == true){
                robot.leftCollector.setPosition(0);
                robot.rightCollector.setPosition(0);
            /**
            }else if (gamepad2.x == true){
                robot.leftFrontGuidance.setPosition(1);
                robot.rightFrontGuidance.setPosition(1);
                robot.leftCollector.setPosition(1);
                robot.rightCollector.setPosition(1);
            }else if (gamepad2.y == true){
                robot.leftFrontGuidance.setPosition(0);
                robot.rightFrontGuidance.setPosition(0);
                robot.leftCollector.setPosition(0);
                robot.rightCollector.setPosition(0);
            }else if (gamepad2.a == true){
                robot.leftRearGuidance.setPosition(1);
                robot.rightRearGuidance.setPosition(1);
            }else if (gamepad2.b == true){
                robot.leftRearGuidance.setPosition(0);
                robot.rightRearGuidance.setPosition(0);
             */
            }else{
                robot.collectorInit();
            }

            Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);

            telemetry.addData("Red :", robot.colorSensor.red());
            telemetry.addData("Blue:", robot.colorSensor.blue());

            idle();
        }
    }
}
