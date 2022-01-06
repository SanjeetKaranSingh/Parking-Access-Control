#!/usr/bin/python
import pyodbc
import RPi.GPIO as GPIO
from sqlcontroller import sqlcontroller
from threading import Thread
from gpiozero import LED
from mfrc522 import SimpleMFRC522
from GateOperation import GateOperation
from ModelController import ModelController
from MotorController import MotorController

reader = SimpleMFRC522()
#greenled = LED(17)
redled = LED(4)


def CheckRFID(modelcontroller,gateOpHelper):
     print("before reader inside thread one")
     print("before reader")
     idnumber, text = reader.read()
     print("after reader")
     print(idnumber)
     print("after reader")
     emp, shouldGiveAccess = modelcontroller.shouldAllowAccess(idnumber)
     if shouldGiveAccess:
     #  #   greenled.on()
     #  #   redled.off()
          print("inside")
          gateOpHelper.GiveAccess(emp)
    #      redled.off()
     else:
     #     redled.on()
          print("out")
        #  greenled.off()
      #    redled.on()

def main():
    try:
        print("test")
        redled.on()
        MyModelController = ModelController()
        motorcontrollerhelper = MotorController()
        gateOpHelper = GateOperation(MyModelController, motorcontrollerhelper,redled)
        distancesensorThread = Thread(target=gateOpHelper.DistanceSensorCheckDistance)
        distancesensorThread.start()
        while True:
     #       requestobj = sqlcontroller()
      #      requestobj.showAllEmployees()
            print("before")
            idnumber, text = reader.read()
            print("pass")
            print("checking erif again")
            CheckRFID(MyModelController,gateOpHelper)
    finally:
        GPIO.cleanup()

main()
