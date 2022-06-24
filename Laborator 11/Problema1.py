import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
import random

def countdown(list):
    i = len(list) - 1
    while list[i] != 0 and i > 0 :
        i -= 1

def ver_1(list):
    thread_1 = threading.Thread(target=countdown(list))
    thread_2 = threading.Thread(target=countdown(list))
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()

def ver_2(list):
    countdown(list)
    countdown(list)

def ver_3(list):
    process_1 = multiprocessing.Process(target=countdown(list))
    process_2 = multiprocessing.Process(target=countdown(list))
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()

def ver_4(list):
    with ThreadPoolExecutor(max_workers = 2) as executor:
        future = executor.submit(countdown(list))
        future = executor.submit(countdown(list))

def generate_list(n):
    list = []
    for i in range(n):
        list.append(random.randint(1,100))
    return list

if __name__ == '__main__':
    list = generate_list(5000000)
    start = time.time()
    ver_1(list)
    end = time.time()
    print("Timp de executie pseudoparalelism cu GIL : ",end = "")
    print(end-start)

    start = time.time()
    ver_2(list)
    end = time.time()
    print("Timp de executie secvential : ",end = "")
    print(end-start)

    start = time.time()
    ver_3(list)
    end = time.time()
    print("Timp de executie paralela cu multiprocessing : ",end = "")
    print(end-start)

    start = time.time()
    ver_4(list)
    end = time.time()
    print("Timp de executie paralela cu concurrent.futures : ",end = "")
    print(end-start)

