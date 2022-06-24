import tkinter as tk
NORM_FONT = ("Helvetica",10)

def popupmsg(msg):
	popup = tk.Tk()
	popup.wm_title("Alarm!")
	label = tk.Label(popup,text=msg,font=NORM_FONT)
	label.pack(side="top",fill="x",pady=10)
	B1 = tk.Button(popup,text="Okay",command=popup.destroy)
	B1.pack()

