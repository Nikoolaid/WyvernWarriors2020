package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear Opmode", group="Linear Opmode")
public class basicDrive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //declaring drive motors

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    //declaring mechanism motors

    private DcMotor intakeMotor = null;
    private DcMotor beltMotor = null;
    private DcMotor shooterMotor = null;

    @Override
    public void RunOpmode() {

    //initializing drive motors

    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    backRight = hardwareMap.get(DcMotor.class, "backRight");

    frontLeft.setDirection(DcMotor.Direction.FORWARD);
    frontRight.setDirection(DcMotor.Direction.FORWARD);
    backLeft.setDirection(DcMotor.Direction.FORWARD);
    backRight.setDirection(DcMotor.Direction.FORWARD);

    //initializing mechanism motors

    intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    beltMotor = hardwareMap.get(DcMotor.class, "beltMotor");
    shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");

    intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    beltMotor.setDirection(DcMotor.Direction.FORWARD);
    shooterMotor.setDirection(DcMotor.Direction.FORWARD);

    //drive code

    double leftPower = deadband(gamepad1.left_stick_y) + deadband(gamepad1.right_stick_x);
    double rightPower = deadband(gamepad1.left_stick_y) - deadband(gamepad1.right_stick_x);
    double motorLimit = .8;

    frontLeft.setPower(leftPower*motorLimit);
    backLeft.setPower(leftPower*motorLimit);
    frontRight.setPower(rightPower*motorLimit);
    backRight.setPower(rightPower*motorLimit);

    //mechanisms controls
    /* current controls :  
        gamepad a runs intake (backwards)
        gamepad left trigger will run shooter motor*
        gamepad right trigger will run belt motor*
            *both triggers are pressure sensitive so there is some range of speeds you can go at !! 
    */
    if(gamepad1.a) {
        intakeMotor.setPower(-1);
    } else {
        intakeMotor.setPower(0);
    }

    shooterMotor.setPower(deadband(gamepad1.left_trigger));
    beltMotor.setPower(deadband(gamepad1.right_trigger));
    
    }

    /**
     * This method takes in input, the number you're getting from joysticks,
     * and a range you want to make dead on the joysticks. If the joystick 
     * input is in the range, the method will return 0. Otherwise, it will
     * return the current value of the joystick. 
     */
    public double deadband(double input) { 
        double range = .02;
        if(input >= 0 && input <= range) { //if input is positive and smaller than range
            return 0;
        } else if(input < 0 && input >= -range) { // or if input is negative and greater than negative range
            return 0;
        } else { //if netiher of these, just return the initial value
            return input;
        }
    }
}