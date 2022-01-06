#!/home/pi/software/bin/python3
import sys
import RPi.GPIO as GPIO
from threading import Thread
import time
from gpiozero import LED
sys.path.insert(1, '/home/pi/tpj/Main')
from MotorController import MotorController
from buzzerController import BuzzerController
import cgi, cgitb
from typing import Pattern
import urllib.request
import re;
from datetime import date, datetime
cgitb.enable()  # enabled for CGI script troubleshooting
                 # script langauge/runtime errors are displayed and sent back to
                 # the browser

# create instance of FieldStorage to process CGI form values
form = cgi.FieldStorage()
IsNightModeOn = (form.getvalue('IsNightModeOn'))
isEmergency = (form.getvalue('isEmergency'))
print("Content-type: text/html\n\n")
#pins
led1 = 16
led2 = 20
led3 = 21
led4 = 27
led5 = 23
led6 = 12
# set pins up:

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(led1, GPIO.OUT)
GPIO.setup(led2, GPIO.OUT)
GPIO.setup(led3, GPIO.OUT)
GPIO.setup(led4, GPIO.OUT)
GPIO.setup(led5, GPIO.OUT)
GPIO.setup(led6, GPIO.OUT)
print(IsNightModeOn)
if isEmergency == "emerON":
    fh = open("/home/pi/tpj/Main/shouldLedBlink.txt", 'w', encoding = 'utf-8')
    fh.write("1")
    motorcontrollerhelper = MotorController()
#    motorcontrollerhelper = MotorController()
    motorcontrollerhelper.openGate()
    print("inside isemergency on")
    #code to turn on motor
    fh.close()

if isEmergency == "emerOFF":
    fh = open("/home/pi/tpj/Main/shouldLedBlink.txt", 'w', encoding = 'utf-8')
    fh.write("0")
    print("inside isemergency off")
    motorcontrollerhelper = MotorController()
    print("after close")
    fh.close()
    
#Code for LEDs
if IsNightModeOn == "True":
    fh1 = open("/home/pi/tpj/Main/isNightModeON.txt", 'w', encoding = 'utf-8')
    fh1.write("1")
    print("inside true")
    GPIO.output(led1, GPIO.HIGH)
    GPIO.output(led2, GPIO.HIGH)
    GPIO.output(led3, GPIO.HIGH)
    GPIO.output(led4, GPIO.HIGH)
    GPIO.output(led5, GPIO.HIGH)
    GPIO.output(led6, GPIO.HIGH)
    fh1.close()
    #what if we said turn lights on
if IsNightModeOn == "False":
    fh1 = open("/home/pi/tpj/Main/isNightModeON.txt", 'w', encoding = 'utf-8')
    fh1.write("0")
    print("inside false")
    GPIO.output(led1, GPIO.LOW)
    GPIO.output(led2, GPIO.LOW)
    GPIO.output(led3, GPIO.LOW)
    GPIO.output(led4, GPIO.LOW)
    GPIO.output(led5, GPIO.LOW)
    GPIO.output(led6, GPIO.LOW)
    GPIO.cleanup()
    fh1.close()

    #motorcontrollerhelper.closeGate()
    
    #code to turn off th motor
