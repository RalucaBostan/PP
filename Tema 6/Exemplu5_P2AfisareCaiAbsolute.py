import os
from Exemplu5_Clase import *
from Exemplu5_P1CategoriiFisier import *
rootdir = "E:\Tema 6\DirectorFisiere"
FilesPaths = []

def DeterminarePath(string,path):
    if string == "XML ASCII":
        if DeterminareTipFisier(path) == "ASCII/UTF8":
            XML = XMLFile(path)
            if XML.GetFreq() != 0:
                return path
        return 0
    elif string == "UNICODE":
        if DeterminareTipFisier(path) == "UNICODE/UTF16":
            return path
        return 0
    elif string == "BMP":
        if DeterminareTipFisier(path) == "BINARY":
            BMPFile = BMP(path,12,13,10)
            BMPFile.ShowInfo()
            return path
        return 0

###### MAIN

for root, subdirs, files in os.walk(rootdir):
    for file in os.listdir(root):
        filePath = os.path.join(root,file)
        if os.path.isdir(filePath):
            pass
        else:
            FilesPaths.append(filePath)

print("Fisierele de tip XML ASCII \n\t")
for file in FilesPaths:
    val = DeterminarePath('XML ASCII',file)
    if  val != 0 :
        print("\tPATH : {}.\n".format(val))

print("Fisierele de tip UNICODE \n\t")
for file in FilesPaths:
    val = DeterminarePath('UNICODE',file)
    if  val != 0 :
        print("\tPATH : {}.\n".format(val))

print("Fisierele de tip BMP \n\t")
for file in FilesPaths:
    val = DeterminarePath('BMP',file)
    if  val != 0 :
        print("\tPATH : {}.\n".format(val))






