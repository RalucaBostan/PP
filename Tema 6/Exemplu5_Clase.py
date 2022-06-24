import os

class GenericFile:
    def __init__(self,path):
        pass

    def GetPath(self):
        pass

    def GetFreq(self):
        pass

class TextASCII(GenericFile):
    def __init__(self,Path):
        self.PathAbsolut = Path
        self.Frecvente = 0

    def GetPath(self):
        return self.PathAbsolut

    def GetFreq(self):
        file = open(self.PathAbsolut,'rb')
        try:
            content = file.read()
        finally:
            file.close()

        frecvente = [0] * 256
        for ch in content:
            frecvente[ch] += 1

        frecventeMari = [frecvente[val] for val in [9,10,13,32]]
        for val in range(33,127):
            frecventeMari.append(frecvente[val])

        frecventeMici = [frecvente[val] for val in [11,12,14]]
        for val in range(0,8):
            frecventeMici.append(frecvente[val])
        for val in range(15,31):
            frecventeMici.append(frecvente[val])
        for val in range(128,255):
            frecventeMici.append(frecvente[val])

        sumaFrecventeMari = 0
        sumaFrecventeMici = 0
        for fr in frecventeMari:
            sumaFrecventeMari += fr
        for fr in frecventeMici:
            sumaFrecventeMici += fr

        if sumaFrecventeMari > sumaFrecventeMici:
            self.Frecvente = sumaFrecventeMari
        return self.Frecvente

class TextUNICODE(GenericFile):
    def __init__(self,Path):
        self.PathAbsolut = Path
        self.Frecvente = 0

    def GetPath(self):
        return self.PathAbsolut

    def GetFreq(self):
        file = open(self.PathAbsolut,'rb')
        try:
            content = file.read()
        finally:
            file.close()

        frecventaTotal = 0
        frecventaZero = 0
        for ch in content:
            if ch == 0:
                frecventaZero += 1
            frecventaTotal += 1

        if frecventaZero > frecventaTotal * (30/100):
            self.Frecvente = frecventaZero
        return self.Frecvente

class Binary(GenericFile):
    def __init__(self,Path):
        self.PathAbsolut = Path
        self.Frecvente = 0

    def GetPath(self):
        return self.PathAbsolut

    def GetFreq(self):
        file = open(self.PathAbsolut,'rb')
        try:
            content = file.read()
        finally:
            file.close()

        frecvente = [0] * 256
        for ch in content:
            frecvente[ch] += 1

        count = 0
        for fq in frecvente:
            if fq != 0:
                count += 1
        if 255 - count < 100:
            self.Frecvente = count

        return self.Frecvente

class XMLFile(TextASCII):
    def __init__(self,Path,firstTag=None):
        super().__init__(Path)
        self.FirstTag = firstTag

    def GetFirstTag(self):
        return self.FirstTag

    def GetFreq(self):
        if(super().GetFreq() != 0):
            file = open(self.PathAbsolut,'rb')
            try:
                content = file.read()
            finally:
                file.close()
            frequencyopen = 0
            frequencyclose = 0
            for ch in content:
                ch = chr(ch)
                if ch == '<':
                    frequencyopen = frequencyopen + 1
                elif ch == '>':
                    frequencyclose = frequencyclose + 1
            if frequencyopen == frequencyclose:
                return frequencyopen

class BMP(Binary):
    def __init__(self,Path=None,width=None,height=None,bpp=None):
        super().__init__(Path)
        self.Width = width
        self.Height = height
        self.BPP = bpp

    def ShowInfo(self):
        print("BMP : Width [{}], Height [{}], BPP [{}]".format(self.Width,self.Height,self.BPP))
		



