#!/home/pi/software/bin/python3
from gpiozero import LED
import RPi.GPIO as GPIO
led1 = 17
GPIO.setmode(GPIO.BCM)
GPIO.setup(4, GPIO.OUT)
GPIO.output(4, GPIO.HIGH)
