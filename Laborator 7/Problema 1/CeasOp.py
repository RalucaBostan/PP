import math
class CeasOp:
    @staticmethod
    def diferentaTimp(self,h,m,s):
        difference = [0] * 3
        difference[0] = h[1]
        difference[1] = m[1]
        difference[2] = s[1]
        difference[2] = difference[2] - s[0]
        if difference[2] < 0:
            difference[2] = 60 + difference[2]
            difference[1] = difference[1] - 1
        difference[1] = difference[1] - m[0]
        if difference[1] < 0:
            difference[1] = 60 + difference[1]
            difference[0] = difference[0] - 1
        difference[0] = difference[0] - h[0]
        if difference[0] < 0:
            difference[0] = 24 + difference[0]
        return difference

    @staticmethod
    def ToRadiani(self, unghi):
        return unghi * math.pi / 180