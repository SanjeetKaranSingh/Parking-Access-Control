#!/usr/bin/python
from sqlcontroller import sqlcontroller
from datetime import datetime

class ModelController:
    def __init__(self):
        self.SQLcontroller = sqlcontroller()
    def Onevehicalexited(self):
        self.SQLcontroller.decreaceOneVehical()
