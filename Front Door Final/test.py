from buzzerController import BuzzerController
import cgi, cgitb

import RPi.GPIO as GPIO
myobj = BuzzerController()
myobj.startBeeping()