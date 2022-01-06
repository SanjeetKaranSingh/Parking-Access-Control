from sqlconnection import sqlconnection

class sqlcontroller:   
     def __init__(self):
         self.Controller = sqlconnection()
         self.conn = self.Controller.getSQLConnection()
     def decreaceOneVehical(self):
         self.conn = self.Controller.getSQLConnection()
         cursor = self.conn.cursor()
         print("About to happen connection")
         cursor.execute("update dbo.InstructionFlags SET VehicalCounter = VehicalCounter - 1 WHERE VehicalCounter != 0")
         print("one decreased")
         self.conn.commit()
     def __del__(self):
         #calling destructor to close sql connection
         self.conn.close() 
     