package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by setht on 9/24/2017.
 */

@TeleOp(name = "TeleOp Test")
//@Disabled
public class teleOpTest extends LinearOpMode{

    private double armUp = 0.2;
    private double armDown = 0.775;

    private double flickLeft = 0.3;
    private double flickRight = 1;
    private double flickInit = 0.7;

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    private Servo jewelArm;
    private Servo jewelFlick;

    @Override
    public void runOpMode() throws InterruptedException{

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        jewelArm = hardwareMap.servo.get("jewelArm");
        jewelFlick = hardwareMap.servo.get("jewelFlick");

        waitForStart();

        while(opModeIsActive()){

            frontLeftMotor.setPower(gamepad1.left_stick_y);
            frontRightMotor.setPower(gamepad1.left_stick_y);
            backLeftMotor.setPower(gamepad1.left_stick_y);
            backRightMotor.setPower(gamepad1.left_stick_y);

            if (gamepad1.a == true){
                jewelArm.setPosition(armUp);
            }else if (gamepad1.y == true){
                jewelArm.setPosition(armDown);
            }

            if (gamepad1.x == true){
                jewelFlick.setPosition(flickLeft);
            }else if (gamepad1.b == true){
                jewelFlick.setPosition(flickRight);
            }else{
                jewelFlick.setPosition(flickInit);
            }



            idle();
        }
    }
}
