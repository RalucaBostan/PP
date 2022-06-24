import math
import time
import tkinter as tk
from Popup import *
from CeasOp import *

class Ceas(tk.Frame):
    def __init__(self, master=None):
        self.w = 500
        self.h = 500
        super().__init__(master)
        self.master = master
        self.switch = 0
        self.alarm = 0
        self.cron = 0
        self.alarmdisplayed = 0
        self.cronos = [0,0,0]
        self.ceas = tk.Canvas(self, width = self.w, height = self.h)
        self.ceas.place(x = 0, y = 0)
        self.init_window()

    def init_window(self):
        self.master.title("Ceas")
        self.pack(fill=tk.BOTH, expand=1)
        self.changeHour = tk.Button(self,text="Schimba Ora",command=self.schimba_ora)
        self.changeHour.place(x=510,y=50)
        self.setAlarm = tk.Button(self,text="Setati Alarma",command=self.setare_alarma)
        self.setAlarm.place(x=510,y=220)
        self.cronometru = tk.Button(self,text="Cronometru START/STOP",command=self.cronometter)
        self.cronometru.place(x=510,y=430)

        self.inputTime = [tk.Text(self,width=15,height=1), tk.Text(self,width=15,height=1), tk.Text(self,width=15,height=1)]
        self.inputAlarm = [tk.Text(self,width=15,height=1), tk.Text(self,width=15,height=1), tk.Text(self,width=15,height=1)]

        self.DrawInputs()

        self.UpdateClock()

    def UpdateClock(self):
        self.ceas.create_rectangle(0, 0, self.w, self.h, fill="lavender")
        if self.switch == 0:
            self.ora=time.localtime().tm_hour
            self.min=time.localtime().tm_min
            self.sec=time.localtime().tm_sec
        else:
            self.sec = self.sec + 1
        self.DeseneazaCeas(self.w, self.h, self.ora, self.min, self.sec)
        self.after(1000, self.UpdateClock)

    def DeseneazaCeas(self, w, h, hh, mm, ss):
        self.ceas.create_oval(10,10,w-10,h-10,fill="ivory3")
        XCentru = w / 2
        YCentru = h / 2
        Raza = (w+h) / 4 - 50
        cifre = (3, 4, 5, 6, 7,  8,  9, 10, 11, 12,  1,  2)
        for i in range(12):

            xNr = XCentru + Raza * math.cos(CeasOp.ToRadiani(self,30 * i))
            yNr = YCentru + Raza * math.sin(CeasOp.ToRadiani(self,30 * i))
            xPt = XCentru + (Raza + 30) * math.cos(CeasOp.ToRadiani(self,30 * i))
            yPt = YCentru + (Raza + 30) * math.sin(CeasOp.ToRadiani(self,30 * i))
            self.ceas.create_text(xNr, yNr, fill="RoyalBlue3", text=str(cifre[i]), font = 'Helvetica 18 bold')
            self.ceas.create_oval(xPt - 5, yPt - 5, xPt + 5, yPt + 5, fill = "RoyalBlue4")

        grad_minut = 360 / ( 5 * 12)
        Rinterior = (w+h) / 4 - 30
        Rexterior = (w+h) / 4 - 20

        for i in range(60):
            if i > 0 and i % 5 != 0:
                x1 = XCentru + Rinterior * math.cos(CeasOp.ToRadiani(self,grad_minut * i))
                y1 = YCentru + Rinterior * math.sin(CeasOp.ToRadiani(self,grad_minut * i))
                x2 = XCentru + Rexterior * math.cos(CeasOp.ToRadiani(self,grad_minut * i))
                y2 = YCentru + Rexterior * math.sin(CeasOp.ToRadiani(self,grad_minut * i))
                self.ceas.create_line(x1,y1,x2,y2, fill="royal blue", width = 3)

        self.ceas.create_oval(w / 2 - 10, h / 2 - 10, w / 2 + 10, h / 2 + 10, fill="RoyalBlue1")
        if self.alarm == 1:
            difference = CeasOp.diferentaTimp(self,[self.ora,self.hourAlarm],[self.min,self.minAlarm],[self.sec,self.secAlarm])
            if difference[0] != 0 or difference[1] != 0 or difference[2] != 0:
                self.ceas.create_text(self.w / 2, self.h / 2 + 70, fill = "DeepSkyBlue4", text="Alarma in %d:%d:%d"%(difference[0],difference[1],difference[2]), font = "Times 20 italic bold")
            elif difference[0] == 0 and difference[1] == 0 and difference[2] == 0:
                self.alarm = 2
        elif self.alarm == 2:
            self.alarmdisplayed += 1
            if self.alarmdisplayed == 1:
                popupmsg("Alarmaaa!!")
            #self.ceas.create_text(self.w / 2, self.h / 2 + 70, fill = "red", text="ALARMAA !! WAKE UP !!", font = "Times 20 italic bold")
        if self.cron == 1:
            if self.cronos[2] < 60:
                self.cronos[2] = self.cronos[2] + 1
            else:
                self.cronos[2] = 0
                self.cronos[1] = self.cronos[1] + 1
            if self.cronos[1] >= 60:
                self.cronos[1] = 0
                self.cronos[0] = self.cronos[0] + 1
            self.ceas.create_text(self.w / 2, self.h / 2 + 100, fill = "DeepSkyBlue4",text = "%d : %d : %d"%(self.cronos[0],self.cronos[1],self.cronos[2]),font = "Times 20 italic bold")
        self.DeseneazaAc(1, hh, mm)
        self.DeseneazaAc(2, mm, ss)
        self.DeseneazaAc(3, ss)

    def DeseneazaAc(self, tip, indicator, indicator2 = 0):
        grad_minut = 360 / (5 * 12)
        x1 = self.w / 2
        y1 = self.h / 2
        if tip == 1:
            unghi = CeasOp.ToRadiani(self,((indicator % 12) * 5 * grad_minut + indicator2 * (30 / 60) + 270) % 360)
            scale = 8
            x2 = self.w / 2 + ((self.w + self.h) / scale) * math.cos(unghi)
            y2 = self.h / 2 + ((self.w + self.h) / scale) * math.sin(unghi)
            self.ceas.create_line(x1, y1, x2, y2, fill="black", width = 7)
        elif tip == 2:
            unghi = CeasOp.ToRadiani(self,((indicator % 60) * grad_minut + indicator2 * (6 / 60) + 270) % 360)
            scale = 6
            x2 = self.w / 2 + ((self.w + self.h) / scale) * math.cos(unghi)
            y2 = self.h / 2 + ((self.w + self.h) / scale) * math.sin(unghi)
            self.ceas.create_line(x1, y1, x2, y2, fill="gray25", width = 3)
        elif tip == 3:
            unghi = CeasOp.ToRadiani(self,((indicator % 60) * grad_minut + 270) % 360)
            scale = 5.5
            x2 = self.w / 2 + ((self.w + self.h) / scale) * math.cos(unghi)
            y2 = self.h / 2 + ((self.w + self.h) / scale) * math.sin(unghi)
            self.ceas.create_line(x1, y1, x2, y2, fill="gray32")

    def schimba_ora(self):
        self.switch = not self.switch
        self.ceas.delete("all")
        self.ora = int(self.inputTime[0].get("1.0", tk.END),10)
        self.min = int(self.inputTime[1].get("1.0", tk.END),10)
        self.sec = int(self.inputTime[2].get("1.0", tk.END),10)
        self.DeseneazaCeas(self.w, self.h, self.ora, self.min, self.sec)

    def DrawInputs(self):
        textHour = [tk.Label(self, text="Ora", width=10, height=1),tk.Label(self, text="Ora", width=10, height=1)]
        textHour[0].place(x=510, y=100)
        textHour[1].place(x=510,y=270)
        textMin = [tk.Label(self, text="Minute", width=10, height=1),tk.Label(self, text="Minute", width=10, height=1)]
        textMin[0].place(x=510, y=130)
        textMin[1].place(x=510,y=300)
        textSec = [tk.Label(self, text="Secunde", width=10, height=1), tk.Label(self, text="Secunde", width=10, height=1)]
        textSec[0].place(x=510, y=160)
        textSec[1].place(x=510,y=330)

        self.inputTime[0].place(x=600, y=100)
        self.inputTime[1].place(x=600, y=130)
        self.inputTime[2].place(x=600, y=160)

        self.inputAlarm[0].place(x=600, y=270)
        self.inputAlarm[1].place(x=600, y=300)
        self.inputAlarm[2].place(x=600, y=330)

    def setare_alarma(self):
        self.hourAlarm = int(self.inputAlarm[0].get("1.0",tk.END),10)
        self.minAlarm = int(self.inputAlarm[1].get("1.0",tk.END),10)
        self.secAlarm = int(self.inputAlarm[2].get("1.0",tk.END),10)
        formatAlarma = "Alarma : %d:%d:%d" % (self.hourAlarm,self.minAlarm,self.secAlarm)
        print(formatAlarma)
        Alarm = tk.Label(self,text=formatAlarma,width=15,height=1,font = 'Helvetica 18 bold')
        Alarm.place(x=510,y=370)
        self.alarm = 1

    def cronometter(self):
        self.cronos = [0,0,0]
        self.cron = not self.cron


root = tk.Tk()
root.geometry("800x550")
app = Ceas(root)
root.mainloop()