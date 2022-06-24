import collections
hashmap = dict({})
for element in range(1000):
    hashmap.update({element : element})

print("Hashmap initial \n {}".format(hashmap))

odd_condition = lambda x : (x % 2 == 1)

hashmap_odd = {k : v * 2 if odd_condition(v) else v for (k,v) in hashmap.items()}

print("\n\nHashmap dupa modificarea numerelor impare \n {}".format(hashmap_odd))

###########################################################################


def prime_check(n):
    if(n > 1):
        for i in range(2,n):
            if n % i == 0 :
                return False
    return True


prime_condition = lambda p,x : p(x)

hashmap_prime = {k : v for (k,v) in hashmap.items() if prime_condition(prime_check,v)}

print("\n\nHashmap dupa eliminarea numerelor prime \n {}".format(hashmap_prime))

