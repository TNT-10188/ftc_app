package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by setht on 9/24/2017.
 */

@TeleOp(name = "TeleOp Test")
//@Disabled
public class teleOpTest extends LinearOpMode{

    flipperHardware robot   = new flipperHardware();

    @Override
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap);
        robot.collectorInit();

        waitForStart();

        while(opModeIsActive()){

            //Control for motors
            robot.leftFrontMotor.setPower(gamepad1.left_stick_y);
            robot.rightFrontMotor.setPower(gamepad1.left_stick_y);
            robot.leftRearMotor.setPower(gamepad1.left_stick_y);
            robot.rightRearMotor.setPower(gamepad1.left_stick_y);

            /**
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
            */

            //Control for collectors
            if (gamepad2.dpad_up == true){
                robot.leftFrontGuidance.setPosition(1);
                robot.rightFrontGuidance.setPosition(1);
                robot.leftRearGuidance.setPosition(1);
                robot.rightRearGuidance.setPosition(1);
                robot.leftCollector.setPosition(1);
                robot.rightCollector.setPosition(1);
            }else if (gamepad2.dpad_down == true){
                robot.leftFrontGuidance.setPosition(0);
                robot.rightFrontGuidance.setPosition(0);
                robot.leftRearGuidance.setPosition(0);
                robot.rightRearGuidance.setPosition(0);
                robot.leftCollector.setPosition(0);
                robot.rightCollector.setPosition(0);
            }else if (gamepad2.dpad_left == true){
                robot.leftCollector.setPosition(1);
                robot.rightCollector.setPosition(0);
            }else if (gamepad2.dpad_right == true){
                robot.leftCollector.setPosition(0);
                robot.rightCollector.setPosition(1);
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
            }else{
                robot.collectorInit();
            }



            idle();
        }
    }
}
