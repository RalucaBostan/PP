import os
from Exemplu5_Clase import *

rootdir = "E:\Tema 6\DirectorFisiere"
FilesPaths = []

def DeterminareTipFisier(path):
    textAscii = TextASCII(path).GetFreq()
    textUnicode = TextUNICODE(path).GetFreq()
    binary = Binary(path).GetFreq()
    if textUnicode == 0 and binary == 0:
        return "ASCII/UTF8"
    elif textUnicode > binary:
        return "UNICODE/UTF16"
    else:
        return "BINARY"

##### MAIN


for root, subdirs, files in os.walk(rootdir):
    for file in os.listdir(root):
        filePath = os.path.join(root,file)
        if os.path.isdir(filePath):
            pass
        else:
            FilesPaths.append(filePath)

for file in FilesPaths:
    print(" *****Validare***** \n\tSursa : {}\n\tTip : {}.\n".format(file,DeterminareTipFisier(file)))

