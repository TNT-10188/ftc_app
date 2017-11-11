package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by setht on 9/24/2017.
 */

@TeleOp(name = "TeleOp Test")
//@Disabled
public class teleOpTest extends LinearOpMode{

    flipperHardware robot   = new flipperHardware();

    @Override
    public void runOpMode() throws InterruptedException{

        robot.init();

        robot.frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        robot.frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        robot.backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        robot.backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        robot.frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        robot.backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        robot.jewelArm = hardwareMap.servo.get("jewelArm");
        robot.jewelFlick = hardwareMap.servo.get("jewelFlick");

        waitForStart();

        while(opModeIsActive()){

            robot.frontLeftMotor.setPower(gamepad1.left_stick_y);
            robot.frontRightMotor.setPower(gamepad1.left_stick_y);
            robot.backLeftMotor.setPower(gamepad1.left_stick_y);
            robot.backRightMotor.setPower(gamepad1.left_stick_y);

            if (gamepad1.a == true){
                robot.jewelArm.setPosition(robot.ARM_UP);
            }else if (gamepad1.y == true){
                robot.jewelArm.setPosition(robot.ARM_DOWN);
            }

            if (gamepad1.x == true){
                robot.jewelFlick.setPosition(robot.FLICK_LEFT);
            }else if (gamepad1.b == true){
                robot.jewelFlick.setPosition(robot.FLICK_RIGHT);
            }else{
                robot.jewelFlick.setPosition(robot.FLICK_INIT);
            }



            idle();
        }
    }
}
