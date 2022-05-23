import sys
import math
from dataclasses import dataclass,field


@dataclass
class cvor:
    value: str
    djeca: dict = field(default_factory=dict)


znacajke = {}
brojVarijabli = 0
trazeniBroj = {}
entropija = 0
fajla = []
stablo = None
losaVarijabla = None
distinktneVar = set()
dubina = -1


def inputFile(name):
    global znacajke
    global brojVarijabli
    global trazeniBroj
    global fajla
    global losaVarijabla
    global distinktneVar

    fajla = []
    znacajke = {}
    brojVarijabli = 0
    trazeniBroj = {}
    losaVarijabla = None
    distinktneVar = set()

    i=0
    token=0
    with open(name, 'r', encoding='utf-8') as infile:     
        lines = infile.readline()
        lines = lines.strip()
        lajna = lines.split(',')
        fajla.append(lajna.copy())
        tempLines = infile.readlines()
        for znacajka in lajna:
            znacajke[znacajka]={}
            for line in tempLines:
                line = line.strip()
                line = line.split(',')
                if token == 0:
                    fajla.append(line.copy())
                if (line[i] not in znacajke[znacajka]):
                    znacajke[znacajka][line[i]] = {}
                tempObject = line.pop()
                if(i == len(line)-1):
                    if losaVarijabla == None or tempObject < losaVarijabla:
                        losaVarijabla = tempObject
                    distinktneVar.add(tempObject) 
                if(i == 0):
                    if(tempObject not in trazeniBroj):
                        trazeniBroj[tempObject] = 0
                    trazeniBroj[tempObject] += 1
                if(tempObject not in znacajke[znacajka][line[i]]):
                    znacajke[znacajka][line[i]][tempObject] = 0
                znacajke[znacajka][line[i]][tempObject] += 1                
            if(i < len(line) - 1):
                i+=1
            else:
                break
            token +=1
        brojVarijabli = len(tempLines)


def Entropija2(key, dictionary):
    entropija = 0

    global znacajke

    brojVarijabli = 0

    dict = dictionary[key]
    tempDict = {}

    for item in dict:
        if(item not in tempDict):
            tempDict[item] = dict[item]
            brojVarijabli += dict[item]

    flag = 0
    for key3 in tempDict.keys():
        if(tempDict[key3] != 0):
            flag +=1
        try:
            rez = tempDict[key3]/brojVarijabli
            entropija -= rez * math.log(rez, len(tempDict.keys()))
        except Exception:
            continue
    
    if(flag == 1):
        entropija = 0

    return entropija, brojVarijabli


def Entropija1(key, dataset):
    entropija = 0
    brojVarijabli = 0
    dict = dataset[key]
    tempDict = {}

    for item in dict:   
        for item2 in dict[item]:
            if(item2 not in tempDict):
                tempDict[item2] = dict[item][item2]
                brojVarijabli += dict[item][item2]
            else:
                tempDict[item2] += dict[item][item2]
                brojVarijabli += dict[item][item2]

    flag = 0
    for key3 in tempDict.keys():
        if(tempDict[key3] != 0):
            flag +=1
        try:
            rez = tempDict[key3]/brojVarijabli
            entropija -= rez * math.log(rez, len(tempDict.keys()))
        except Exception:
            continue
    
    if(flag == 1):
        entropija = 0
    
    ig = entropija

    for item in dict:
        entropija2, brojVarijabli2 = Entropija2(item, dict)
        ig -= (brojVarijabli2/brojVarijabli)*entropija2

    return ig
    

def kreirajStrukturuINadiEntropiju(dataset, lockedItem, lockedParent):
    global znacajke
    global entropija

    novaStruktura = {}
    lajna = dataset.pop(0)
    broj = {}

    parentIndex = lajna.index(lockedParent)

    for i in range(len(lajna)-1):
        znacajka = lajna[i]
        if znacajka == lockedParent:
            continue

        novaStruktura[znacajka]={}
        
        for line in dataset:
            if line[i] not in novaStruktura[znacajka]:
                novaStruktura[znacajka][line[i]] = {}

            if line[len(line)-1] not in novaStruktura[znacajka][line[i]]:
                novaStruktura[znacajka][line[i]][line[len(line)-1]] = 0
                
            novaStruktura[znacajka][line[i]][line[len(line)-1]] += 1

    return novaStruktura


def nadiNoviDataset(najveciID, dataset, noviFajla):
    tempFajla = noviFajla.copy()
    trazeniStupac = dataset[najveciID]
    trazeniRedovi = {}

    prviRed = tempFajla.pop(0)
    stupac = prviRed.index(najveciID)
    for item in trazeniStupac.keys():
        if item not in trazeniRedovi:
            trazeniRedovi[item] = []
            trazeniRedovi[item].append(prviRed)
        
        for red in tempFajla:
            if(red[stupac] == item): 
                trazeniRedovi[item].append(red)

    return trazeniRedovi


def nadiNoviFajl(noviFajla, lockedItem, lockedParent):
    noviNoviFajl = []

    lajna = noviFajla[0]
    parentIndex = lajna.index(lockedParent)

    tmp = []
    for i in range(len(lajna)):
        if i != parentIndex:
            tmp.append(lajna[i])
    noviNoviFajl.append(tmp)

    for i in range(1, len(noviFajla)):
        line = noviFajla[i]
        if line[parentIndex] != lockedItem:
            continue
        
        tmp = []
        for i in range(len(line)):
            if i != parentIndex:
                tmp.append(line[i])

        noviNoviFajl.append(tmp)

    return noviNoviFajl

            
def pronadiRjesenje(noviFajla):
    brojVrijednosti = {}
    najveci = -1
    najveciID = ""

    
    for i in range(1, len(noviFajla)):
        line = noviFajla[i]
        if line[len(line)-1] not in brojVrijednosti:
            brojVrijednosti[line[len(line)-1]] = 0
        brojVrijednosti[line[len(line)-1]] += 1

    for item in brojVrijednosti:
        if brojVrijednosti[item] > najveci:
            najveci = brojVrijednosti[item]
            najveciID = item
        elif brojVrijednosti[item] == najveci and najveciID>item:
            najveciID=item

    return najveciID


def vrtiSve(dataset, noviFajla, dubina):
    entropije = {}
    noviDataset = {}
    novaStruktura = {}

    if dubina == 0:
        return cvor(pronadiRjesenje(noviFajla), {})

    for item in dataset:
        entropije[item] = Entropija1(item, dataset)
        print(f"IG({item}) = {entropije[item]:.4f}  ", end="")

    print()


    najveci = -1
    najveciID = ""

    for item in entropije:
        if entropije[item] > najveci:
            najveci = entropije[item]  
            najveciID = item
        elif entropije[item] == najveci and najveciID>item:
            najveciID=item


    if najveci == -1:
        return cvor (noviFajla[1][0], {})


    if najveci == 0:
        return cvor(list(dataset[list(dataset.keys())[0]][list(dataset[list(dataset.keys())[0]].keys())[0]].keys())[0], {})

    node = cvor(najveciID, {})

    noviDataset = nadiNoviDataset(najveciID, dataset, noviFajla)

    for item in sorted(list(noviDataset.keys())):
        novaStruktura = kreirajStrukturuINadiEntropiju(noviDataset[item], item, najveciID)
        noviNoviFajl = nadiNoviFajl(noviFajla, item, najveciID)
        dijete = vrtiSve(novaStruktura, noviNoviFajl, dubina - 1)
        node.djeca[item] = dijete

    return node
         

def razrijesiDrvo(node:cvor, povijest):
    povijest.append(node.value)
    if node.djeca != {}:
        for dijete in node.djeca:
            povijest.append(dijete)
            razrijesiDrvo(node.djeca[dijete], povijest)
            povijest.pop()
    else:
        for i in range(int(len(povijest)/2)):
            print(f"{i+1}:{povijest[i*2]}={povijest[i*2+1]} ", end="")
        print(f"{node.value}")
    povijest.pop()


def provediKrozStablo(cvoric:cvor):
    global fajla
    global losaVarijabla
    global distinktneVar

    distinktneVar = sorted(distinktneVar)
    brojacTocnih = 0
    tempFajlicic = fajla.pop(0)
    vrijednosti = []
    matrica = []

    for i in range(len(distinktneVar)):
        matrica.append([])
        for j in range(len(distinktneVar)):
            matrica[i].append(0)
    

    for line in fajla:
        cvorina = cvoric
        try:
            while len(cvorina.djeca) != 0:
                cvorina = cvorina.djeca[line[tempFajlicic.index(cvorina.value)]]
            if(cvorina.value == line[len(line)-1]):
                brojacTocnih += 1
            vrijednosti.append(cvorina.value)
            matrica[list(distinktneVar).index(cvorina.value)][list(distinktneVar).index(line[len(line)-1])] += 1
        except Exception:
            if(losaVarijabla == line[len(line)-1]):
                brojacTocnih += 1
            vrijednosti.append(losaVarijabla)
            matrica[list(distinktneVar).index(losaVarijabla)][list(distinktneVar).index(line[len(line)-1])] += 1


    print("[PREDICTIONS]: ", end="")
    print(" ".join(vrijednosti))

    preciznost = brojacTocnih/(len(vrijednosti))

    print(f"[ACCURACY]: {preciznost:.5f}")
    print("[CONFUSION_MATRIX]:")
    for i in range(len(distinktneVar)):
        for j in range(len(distinktneVar)):
            print(f"{matrica[j][i]} ", end="")
        print()


if __name__ == "__main__":  # main
    args = sys.argv
    inputFile(args[1])
    try:
        dubina = int(args[3])
    except Exception:
        pass
    root = vrtiSve(znacajke, fajla, dubina)
    print("[BRANCHES]:")
    razrijesiDrvo(root, [])
    inputFile(args[2])
    provediKrozStablo(root)
