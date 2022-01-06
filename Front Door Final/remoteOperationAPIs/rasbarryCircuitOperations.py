#!/home/pi/software/bin/python3
import sys
import RPi.GPIO as GPIO
from sqlcontroller import sqlcontroller
from threading import Thread
from gpiozero import LED
sys.path.insert(1, '/home/pi/tpj/Main')
from MotorController import MotorController
import cgi, cgitb
from typing import Pattern
import urllib.request
import re;
from datetime import date, datetime
cgitb.enable( )  # enabled for CGI script troubleshooting
                 # script langauge/runtime errors are displayed and sent back to
                 # the browser

# create instance of FieldStorage to process CGI form values
form = cgi.FieldStorage()
IsNightModeOn = (form.getvalue('IsNightModeOn'))
isEmergency = (form.getvalue('isEmergency'))

#Code for LEDs
led1 = gpiozero.LED(17)
led2 = gpiozero.LED(4)

if IsNightModeOn == "True":
    led1.on()
    led2.on()
    #what if we said turn lights on
if IsNightModeOn == "False":
    led1.off()
    led2.off()

motorcontrollerhelper = MotorController()

if isEmergency == "True":
    motorcontrollerhelper.openGate()
    #code to turn on motor

if isEmergency == "False":
    motorcontrollerhelper.closeGate()
    #code to turn off th motor
    