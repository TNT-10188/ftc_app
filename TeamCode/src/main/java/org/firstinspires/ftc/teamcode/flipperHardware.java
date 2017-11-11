package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by setht on 11/9/2017.
 */

public class flipperHardware {

    public DcMotor frontLeftMotor  = null;
    public DcMotor frontRightMotor  = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;

    public Servo jewelArm = null;
    public Servo jewelFlick = null;

    //Useful variables
    public static final double ARM_UP = 0.2;
    public static final double ARM_DOWN = 0.775;

    public double FLICK_LEFT = 0.3;
    public double FLICK_RIGHT = 1;
    public double FLICK_INIT = 0.7;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public flipperHardware{

    }

    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        jewelArm = hwMap.servo.get("jewelArm");
        jewelFlick = hwMap.servo.get("jewelFlick");

        jewelArm.setPosition(ARM_UP);
        jewelFlick.setPosition(FLICK_INIT);
    }
}

