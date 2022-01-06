from gpiozero import Buzzer
from gpiozero import LED
import RPi.GPIO as GPIO
from threading import Thread
class BuzzerController:
    __instance = None
    def __init__(self):
            self.blinking = True
            self.bz = Buzzer(21)
            self.start = False
            self.bz.beep()
            distancesensorThread = Thread(target=self.BuzzerWork)
            distancesensorThread.start()
            
    
    def startBeeping(self):
        self.start = True
    def stopBeeping(self):
        self.start = False

    def BuzzerWork(self):
        while(True):
            if self.bz.value == 1:
                if self.start == True:
                   # print(self.bz.value)
                    self.bz.on()
                    #self.led2.blink()
                    #self.greenled.on()
            if self.start == False:
                self.bz.beep()