package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
            telemetry.addData("Elevator", robot.winchMotor.getCurrentPosition());
            telemetry.update();
            int targetLifter;
            /**if (gamepad2.right_bumper == true){
                targetLifter = (robot.winchMotor.getCurrentPosition() + 100);
                robot.winchMotor.setTargetPosition(targetLifter);
                robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.winchMotor.setPower(1);
            }else if (gamepad2.left_bumper == true)
                targetLifter = (robot.winchMotor.getCurrentPosition() - 100);
                robot.winchMotor.setTargetPosition(targetLifter);
                robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.winchMotor.setPower(1);**/
            if (gamepad2.y == true) {
                robot.winchMotor.setTargetPosition(robot.POSITION_1);
                robot.winchMotor.setPower(1);

                while ((robot.winchMotor.getCurrentPosition()) <= (robot.POSITION_1 + 250) || (robot.winchMotor.getCurrentPosition()) >= (robot.POSITION_1 - 250)) {
                    robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }else if (gamepad2.b == true) {
                robot.winchMotor.setTargetPosition(robot.POSITION_2);
                robot.winchMotor.setPower(1);

                while ((robot.winchMotor.getCurrentPosition()) <= (robot.POSITION_2 + 250) || (robot.winchMotor.getCurrentPosition()) >= (robot.POSITION_2 - 250)) {
                    robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }else if (gamepad2.a == true) {
                robot.winchMotor.setTargetPosition(robot.POSITION_3);
                robot.winchMotor.setPower(1);

                while ((robot.winchMotor.getCurrentPosition()) <= (robot.POSITION_3 + 250) || (robot.winchMotor.getCurrentPosition()) >= (robot.POSITION_3 - 250)) {
                    robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }else if (gamepad2.x == true) {
                robot.winchMotor.setTargetPosition(robot.POSITION_4);
                robot.winchMotor.setPower(1);

                while ((robot.winchMotor.getCurrentPosition()) <= (robot.POSITION_4 + 250) || (robot.winchMotor.getCurrentPosition()) >= (robot.POSITION_4 - 250)) {
                    robot.winchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }
             //
            Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);

            telemetry.addData("Red :", robot.colorSensor.red());
            telemetry.addData("Blue:", robot.colorSensor.blue());
            telemetry.update();

            //jewel arm calibration
            if (gamepad1.dpad_up) {
                robot.jewelArm.setPosition(robot.jewelArm.getPosition()+0.1);
                telemetry.addData("Jewel Arm", robot.jewelArm.getPosition());
            } else if (gamepad1.dpad_down) {
                robot.jewelArm.setPosition(robot.jewelArm.getPosition()-0.1);
                telemetry.addData("Jewel Arm", robot.jewelArm.getPosition());

                }
            }
           telemetry.update();
            idle();
        }
    }

