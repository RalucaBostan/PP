import threading
import multiprocessing
import asyncio
from concurrent.futures import ThreadPoolExecutor

class Semafor:
    instanta = None
    def Enter(self):
        if Semafor.instanta == None:
            Semafor.instanta = self

    def Green(self):
        if Semafor.instanta == None:
            return True
        return False

    def Exit(self):
        Semafor.instanta = None

class Logger:
    def __init__(self,filename):
        self.filename = filename

    def Open(self):
        self.file = open(self.filename,"w")

    def Reset(self):
        self.file.seek(0) #pozitioneaza cursorul pe primul caracter
        self.file.truncate() #elimina continutul

    def Write(self,text):
        self.file.write(text)


class ProducerThread(threading.Thread):
    def __init__(self,log,name):
        super().__init__()
        self.log = log
        self.name = name

    def run(self):
        if(Semafor().Green()):
            Semafor().Enter()
            for i in range(100):
                for log in self.log:
                    log.Write("Thread [{}]. I am writing number {}.\n".format(self.name,i))
            Semafor().Exit()

@asyncio.coroutine
def ProducerCoroutine(log,name):
    if(Semafor().Green()):
        Semafor().Enter()
        for i in range(100):
            for logg in log:
                logg.Write("Thread [{}]. I am writing number {}.\n".format(name,i))
        Semafor().Exit()

class ProducerProcess(multiprocessing.Process):
    def __init__(self,log,nume):
        super().__init__()
        self.log = log
        self.nume = nume

    def run(self):
        if(Semafor().Green()):
            Semafor().Enter()
            for i in range(100):
                for log in self.log:
                    log.Write("Process [{}]. I am writing number {}.\n".format(self.nume,i))
            Semafor().Exit()
        return

def ProducerThreadPool(log,text):
    if(Semafor().Green()):
        Semafor().Enter()
        for i in range(100):
            for logg in log:
                logg.Write("Thread [{}] from Pool. I am writing number {}.\n".format(text,i))
        Semafor().Exit()


if __name__ == '__main__':

    threadlogs = [Logger("ThreadLogger1.txt"), Logger("ThreadLogger2.txt"),Logger("ThreadLogger3.txt")]
    for log in threadlogs:
        log.Open()
        log.Reset()
    threadproducers = [ProducerThread(threadlogs,i) for i in range(10)]
    for thread in threadproducers:
        thread.start()
        thread.join()

    processlogs = [Logger("ProcessLogger1.txt"), Logger("ProcessLogger2.txt"),Logger("ProcessLogger3.txt")]
    for log in processlogs:
        log.Open()
        log.Reset()
    jobs = []
    for i in range(5):
        process = ProducerProcess(processlogs,i)
        jobs.append(process)
        process.start()
        process.join()


    coroutinelogs = [Logger("CoroutineLogger1.txt"),Logger("CoroutineLogger2.txt"),Logger("CoroutineLogger3.txt")]
    for log in coroutinelogs:
        log.Open()
        log.Reset()
    tasks = [asyncio.Task(ProducerCoroutine(coroutinelogs,i)) for i in range(5)]
    loop = asyncio.get_event_loop()
    loop.run_until_complete(asyncio.wait(tasks))
    loop.close()


    threadpoollogs = [Logger("ThreadPoolLogger1.txt"),Logger("ThreadPoolLogger2.txt"),Logger("ThreadPoolLogger3.txt")]
    for log in threadpoollogs:
        log.Open()
        log.Reset()
    with ThreadPoolExecutor(max_workers = 5) as executor:
        for i in range(5):
            future = executor.submit(ProducerThreadPool(threadpoollogs,i))
