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

public class paternosterHardware {

    //Adds motor members.
    public DcMotor leftFrontMotor  = null;
    public DcMotor rightFrontMotor  = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;

    //Adds lifter member
    public DcMotor winchMotor = null;

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

    static final double COUNTS_PER_MOTOR_REV = 1440 ;
    static final double DRIVE_GEAR_REDUCTION = 40.0 ;
    static final double WHEEL_DIAMETER_INCHES = 4.0 ;
    //pi was written completely from memory
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.141592653589793238462643383279502884197169399375105820974944);

    HardwareMap hwMap = null;
    private ElapsedTime runtime = new ElapsedTime();


    public paternosterHardware(){

    }

    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        //Define motors
        leftFrontMotor = hwMap.dcMotor.get("leftFrontMotor");
        rightFrontMotor = hwMap.dcMotor.get("rightFrontMotor");
        leftRearMotor = hwMap.dcMotor.get("leftRearMotor");
        rightRearMotor = hwMap.dcMotor.get("rightRearMotor");

        //Reverse left motors
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        //Zero motors
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);

        //Define winch motor
        winchMotor = hwMap.dcMotor.get("winchMotor");

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
        colorSensor = hwMap.get(ModernRoboticsI2cColorSensor.class, "colorSensor");
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
    //Shortens the control of the guidance servos
    public void guidanceControl(double guide1, double guide2, double guide3, double guide4, double guide5, double guide6){
        leftFrontGuidance.setPosition(guide1);
        rightFrontGuidance.setPosition(guide2);
        leftRearGuidance.setPosition(guide3);
        rightRearGuidance.setPosition(guide4);
        leftCollector.setPosition(guide5);
        rightCollector.setPosition(guide6);
    }

    public void driveTime(double speed, double time){
        if (speed >= -1 && speed <= 1){
            runtime.reset();
            while (runtime.seconds() < time) {
                leftFrontMotor.setPower(speed);
                rightFrontMotor.setPower(speed);
                leftRearMotor.setPower(speed);
                rightRearMotor.setPower(speed);
            }
        }
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
    }

    public void driveInch(double leftInch, double rightInch, double speed){

        int leftFrontTarget;
        int rightFrontTarget;
        int leftRearTarget;
        int rightRearTarget;

        if (speed >= -1 && speed <= 1){
            leftFrontTarget = leftFrontMotor.getCurrentPosition() + (int)(leftInch * COUNTS_PER_INCH);
            rightFrontTarget = rightFrontMotor.getCurrentPosition() + (int)(rightInch * COUNTS_PER_INCH);
            leftRearTarget = leftRearMotor.getCurrentPosition() + (int)(leftInch * COUNTS_PER_INCH);
            rightRearTarget = rightRearMotor.getCurrentPosition() + (int)(rightInch * COUNTS_PER_INCH);

            leftFrontMotor.setTargetPosition(leftFrontTarget);
            rightFrontMotor.setTargetPosition(rightFrontTarget);
            leftRearMotor.setTargetPosition(leftRearTarget);
            rightRearMotor.setTargetPosition(rightRearTarget);

            leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftFrontMotor.setPower(Math.abs(speed));
            rightFrontMotor.setPower(Math.abs(speed));
            leftRearMotor.setPower(Math.abs(speed));
            rightRearMotor.setPower(Math.abs(speed));

            while(leftFrontMotor.isBusy() && rightFrontMotor.isBusy() && leftRearMotor.isBusy() && rightRearMotor.isBusy()){

            }
            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);

            leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
    }

    public void jewelAuto(boolean Red) {
        if (Red == true) {
            if (colorSensor.blue() > colorSensor.red()) {
                jewelFlick.setPosition(FLICK_LEFT);
            } else if (colorSensor.blue() < colorSensor.red()) {
                jewelFlick.setPosition(FLICK_RIGHT);
            } else {
                jewelFlick.setPosition(FLICK_INIT);
            }
        }else{
            if (colorSensor.blue() < colorSensor.red()) {
                jewelFlick.setPosition(FLICK_LEFT);
            } else if (colorSensor.blue() > colorSensor.red()) {
                jewelFlick.setPosition(FLICK_RIGHT);
            } else {
                jewelFlick.setPosition(FLICK_INIT);
            }
        }
    }
}
