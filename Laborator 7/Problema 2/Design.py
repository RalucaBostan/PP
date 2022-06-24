import tkinter as tk
import os
from Elements import *

class Design(tk.Frame):
    def __init__(self,master=None):
        self.w = 300
        self.h = 300
        super().__init__(master)
        self.master = master
        self.init_window()

    def init_window(self):
        self.master.title("Web Analyzer")
        self.PageTitle = tk.Label(self,text="Web Analyzer",width=20,height=5,font='Helvetica 20 bold')
        self.LinkLabel = tk.Label(self,text="Link",width=5,height=1,font='Helvetica 15 bold')
        self.LinkInput = tk.Text(self,width=32,height=1)
        self.GenerateButton = tk.Button(self,text="Generare",width=10,height=1,font='Helvetica 15 bold',command=self.GenerateLink)
        self.ExtraButtons = [tk.Button(self,text="Deschidere fisier cu imagini",width=23,height=1,font='Helvetica 12 bold',command=self.OpenImages),
                             tk.Button(self,text="Deschidere fisier cu pagini",width=23,height=1,font='Helvetica 12 bold',command=self.OpenLinks),
                             tk.Button(self,text="Deschidere fisier cu documente",width=25,height=1,font='Helvetica 12 bold',command=self.OpenDocs)]
        self.Draw()

    def Draw(self):
        self.pack(fill=tk.BOTH,expand=1)
        self.PageTitle.pack()
        self.LinkLabel.place(x=110,y=120)
        self.LinkInput.place(x=20,y=155)
        self.GenerateButton.place(x=80,y=200)
        self.ExtraButtons[0].place(x=30,y=255)
        self.ExtraButtons[1].place(x=30,y=305)
        self.ExtraButtons[2].place(x=20,y=355)

    def GenerateLink(self):
        text = self.LinkInput.get("1.0",tk.END)
        Execute = LinkOperations(text)
        Execute.Execute()
        links = Links(text,"Links.txt")
        links.WriteFile()
        images = Images(text,"Images.txt")
        images.WriteFile()
        docs = Docs(text,"Docs.txt")
        docs.WriteFile()

    def OpenLinks(self):
        os.system("start " + "Links.txt")
    def OpenImages(self):
        os.system("start " + "Images.txt")
    def OpenDocs(self):
        os.system("start " + "Docs.txt")

root = tk.Tk()
root.geometry("300x400")
designInstance = Design(root)
root.mainloop()
