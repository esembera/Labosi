import argparse
import queue

# Globalne varijable
opisnikProstora = {}
pocetnoStanje = ''
zavrsnaStanja = []
heuristika = {}


class UCSTuple: # Klasa za tuple koji koristim u UCS algoritmu u kojem se vrši usporedba po cijeni, a zatim po trenutnom stanju
    def __init__(self, cijena, trenutnoStanje, prethodnoStanje) -> None:
        self.cijena = cijena
        self.prethodnoStanje = prethodnoStanje
        self.trenutnoStanje = trenutnoStanje
    def __lt__(self, other):
        if self.cijena == other.cijena:
            return self.trenutnoStanje < other.trenutnoStanje
        return self.cijena < other.cijena


class cucTuple: # Klasa za tuple koji koristim u A-star algoritmu u kojem se vrši usporedba po heuristici, a zatim po trenutnom stanju
    def __init__(self, cijena, trenutnoStanje, prethodnoStanje, heuristika) -> None:
        self.cijena = cijena
        self.prethodnoStanje = prethodnoStanje
        self.trenutnoStanje = trenutnoStanje
        self.heuristika = heuristika
    def __lt__(self, other):
        if self.heuristika == other.heuristika:
            return self.trenutnoStanje < other.trenutnoStanje
        return self.heuristika < other.heuristika


def parseArgs(): # Funkcija koja parsira ulazne arugmente
    parser = argparse.ArgumentParser()

    parser.add_argument('--ss', type=str, help="Putanja do datoteke sa stanjima")
    parser.add_argument('--check-consistent', action='store_true', default=False, help="Zastavica za provjeravanje konzistencije")
    parser.add_argument('--h', type=str, help="putanja do heuristike")
    parser.add_argument('--check-optimistic', action='store_true', default=False, help="Zastavica za provjeravanje optimisticnosti")
    parser.add_argument('--alg', type=str, help="Odabir vrste algoritma")

    return parser.parse_args()


def inputFile(name): # Funkcija za učitavanje opisnika prostora
    global pocetnoStanje
    global zavrsnaStanja
    global opisnikProstora
    with open (name, 'r',encoding='utf-8') as infile:
        lines = infile.readlines()
    
    for line in lines:
        line = line.strip()
        if(line.startswith('#')):
            continue
        if(pocetnoStanje == ''):
            pocetnoStanje = line
            continue
        if(len(zavrsnaStanja)==0):
            zavrsnaStanja = line.split(' ')
            continue

        tempOpisnik = line.split(' ')
        tupleList = []
        for i in range (1, len(tempOpisnik)):
            temp = tempOpisnik[i].split(',')
            tupleList.append((temp[0],temp[1]))
        tupleList.sort()
        opisnikProstora[tempOpisnik[0].removesuffix(':')] = tupleList
        

def inputFileCuc(name): # Funkcija za učitavanje heuristike
    global heuristika
    with open (name, 'r',encoding='utf-8') as infile:
        lines = infile.readlines()
    for line in lines:
        line = line.strip()
        if(line.startswith('#')):
            continue
        heuristika[line.split(': ')[0]] = float(line.split(': ')[1]) 


def BFS(): # Funkcija za izvršavanje BFS algoritma
    global pocetnoStanje
    global zavrsnaStanja
    global opisnikProstora
    bt = {}
    print('# BFS')
    red = queue.SimpleQueue()
    visited = {}
    red.put((pocetnoStanje,'', 0))
    while red.qsize():
        trenutnoStanje,prethodnoStanje, cijena = red.get()
        if trenutnoStanje in visited:
            continue
        visited[trenutnoStanje] = True
        bt[trenutnoStanje] = prethodnoStanje
        if trenutnoStanje in zavrsnaStanja:
            print('[FOUND_SOLUTION]: yes')
            print('[STATES_VISITED]: '+ str(len(visited)))
            findPath(trenutnoStanje, bt, cijena)
            return
        for susjed in opisnikProstora[trenutnoStanje]:
            red.put((susjed[0],trenutnoStanje,cijena + float(susjed[1])))
    print('[FOUND_SOLUTION]: no')
    

def findPath(zavStanje, bt, cijena): # Funkcija koja izvršava backtrace algoritam za pronalazak najkraćeg puta od cilja do starta
    global opisnikProstora
    path = []
    while zavStanje != pocetnoStanje:
        path.append(zavStanje)
        zavStanje = bt[zavStanje]
    path.append(pocetnoStanje)
    print('[PATH_LENGTH]: '+str(len(path)))
    print('[TOTAL_COST]: ' + str(cijena))
    path.reverse()
    print('[PATH]: '+path[0],end="")
    for i in range(1, len(path)):
        print(' => ' + path[i], end="")


def UCS(): # Funkcija za izvršavanje UCS algoritma
    global pocetnoStanje
    global zavrsnaStanja
    global opisnikProstora
    print('# UCS')
    bt = {}
    red = queue.PriorityQueue()
    visited = {}
    ivanCraftRS = UCSTuple(0, pocetnoStanje, '')
    red.put(ivanCraftRS)
    while red.qsize():
        ivanCraftRS = red.get()
        if ivanCraftRS.trenutnoStanje in visited:
            continue
        visited[ivanCraftRS.trenutnoStanje] = True
        bt[ivanCraftRS.trenutnoStanje] = ivanCraftRS.prethodnoStanje
        if ivanCraftRS.trenutnoStanje in zavrsnaStanja:
            print('[FOUND_SOLUTION]: yes')
            print('[STATES_VISITED]: '+ str(len(visited)))
            findPath(ivanCraftRS.trenutnoStanje, bt, ivanCraftRS.cijena)
            return
        for susjed in opisnikProstora[ivanCraftRS.trenutnoStanje]:
            red.put(UCSTuple(ivanCraftRS.cijena + float(susjed[1]),susjed[0],ivanCraftRS.trenutnoStanje))
    print('[FOUND_SOLUTION]: no')


def UCSbezPrinta(): # Funkcija koja izvršava UCS, ali bez printa, koju koristim za izračunavanje prave cijene puta kod provjere optimisticnosti heuristike
    global pocetnoStanje
    global zavrsnaStanja
    global opisnikProstora
    bt = {}
    red = queue.PriorityQueue()
    visited = {}
    ivanCraftRS = UCSTuple(0.0, pocetnoStanje, '')
    red.put(ivanCraftRS)
    while red.qsize():
        ivanCraftRS = red.get()
        if ivanCraftRS.trenutnoStanje in visited:
            continue
        visited[ivanCraftRS.trenutnoStanje] = True
        bt[ivanCraftRS.trenutnoStanje] = ivanCraftRS.prethodnoStanje
        if ivanCraftRS.trenutnoStanje in zavrsnaStanja:
            return ivanCraftRS.cijena
        for susjed in opisnikProstora[ivanCraftRS.trenutnoStanje]:
            red.put(UCSTuple(ivanCraftRS.cijena + float(susjed[1]),susjed[0],ivanCraftRS.trenutnoStanje))
    return -1.0


def A_STAR(putanja): # Funkcija za izvršavanje A-Star algoritma
    global pocetnoStanje
    global zavrsnaStanja
    global opisnikProstora
    global heuristika
    print('# A-STAR ' + putanja)
    bt = {}
    red = queue.PriorityQueue()
    visited = {}
    cucDictionary = {}
    ivanCraftRS = cucTuple(0, pocetnoStanje, '', 0)
    red.put(ivanCraftRS)
    while red.qsize():
        ivanCraftRS = red.get()
        if ivanCraftRS.trenutnoStanje in visited and ivanCraftRS.cijena >= cucDictionary[ivanCraftRS.trenutnoStanje]:
            continue
        cucDictionary[ivanCraftRS.trenutnoStanje] = ivanCraftRS.cijena
        visited[ivanCraftRS.trenutnoStanje] = True
        bt[ivanCraftRS.trenutnoStanje] = ivanCraftRS.prethodnoStanje
        if ivanCraftRS.trenutnoStanje in zavrsnaStanja:
            print('[FOUND_SOLUTION]: yes')
            print('[STATES_VISITED]: '+ str(len(visited)))
            findPath(ivanCraftRS.trenutnoStanje, bt, ivanCraftRS.cijena)
            return
        for susjed in opisnikProstora[ivanCraftRS.trenutnoStanje]:
            red.put(cucTuple(ivanCraftRS.cijena + float(susjed[1]),susjed[0],ivanCraftRS.trenutnoStanje, ivanCraftRS.cijena + heuristika[susjed[0]] + float(susjed[1])))
    print('[FOUND_SOLUTION]: no')


def provjeriKonzistenciju(putanja): # Funkcija koja provjerava konzistenciju heuristike
    global opisnikProstora
    global heuristika
    print('# HEURISTIC-CONSISTENT ' + putanja)

    cucaa = list(opisnikProstora.keys())
    cucaa.sort()

    cuc = True
    for stanje in cucaa:
        for sljedeceStanje in opisnikProstora[stanje]:
            if heuristika[stanje] <= heuristika[sljedeceStanje[0]] + float(sljedeceStanje[1]):
                print(f'[CONDITION]: [OK] h({stanje}) <= h({sljedeceStanje[0]}) + c: {heuristika[stanje]} <= {heuristika[sljedeceStanje[0]]} + {float(sljedeceStanje[1])}')
            else:
                cuc=False
                print(f'[CONDITION]: [ERR] h({stanje}) <= h({sljedeceStanje[0]}) + c: {heuristika[stanje]} <= {heuristika[sljedeceStanje[0]]} + {float(sljedeceStanje[1])}')
    if cuc:
        print('[CONCLUSION]: Heuristic is consistent.')
    else:
        print('[CONCLUSION]: Heuristic is not consistent.')


def provjeriOptimisticnost(putanja): # Funkcija koja provjerava optimističnost heuristike
    global opisnikProstora
    global heuristika
    global pocetnoStanje
    print('# HEURISTIC-OPTIMISTIC ' + putanja)
    cuccs = list(opisnikProstora.keys())
    cuccs.sort()

    cuc = True
    for stanje in cuccs:
        pocetnoStanje = stanje
        kifla = UCSbezPrinta()
        if(heuristika[stanje] <= kifla):
            print(f'[CONDITION]: [OK] h({stanje}) <= h*: {heuristika[stanje]} <= {kifla}')
        else:
            cuc=False
            print(f'[CONDITION]: [ERR] h({stanje}) <= h*: {heuristika[stanje]} <= {kifla}')
    if cuc:
        print('[CONCLUSION]: Heuristic is optimistic.')
    else:
        print('[CONCLUSION]: Heuristic is not optimistic.')



if __name__ == "__main__": # main
    args = parseArgs()
    inputFile(args.ss)
    if (args.h): 
        inputFileCuc(args.h)
    if(args.alg == 'bfs'):
        BFS()
    if(args.alg == 'ucs'):
        UCS()
    if(args.alg == 'astar'):
        A_STAR(args.h)
    if(args.check_consistent):
        provjeriKonzistenciju(args.h)
    if(args.check_optimistic):
        provjeriOptimisticnost(args.h)

