package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by setht on 9/24/2017.
 */

@TeleOp(name = "TeleOp Test")
public class teleOpTest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();

        while(opModeIsActive()){

            idle();

        }
    }
}
