#!/usr/bin/python
from gpiozero.pins.pigpio import PiGPIOFactory
from gpiozero import Servo
class MotorController:
    def __init__(self):
        self.rvo = Servo(13,pin_factory=PiGPIOFactory())
        self.closeGate()
    def closeGate(self):
        self.rvo.max()
    def openGate(self):
        self.rvo.min()