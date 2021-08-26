skupStanja=input().split(",")
abeceda=input().split(",")
prihvatljivaStanja=input().split(",")
pocetnoStanje=input()
funkcijePrijelaza = {} 
tempPrijelazi = input().split(",",1)
try:
    while tempPrijelazi:
        if tempPrijelazi[0] == "van": 
            break
        if tempPrijelazi[0] not in funkcijePrijelaza.keys():
            funkcijePrijelaza[tempPrijelazi[0]]={}
        funkcijePrijelaza[tempPrijelazi[0]][tempPrijelazi[1].split("->")[0]]=tempPrijelazi[1].split("->")[1]
        tempPrijelazi=input().split(",",1)
except Exception:
    pass

if __name__=="__main__":
    dohvatljivaStanja = [pocetnoStanje]
    brojac = 0
    while brojac < len(dohvatljivaStanja):
        if dohvatljivaStanja[brojac] in funkcijePrijelaza.keys():
            for simbol in abeceda:
                if simbol in funkcijePrijelaza[dohvatljivaStanja[brojac]].keys():
                    if funkcijePrijelaza[dohvatljivaStanja[brojac]][simbol] not in dohvatljivaStanja:
                        dohvatljivaStanja.append(funkcijePrijelaza[dohvatljivaStanja[brojac]][simbol])
        brojac+=1

    for stanje in skupStanja:
        if stanje not in dohvatljivaStanja:
            funkcijePrijelaza.pop(stanje)
    lista={}
    dohvatljivaStanja.sort()
    
    for stanje in prihvatljivaStanja.copy():
        if stanje not in dohvatljivaStanja:
            prihvatljivaStanja.remove(stanje)

    tablica = [[0 for i in range(len(dohvatljivaStanja))] for j in range(len(dohvatljivaStanja))]

    for i in range(0,len(dohvatljivaStanja)):
        for j in range(0, i+1):
            for simbol in abeceda:
                if ((funkcijePrijelaza[dohvatljivaStanja[i]][simbol] in prihvatljivaStanja)^(funkcijePrijelaza[dohvatljivaStanja[j]][simbol] in prihvatljivaStanja)):
                    tablica[i][j]=1
                    break
                else:
                    if not tablica[i][j]:
                        if(funkcijePrijelaza[dohvatljivaStanja[i]][simbol],funkcijePrijelaza[dohvatljivaStanja[j]][simbol]) not in lista.keys():
                            lista[(funkcijePrijelaza[dohvatljivaStanja[i]][simbol],funkcijePrijelaza[dohvatljivaStanja[j]][simbol])]=[]
                        lista[(funkcijePrijelaza[dohvatljivaStanja[i]][simbol],funkcijePrijelaza[dohvatljivaStanja[j]][simbol])].append((dohvatljivaStanja[i],dohvatljivaStanja[j]))

    for kljuc in lista.keys():
        if tablica[dohvatljivaStanja.index(kljuc[0])][dohvatljivaStanja.index(kljuc[1])]:
            for stanja in lista[kljuc]:
                tablica[dohvatljivaStanja.index(stanja[0])][dohvatljivaStanja.index(stanja[1])] = 1

    istovjetnaStanja=[]
    for i in range(1,len(dohvatljivaStanja)):
        for j in range(0, i):
            if(tablica[i][j]==0):
                istovjetnaStanja.append([dohvatljivaStanja[i],dohvatljivaStanja[j]] if not((dohvatljivaStanja[i] in prihvatljivaStanja)^(dohvatljivaStanja[j] in prihvatljivaStanja))else None)
                if not istovjetnaStanja[-1]:
                    istovjetnaStanja.pop(-1)
                istovjetnaStanja[-1].sort()

    DozvoljenaStanjaZaIspis=set()

    for stanje in dohvatljivaStanja:
        for isto in istovjetnaStanja:
            if stanje in isto:
                if stanje in DozvoljenaStanjaZaIspis:
                    DozvoljenaStanjaZaIspis.remove(stanje)
                DozvoljenaStanjaZaIspis.add(isto[0])
                break
            else:
                DozvoljenaStanjaZaIspis.add(stanje)

    DozvoljenaStanjaZaIspis=list(DozvoljenaStanjaZaIspis)
    DozvoljenaStanjaZaIspis.sort()
    
    prihvatljivaStanjaZaIpis=set()

    for stanje in prihvatljivaStanja:    
        for isto in istovjetnaStanja:
            if stanje in isto:
                if stanje in prihvatljivaStanjaZaIpis:
                    prihvatljivaStanjaZaIpis.remove(stanje)
                prihvatljivaStanjaZaIpis.add(isto[0])
                break
            else: 
                prihvatljivaStanjaZaIpis.add(stanje)

    prihvatljivaStanjaZaIpis=list(prihvatljivaStanjaZaIpis)
    prihvatljivaStanjaZaIpis.sort()

    funkcijePrijelazaZaIspis = {}

    for kljuc in funkcijePrijelaza.keys():
        for isto in istovjetnaStanja:
            if kljuc in isto:
                if kljuc in funkcijePrijelazaZaIspis.keys():
                    funkcijePrijelazaZaIspis.pop(kljuc)
                funkcijePrijelazaZaIspis[isto[0]]={}
                break
            else:
                funkcijePrijelazaZaIspis[kljuc]={}

    for kljuc in funkcijePrijelazaZaIspis.keys():
        for simbol in abeceda:
            for isto in istovjetnaStanja:
                if funkcijePrijelaza[kljuc][simbol] in isto:
                    funkcijePrijelazaZaIspis[kljuc][simbol]=isto[0]
                    break
                else:
                    funkcijePrijelazaZaIspis[kljuc][simbol]=funkcijePrijelaza[kljuc][simbol]
            
    if not istovjetnaStanja:
        print(",".join(dohvatljivaStanja))
        print(",".join(abeceda))
        print(",".join(prihvatljivaStanja))
        print(pocetnoStanje)
        for kljuc in funkcijePrijelaza.keys():
            for simbol in funkcijePrijelaza[kljuc].keys():
                print(f"{kljuc},{simbol}->{funkcijePrijelaza[kljuc][simbol]}")
    else:
        print(",".join(DozvoljenaStanjaZaIspis))
        print(",".join(abeceda))
        print(",".join(prihvatljivaStanjaZaIpis))
        for stanje in istovjetnaStanja:
            if pocetnoStanje in stanje:
                pocetnoStanje = stanje[0]
                break
        print(pocetnoStanje)
        for kljuc in funkcijePrijelazaZaIspis.keys():
            for simbol in funkcijePrijelazaZaIspis[kljuc].keys():
                print(f"{kljuc},{simbol}->{funkcijePrijelazaZaIspis[kljuc][simbol]}")


