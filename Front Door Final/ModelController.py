from sqlcontroller import sqlcontroller
from datetime import datetime
from EntryStruct import EntryStruct
class ModelController:
    def __init__(self):
        self.SQLcontroller = sqlcontroller()
    def shouldAllowAccess(self, idnumber):
        print(self.SQLcontroller.ParkingAvailability())
       #add if to check if entry is employee
        if self.SQLcontroller.ParkingAvailability() == 1:
            print("in model controller")
            print("was above")
            if self.SQLcontroller.GetEmployeeWithFogID(idnumber) != None:
                #if parking is available for All employees that are registered
                if self.SQLcontroller.IsspaceAvailable():
                    return self.SQLcontroller.GetEmployeeWithFogID(idnumber), True
        return None, False


    def AccessGiven(self, empobj):
        self.SQLcontroller.VehicalAddedIncCounter()
        self.SQLcontroller.AddEntryObject(EntryStruct(empobj, datetime.now()))
