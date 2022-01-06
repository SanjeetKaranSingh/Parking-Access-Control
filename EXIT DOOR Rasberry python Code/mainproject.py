#!/usr/bin/python
import pyodbc
#import RPi.GPIO as GPIO
from sqlcontroller import sqlcontroller
from threading import Thread
from gpiozero import LED
from GateOperation import GateOperation
from ModelController import ModelController
from MotorController import MotorController

# greenled = LED(17)
# redled = LED(4)


def CheckRFID(modelcontroller,gateOpHelper):
    print("before reader inside thread one")
    id, text = reader.read()
    print(id)
    print("after reader")
    # gateOpHelper.GiveAccess(emp)

    # if shouldGiveAccess:
    #  #   greenled.on()
    #  #   redled.off()
    #     print("inside")
    #     gateOpHelper.GiveAccess(emp)
    # else:
    #     print("out")
    #   #  greenled.off()
    #   #  redled.on()

def main():
    MyModelController = ModelController()
    motorcontrollerhelper = MotorController()
    gateOpHelper = GateOperation(MyModelController, motorcontrollerhelper)
    distancesensorThread = Thread(target=gateOpHelper.DistanceSensorCheckDistance)
    gateOpHelper.GiveAccess()
    print("distance is ")
    distancesensorThread.start()
    
main()