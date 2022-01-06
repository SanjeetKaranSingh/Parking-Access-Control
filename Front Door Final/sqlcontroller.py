from sqlconnection import sqlconnection
from Employee import Employee
from datetime import datetime

# Current date time in local system
print(datetime.now())
class sqlcontroller:
     
     def __init__(self):
         print('object created')
         self.Controller = sqlconnection()
         self.conn = self.Controller.getSQLConnection()
     
     def ParkingAvailability(self):
         #0 if parking is avaiable for public
         #1 if parking is open for all employees
         #2 if parking is availble for selected employees
         self.conn = self.Controller.getSQLConnection()
         cursor = self.conn.cursor()
         value = int(cursor.execute("select ParkingAvailability from dbo.InstructionFlags").fetchval())
         self.conn.commit()
         return value
         
     def IsspaceAvailable(self):
         self.conn = self.Controller.getSQLConnection()
         cursor = self.conn.cursor()
         ParkingLimit = int(cursor.execute("select ParkingLimit from dbo.InstructionFlags").fetchval())
         currentVehicalCounter = int(cursor.execute("select VehicalCounter from dbo.InstructionFlags").fetchval())
         self.conn.commit()
         return currentVehicalCounter < ParkingLimit
        #See if there is space available in parking, if yes return true else no

     def showAllEmployees(self):
        
        cursor = self.conn.cursor()
        cursor.execute("SELECT * from EmployeeDS")
        for row in cursor.fetchall():
            print(row)
     
     def GetEmployeeWithFogID(self, id1):
         #Get object from sql 
         cursor = self.conn.cursor()

         statement = "select Username from dbo.EmployeeDT where FogNo = " + str(id1)
         print("statement = " + statement)
         name = (cursor.execute(statement).fetchval())
         if(name != None):
             return Employee(id1, name)
         else:
             return None
     def VehicalAddedIncCounter(self):
         self.conn = self.Controller.getSQLConnection()
         cursor = self.conn.cursor()
         cursor.execute("update dbo.InstructionFlags SET VehicalCounter = VehicalCounter + 1")
         self.conn.commit()
         #Sql statement to increase number of vehicals in parking
     def AddEntryObject(self, obj):
         print("Entry object")
         self.conn = self.Controller.getSQLConnection()
         cursor = self.conn.cursor()
         print(str(obj.FogId))
         print(str(datetime.now()))
         cursor.execute("insert into EntryTable (EmployeeID,TimeValue) VALUES (?,?)",(obj.FogId,datetime.now().strftime('%Y/%m/%d %I:%M:%S')))
         self.conn.commit()
        #Sql statement to add employee entry
     def __del__(self):
         print('Destructor called, Employee deleted.')
         #calling destructor to close sql connection
         if self.conn != None:
             self.conn.close()
         else:
             print("error in connection")
     