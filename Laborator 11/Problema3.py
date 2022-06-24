import threading
import random

class Multiply(threading.Thread):
    def __init__(self,array,alpha):
        super().__init__()
        self.array = array
        self.alpha = alpha

    def run(self):
        for i in range(len(self.array)-1):
            self.array[i] *= self.alpha
        return


class Sort(threading.Thread):
    def __init__(self,array):
        super().__init__()
        self.array = array

    def run(self):
        self.array.sort()
        return


class Display(threading.Thread):
    def __init__(self,array):
        super().__init__()
        self.array = array

    def run(self):
        for element in self.array:
            print(element)

def ArrayGenerator(n):
    array = []
    for i in range(n):
        array.append(random.randint(1,100000))
    return array

if __name__ == '__main__':
    array = ArrayGenerator(100000)
    multiplyThread = Multiply(array,4)
    sortThread = Sort(array)
    displayThread = Display(array)
    pipeline = [multiplyThread,sortThread,displayThread]
    for thread in pipeline:
        thread.start()
        thread.join()
