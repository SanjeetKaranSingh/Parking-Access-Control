from gpiozero import DistanceSensor
from ModelController import ModelController
from MotorController import MotorController
from gpiozero import LED
import time
class GateOperation:
    def __init__(self, MyModelController, MotorController):
        self.modelcontroller = MyModelController
        self.start = False
        print("init")
        self.sensor = DistanceSensor(echo = 21,trigger=20)
        self.MotorController = MotorController
        self.greenled = LED(24)
        self.redled = LED(27)
    def GiveAccess(self):
      #  print("inside give access fun")
        #self.employee = emp
        self.start = True
        print("start changed to"+str(self.start))
    def NotGiveAccess(self):
        self.start == False
    
    def DistanceSensorCheckDistance(self):
        ISObjArrived = 0
        objectleft = 0
        print(self.sensor.distance*10)
        while True:
            if self.start == True:
                print(self.sensor.distance*10)
                if ISObjArrived == 0 and self.sensor.distance*10 < 2:
                    ISObjArrived = 1
                    self.MotorController.openGate()
                    self.greenled.on()
                    #code to open door
                if ISObjArrived == 1:
                    if self.sensor.distance*10 > 2:
                        time.sleep(4)
                        self.MotorController.closeGate()
                        self.modelcontroller.Onevehicalexited()
                        objectleft = 1
                        ISObjArrived = 0
                     #   time.sleep(2)
                        self.start = True
                      #  print(str(self.start))
            else:
                self.redled.on()
                #print("inside second thread")
