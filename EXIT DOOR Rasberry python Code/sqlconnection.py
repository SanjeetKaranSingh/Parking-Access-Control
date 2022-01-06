import pyodbc
class sqlconnection:
    def __init__(self):
        self.dsn = 'sqlserverdatasource'  #Name of server data source
        self.user = 'sa'       #Username for accessing DataBAse
        self.password = 'access'   #Password for accessing DB
        self.database = 'rasberry'   #DB name
        self.con_string = 'DSN=%s;UID=%s;PWD=%s;DATABASE=%s;' % (self.dsn, self.user, self.password, self.database)
        self.cnxn = pyodbc.connect(self.con_string)
        self.cursor = self.cnxn.cursor()
    def getSQLConnection(self):
        return self.cnxn