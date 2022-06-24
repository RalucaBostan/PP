from typing import Iterator
import sys
sys.setrecursionlimit(3000)

def pfactorsr(x: int) -> Iterator[int]:
    def factor_n(x : int, n: int) -> Iterator[int]:
        if n * n > x:
            yield x
            return
        if x % n == 0:
            yield n
            if x // n > 1:
                yield from factor_n(x // n ,n)
        else:
            yield from factor_n(x,n+2)
    if x % 2 == 0 :
        yield 2
        if x // 2 > 1:
            yield from pfactorsr(x // 2)
            return

    yield from factor_n(x,3)

print(list(pfactorsr(1455560)))