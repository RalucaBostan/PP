from functools import reduce

lista = [50,71,11,97,54,62,77]

rezultat = min(list(filter(lambda x : (x%2==0),lista))),min(list(map(lambda x : (x*2),lista))),reduce((lambda x,y:x+y),lista)

print("rezultatul unei functii de nivel superior este {}".format(rezultat))

###########################################################################

result = []
result.append(min(element for element in lista if element % 2 == 0))
result.append(min(element*2 for element in lista))
listacopy = 0
for element in lista:
    listacopy += element
result.append(listacopy)
print(result)
