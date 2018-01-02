package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by setht on 11/9/2017.
 * Updated by setht on 2017-12-31
 */

public class flipperHardware {

    //Adds motor members.
    public DcMotor leftFrontMotor  = null;
    public DcMotor rightFrontMotor  = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;

    //Adds jewel arm members.
    public Servo jewelArm = null;
    public Servo jewelFlick = null;

    //Adds collector members.
    public Servo leftFrontGuidance = null;
    public Servo rightFrontGuidance = null;
    public Servo leftRearGuidance = null;
    public Servo rightRearGuidance = null;
    public Servo leftCollector = null;
    public Servo rightCollector = null;

    //Adds sensor members
    public ColorSensor colorSensor = null;

    //Useful variables
    public static final double ARM_UP = 0.2;
    public static final double ARM_DOWN = 0.775;

    public double FLICK_LEFT = 0.3;
    public double FLICK_RIGHT = 1;
    public double FLICK_INIT = 0.7;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public flipperHardware(){

    }

    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        //Define motors
        leftFrontMotor = hwMap.dcMotor.get("frontLeftMotor");
        rightFrontMotor = hwMap.dcMotor.get("frontRightMotor");
        leftRearMotor = hwMap.dcMotor.get("backLeftMotor");
        rightRearMotor = hwMap.dcMotor.get("backRightMotor");

        //Reverse left motors
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        //Zero motors
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);

        //Define jewel arm
        jewelArm = hwMap.servo.get("jewelArm");
        jewelFlick = hwMap.servo.get("jewelFlick");

        //Init arm
        jewelArm.setPosition(ARM_UP);
        jewelFlick.setPosition(FLICK_INIT);

        //Define collectors
        leftFrontGuidance = hwMap.servo.get("leftFrontGuidance");
        rightFrontGuidance = hwMap.servo.get("rightFrontGuidance");
        leftRearGuidance = hwMap.servo.get("leftRearGuidance");
        rightRearGuidance =hwMap.servo.get("rightRearGuidance");
        leftCollector = hwMap.servo.get("leftCollector");
        rightCollector =hwMap.servo.get("rightCollector");

        //Define sensors
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
    }
    //Initializes guidance motors
    public void collectorInit(){
        leftFrontGuidance.setPosition(0.5);
        rightFrontGuidance.setPosition(0.5);
        leftRearGuidance.setPosition(0.5);
        rightRearGuidance.setPosition(0.5);
        leftCollector.setPosition(0.5);
        rightCollector.setPosition(0.5);
    }
}

