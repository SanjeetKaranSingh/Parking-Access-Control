from gpiozero import DistanceSensor
from Employee import Employee
from ModelController import ModelController
from MotorController import MotorController
from gpiozero import LED
import time
class GateOperation:
    def __init__(self, MyModelController, MotorController,redled):
        self.modelcontroller = MyModelController
        self.start = False
        self.sensor = DistanceSensor(echo = 24,trigger=18)
        self.MotorController = MotorController
        self.greenled = LED(17)
        self.redled = redled
    def GiveAccess(self, emp):
      #  print("inside give access fun")
        self.employee = emp
        self.start = True
        print("start changed to"+str(self.start))
    def NotGiveAccess(self):
        self.start == False
    
    def DistanceSensorCheckDistance(self):
        ISObjArrived = 0
        objectleft = 0
        while True:
            if self.start == True:
                print(self.sensor.distance*10)
                if ISObjArrived == 0 and self.sensor.distance*10 < 2:
                    ISObjArrived = 1
                    self.MotorController.openGate()
                    self.redled.off()
                    self.greenled.on()
                    #code to open door
                if ISObjArrived == 1:
                    if self.sensor.distance*10 > 2:
                        time.sleep(2)
                        self.greenled.off()
                        self.redled.on()                        
                        self.MotorController.closeGate()
                        self.modelcontroller.AccessGiven(self.employee)
                        objectleft = 1
                        ISObjArrived = 0
                        self.start = False
                        print(str(self.start))
            else:
                self.redled.on()
                #print("inside second thread")
