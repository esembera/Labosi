#importani library-iji
import sys 

#globalne varijable
klauzule = []
zavrsnielem = ''
izlaz = False
index = 0
tekst = []
mapa = {}


def nigativirajElement(element: str): # funkcija za negiranje elemenata unutar klauzule
    if (element.startswith('~')):
        element = element.strip('~')
        return element
    element = '~' + element
    return element


def nigativirajKlauzulu(klauzula: tuple): #Funkcija za negiranje klauzula
    global klauzule
    klauzule.pop()
    for k in klauzula:
        k = nigativirajElement(k)
        klauzule.append(tuple([k]))


def inputFile(name):  # Funkcija za učitavanje opisnika prostora
    global klauzule
    global zavrsnielem
    i = 0
    with open(name, 'r', encoding='utf-8') as infile:
        lines = infile.readlines()
    for line in lines:
        i += 1
        line = line.strip()
        if (line.startswith('#')):
            continue
        if (line == ''):
            continue
        klauzule.append(tuple(line.lower().split(' v ')))


def remove(tup, slovo): # funkcija za micanje elemenata iz klauzule
    tempTuple = ()
    for i in tup:
        if (i == slovo):
            continue
        tempTuple += tuple([i])

    return tempTuple


def provjeriRedundanciju(tup): # funkcija za provjeravanje redundancije među klauzulama
    global klauzule
    if (tup == ()):
        return True
    for i in klauzule:
        zbroj = 0
        for j in i:
            for k in tup:
                if (j == k):
                    zbroj += 1
        if (zbroj == len(i)):
            return False
    return True


def provjeriTautologiju(tup): # funkcija za provjeru tautologije među klauzulama
    for k in tup:
        for j in tup:
            if (k == nigativirajElement(j)):
                return False
    return True


def makniDuplikate(tup): # funkcija koja miče duple elemente u klauzulama
    return tuple(set(tup))


def pronadiRazlicitoIstog(prvi, drugi): # funkcija koja pronalazi dvije klauzule koje se mogu spojiti na temelju istih ali suprotnih elemenata
    global izlaz
    global index
    global tekst
    for e in prvi:
        for f in drugi:
            if e == nigativirajElement(f):
                tempTuple = remove(prvi, e) + remove(drugi, f)
                tempTuple = makniDuplikate(tempTuple)
                if (provjeriTautologiju(tempTuple) and provjeriRedundanciju(tempTuple)):
                    index+=1
                    if(tempTuple != ()):
                        tekst.append(str(index) + '. ' + ' v '.join(tempTuple) + ' (' + str(klauzule.index(prvi)+1) + ', ' + str(klauzule.index(drugi)+1) + ')')
                        klauzule.append(tempTuple)
                    else:
                        tekst.append(str(index) + '. NIL' + ' (' + str(klauzule.index(prvi)+1) + ', ' + str(klauzule.index(drugi)+1) + ')')
                        klauzule.append(tempTuple)
                        izlaz = True
                        return


def resolution(): # funkcija za resolution
    global klauzule
    global izlaz
    global zavrsnielem
    global tekst
    global mapa
    daneKlauzule = 0
    zavrsnielem = ' v '.join(klauzule[-1])
    nigativirajKlauzulu(klauzule[-1])
    global index
    for k in klauzule:
        index += 1
        daneKlauzule +=1
        ispis = ' v '.join(k)
        print(str(index) + '. ' + ispis)
    print('===============')
    for k in klauzule:
        for j in klauzule:
            pronadiRazlicitoIstog(k, j)
            if izlaz:
                break
        if izlaz:
            break
    
    if tekst != []:
        j=0
        br = 0
        tempTekst = []
        linijeZaObici = []
    
        tempLine = tekst[-1]
        tempTekst.append(tempLine)
        if int(tempLine.split("(")[1].strip(')').split(', ')[0]) > daneKlauzule or int(tempLine.split("(")[1].strip(')').split(', ')[1]) > daneKlauzule:
            if(int(tempLine.split("(")[1].strip(')').split(', ')[0]) > daneKlauzule):
                linijeZaObici.append(tempLine.split("(")[1].strip(')').split(', ')[0])
            if(int(tempLine.split("(")[1].strip(')').split(', ')[1]) > daneKlauzule):
                linijeZaObici.append(tempLine.split("(")[1].strip(')').split(', ')[1])
        tekst.pop()

        tekst1 = tekst.copy()

        i=0
        j = daneKlauzule + 1
        while i < len(tekst1):
            tempLine = tekst[-1]
            if tempLine.split('. ')[0] in linijeZaObici:
                linijeZaObici.remove(tempLine.split('. ')[0])
                tempTekst.insert(0,tempLine)
                if int(tempLine.split("(")[1].strip(')').split(', ')[0]) > daneKlauzule or int(tempLine.split("(")[1].strip(')').split(', ')[1]) > daneKlauzule:
                    if(int(tempLine.split("(")[1].strip(')').split(', ')[0]) > daneKlauzule):
                        linijeZaObici.append(tempLine.split("(")[1].strip(')').split(', ')[0])
                    if(int(tempLine.split("(")[1].strip(')').split(', ')[1]) > daneKlauzule):
                        linijeZaObici.append(tempLine.split("(")[1].strip(')').split(', ')[1])
            tekst.pop()
            i+=1
        j=daneKlauzule+1
        for line in tempTekst:
            tempLajna = line.split('. ')[1]
            tempLajna = str(j) + '. ' + tempLajna
            tempString = tempLajna.split('(')[0]
            prvi = tempLajna.split(' (')[1].split(', ')[0]
            drugi = tempLajna.split(' (')[1].split(', ')[1].strip(')')
            if prvi in mapa.keys():
                tempString+= '(' + mapa[prvi] + ', '
            else:
                tempString+= '(' + prvi + ', '
            
            if drugi in mapa.keys():
                tempString+= mapa[drugi] + ')'
            else:
                tempString+= drugi + ')'

            print(tempString)
            mapa[line.split('. ')[0]] = tempString.split('. ')[0]
            j+=1
            



    print('===============')
    if (izlaz):
        print("[CONCLUSION]: " + zavrsnielem.strip().lower() + " is true")
    else:
        print("[CONCLUSION]: " + zavrsnielem.strip().lower() + " is unknown")


def cooking(args): # funkcija za cooking
    global klauzule
    global izlaz
    global index
    global tekst
    global mapa
    with open(args[3], 'r', encoding='utf-8') as infile:
        lines = infile.readlines()
    for line in lines:
        line = line.lower().strip()
        if (line.startswith('#')):
            continue
        if (line == ''):
            continue
        print("User's command: " + line)
        lajna = tuple(line[:-2].split(' v '))
        if(line[-1] == '+'):
            if lajna not in klauzule:
                klauzule.append(lajna)
        elif(line[-1] == '-'):
            if lajna in klauzule:
                klauzule.remove(lajna)
        else:
            izlaz = False
            index = 0
            tekst = []
            mapa = {}
            klauzule1 = klauzule.copy()
            klauzule.append(lajna)
            resolution()
            klauzule = klauzule1    
    

if __name__ == "__main__":  # main
    args = sys.argv
    inputFile(args[2])
    if (args[1] == 'resolution'):
        resolution()
    if (args[1] == 'cooking'):
        cooking(args)


