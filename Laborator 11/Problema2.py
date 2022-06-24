from threading import Thread,Condition
import time


class Consumator(Thread):
    def __init__(self,conditie,elemente):
        Thread.__init__(self)
        self.conditie = conditie
        self.elemente = elemente
    def consumator(self):
        self.conditie.acquire()
        if len(self.elemente) == 0:
            self.conditie.wait()
            print("mesaj de la consumator : nu am nimic disponibil")
        self.elemente.pop()
        print("mesaj de la consumator : am utilizat un element")
        print("mesaj de la consumator : mai am disponibil ",len(self.elemente)," elemente")
        self.conditie.notify()
        self.conditie.release()
    def run(self):
        for i in range(5):
            self.consumator()

class Producator(Thread):
    def __init__(self,conditie,elemente):
        Thread.__init__(self)
        self.conditie = conditie
        self.elemente = elemente
    def producator(self):
        self.conditie.acquire()
        if len(self.elemente) == 10:
            self.conditie.wait()
            print("mesaj de la producator : am disponibile ",len(self.elemente)," elemente")
            print("mesaj de la producator : am oprit productia")
        self.elemente.append(1)
        print("mesaj de la producator : am produs ",len(self.elemente)," elemente")
        self.conditie.notify()
        self.conditie.release()
    def run(self):
        for i in range(5):
            self.producator()

conditie = Condition()
elemente = []
producator = Producator(conditie,elemente)
consumator = Consumator(conditie,elemente)
producator.start()
consumator.start()
producator.join()
consumator.join()