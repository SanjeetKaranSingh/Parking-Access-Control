#!/usr/bin/python
from gpiozero.pins.pigpio import PiGPIOFactory
from gpiozero import Servo
#import RPi.GPIO as GPIO
class MotorController:
    def __init__(self):
        #GPIO.setmode(GPIO.BOARD)
        self.rvo = Servo(12, pin_factory=PiGPIOFactory())
        self.closeGate()
    def closeGate(self):
        self.rvo.max()
    def openGate(self):
        self.rvo.min()