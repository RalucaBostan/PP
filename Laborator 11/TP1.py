import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import asyncio
from collections import deque
import random
import time

def Sum(n):
    i = 0
    s = i
    for index in range(n):
        i += 1
        s += i
    return s

@asyncio.coroutine
def Suma(n):
    print("\tCoroutine : Suma primelor {} numere este {}. ".format(n,Sum(n)))


class FirDeExecutie(threading.Thread):
    def __init__(self,n):
        super().__init__()
        self.n = n
    def run(self):
        print("\t Thread : Suma primelor {} numere este {}. ".format(self.n,Sum(self.n)))

class Proces(multiprocessing.Process):
    def __init__(self,n):
        super().__init__()
        self.n = n
    def run(self):
        print("\tProces : Suma primelor {} numere este {}. ".format(self.n,Sum(self.n)))

class PiscinaDeThreaduri:
    def __init__(self,n,workers):
        super().__init__()
        self.n = n
        self.workers = workers

    def start(self):
        with ThreadPoolExecutor(max_workers=self.workers) as executor:
            print("\tPool : Suma primelor {} numere este {}. ".format(self.n,Sum(self.n)))

class Secvential:
    def __init__(self,n):
        self.n = n
    def start(self):
        print("\tSecvential : Suma primelor {} numere este {}. ".format(self.n,Sum(self.n)))


if __name__ == '__main__':
    coada = deque()
    for i in range(4):
        randomvalue = random.randint(10000,10000000)
        coada.appendleft(randomvalue)

    while coada:
        n = coada.pop()
        print("n = {}".format(n))

        thread = FirDeExecutie(n)
        proces = Proces(n)
        pool = PiscinaDeThreaduri(n,10)
        secvential = Secvential(n)

        start = time.time()
        thread.start()
        thread.join()
        end = time.time()
        print("\t\t[Timp de executie thread : {}]".format(end - start))

        start = time.time()
        proces.start()
        proces.join()
        end = time.time()
        print("\t\t[Timp de executie proces : {}]".format(end - start))

        start = time.time()
        pool.start()
        end = time.time()
        print("\t\t[Timp de executie threadpool : {}]".format(end - start))

        start = time.time()
        secvential.start()
        end = time.time()
        print("\t\t[Timp de executie secvential : {}]".format(end - start))

        start = time.time()
        task = asyncio.Task(Suma(n))
        bucla = asyncio.get_event_loop()
        bucla.run_until_complete(task)
        end = time.time()
        print("\t\t[Timp de executie Coroutine : {}]".format(end - start))
        print("\n")


    tasks = [asyncio.Task(Suma(1000000)),asyncio.Task(Suma(2500000)),asyncio.Task(Suma(5000000)),asyncio.Task(Suma(7500000))]
    start = time.time()
    loop = asyncio.get_event_loop()
    loop.run_until_complete(asyncio.wait(tasks))
    loop.close()
    end = time.time()
    print("\nTimpul necesar pentru calcularea celor 4 sume de catre cele 4 coroutine : {}. ".format(end - start))



'''
n = 9012044
	 Thread : Suma primelor 9012044 numere este 40608473034990. 
		[Timp de executie thread : 2.6895315647125244]
	Proces : Suma primelor 9012044 numere este 40608473034990. 
		[Timp de executie proces : 3.449678897857666]
	Pool : Suma primelor 9012044 numere este 40608473034990. 
		[Timp de executie threadpool : 2.8515710830688477]
	Secvential : Suma primelor 9012044 numere este 40608473034990. 
		[Timp de executie secvential : 2.798764228820801]


n = 8861008
	 Thread : Suma primelor 8861008 numere este 39258735818536. 
		[Timp de executie thread : 2.722769260406494]
	Proces : Suma primelor 8861008 numere este 39258735818536. 
		[Timp de executie proces : 4.118836402893066]
	Pool : Suma primelor 8861008 numere este 39258735818536. 
		[Timp de executie threadpool : 3.122633695602417]
	Secvential : Suma primelor 8861008 numere este 39258735818536. 
		[Timp de executie secvential : 2.729586362838745]


n = 9029921
	 Thread : Suma primelor 9029921 numere este 40769741148081. 
		[Timp de executie thread : 2.9042017459869385]
	Proces : Suma primelor 9029921 numere este 40769741148081. 
		[Timp de executie proces : 3.238158702850342]
	Pool : Suma primelor 9029921 numere este 40769741148081. 
		[Timp de executie threadpool : 2.959592819213867]
	Secvential : Suma primelor 9029921 numere este 40769741148081. 
		[Timp de executie secvential : 3.056450843811035]


n = 4722438
	 Thread : Suma primelor 4722438 numere este 11150712693141. 
		[Timp de executie thread : 1.795341968536377]
	Proces : Suma primelor 4722438 numere este 11150712693141. 
		[Timp de executie proces : 2.247403860092163]
	Pool : Suma primelor 4722438 numere este 11150712693141. 
		[Timp de executie threadpool : 1.7799427509307861]
	Secvential : Suma primelor 4722438 numere este 11150712693141. 
		[Timp de executie secvential : 1.9076600074768066]
'''