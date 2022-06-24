class Celula:
    def GetNume(self):
        pass

class Fibra_Musculara(Celula):
    def __init__(self,nume,masam):
        self.Nume = nume
        self.MasaMusculara = masam

    def GetNume(self):
        return self.Nume

    def GetMasaMusculara(self):
        return self.MasaMusculara

class Fibra_Nervoasa(Celula):
    def __init__(self,nume,lung):
        self.Nume = nume
        self.Lungime = lung

    def GetNume(self):
        return self.Nume

    def GetLungime(self):
        return self.Lungime


class Muschi_Generic:
    Fibre = []
    def __init__(self,nume,masam,scop,fibre):
        self.Nume = nume
        self.MasaMusculara = masam
        self.Scop = scop
        self.Fibre = fibre

    def GetNume(self):
        return self.Nume

    def GetScop(self):
        return self.Scop

    def GetMasaMusculara(self):
        return self.MasaMusculara

class Trunchi_Nervos:
    Nervi = []
    def __init__(self,nume,lung,spec,nervi):
        self.Nume = nume
        self.Lungime = lung
        self.Specializare = spec
        self.Nervi = nervi

    def GetNume(self):
        return self.Nume

    def GetLungime(self):
        return self.Lungime

    def GetSpecializare(self):
        return self.Specializare


Muschi = []
fibra = []
fibra.append(Fibra_Musculara("tonice",0.4))
fibra.append(Fibra_Musculara("glicolitice",0.9))
Muschi.append(Muschi_Generic("Biceps Stang",0.494299,{"locomotor","incordare"},fibra))
Muschi.append(Muschi_Generic("Biceps Drept",0.3666,{"locomotor","incordare"},fibra))
Muschi.append(Muschi_Generic("Triceps Stang",0.4447,{"locomotor","relaxare"},fibra))
Muschi.append(Muschi_Generic("Triceps Drept",0.491599,{"locomotor","relaxare"},fibra))
Muschi.append(Muschi_Generic("Gamba Stanga",0.43329,{"locomotor","incordare"},fibra))
Muschi.append(Muschi_Generic("Gamba Dreapta",0.4665,{"locomotor","incordare"},fibra))
Muschi.append(Muschi_Generic("Stomac",1.251799,{"digestie","incordare"},fibra))


total_muschi = 0
for muschi in Muschi:
    print("Masa musculara a muschilor [{}] = {}".format(muschi.GetNume(),muschi.GetMasaMusculara()))
    total_muschi += muschi.GetMasaMusculara()

print("Masa totala a muschilor = {}".format(total_muschi))


SistemNervos = []
nervi = []
nervi.append(Fibra_Nervoasa("dendrite",12))
nervi.append(Fibra_Nervoasa("axoni",15))
SistemNervos.append(Trunchi_Nervos("Emisfera Stanga",1439.199,"Scop Emisfera Stanga",nervi))
SistemNervos.append(Trunchi_Nervos("Emisfera Dreapta",1672.8,"Scop Emisfera Dreapta",nervi))
SistemNervos.append(Trunchi_Nervos("Cerebel",1088.5,"Scop Cerebel",nervi))
SistemNervos.append(Trunchi_Nervos("Maduva",1210.9,"Scop Maduva",nervi))

lungime_sn = 0
for sn in SistemNervos:
    print("Lungimea sistemului nervos [{}] = {}".format(sn.GetNume(),sn.GetLungime()))
    lungime_sn += sn.GetLungime()

print("Lungimea axonilor din sistemul nervos = {}".format(lungime_sn))

print("Urmatorii muschi au functie locomotorie:")
for muschi in Muschi:
    scop = muschi.GetScop()
    if "locomotor" in scop:
        print("{} [{}]".format(muschi.GetNume(),muschi.GetScop()))



