/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="blueAuto1")
//@Disabled
public class redAuto1 extends LinearOpMode {

    /* Declare OpMode members. */
    paternosterHardware robot = new paternosterHardware();
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            // Jewel Arm
            sleep(1000);
            robot.jewelArm.setPosition(.29);
            // Jewel FLick
            telemetry.addData("Red", robot.colorSensor.red());
            telemetry.addData("Blue", robot.colorSensor.blue());
            if (robot.colorSensor.blue() > robot.colorSensor.red()){
                sleep(1000);
                robot.jewelFlick.setPosition(0);
            }else if (robot.colorSensor.blue() < robot.colorSensor.red()) {
                sleep(1000);
                robot.jewelFlick.setPosition(1);
            }else robot.jewelFlick.setPosition(.5);
        }

        //Leg 1: Drive forward at full speed for 1 second
        telemetry.addData("Status", "Leg 1 In progress");
        telemetry.update();

        robot.driveTime(1, 1);

        telemetry.addData("Status", "Leg 1 complete");
        telemetry.update();
    //Wait 10 seconds in order to make sure everything works
        sleep(10000);


        //Leg 2: Drive backwards 12in at full speed
        telemetry.addData("Status", "Leg 2 in progress");
        telemetry.update();

        robot.driveInch(-12, -12, 1);

        telemetry.addData("Status", "Leg 2 complete");
        telemetry.update();

        //Show that opmode is complete
        telemetry.addData("Status", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
