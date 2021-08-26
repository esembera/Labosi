tempLista=input().split("|")
UlazniNizovi = [i.split(",") for i in tempLista]
dozvoljenaStanja=input().split(",")
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
        funkcijePrijelaza[tempPrijelazi[0]][tempPrijelazi[1].split("->")[0]]=tempPrijelazi[1].split("->")[1].split(",")
        tempPrijelazi=input().split(",",1)
except Exception:
    pass

def EpsilonPrijelaz(trenutnaStanja:list):
    i=0
    while i < len(trenutnaStanja):
        if trenutnaStanja[i] in funkcijePrijelaza.keys():
            if "$" in funkcijePrijelaza[trenutnaStanja[i]].keys():
                trenutnaStanja+=funkcijePrijelaza[trenutnaStanja[i]]["$"]
                tmpTrenutna=[]
                for j in trenutnaStanja:
                    if j not in tmpTrenutna:
                        if not(j == "#" and tmpTrenutna[-1] != "#"):
                            tmpTrenutna.append(j)
                trenutnaStanja=tmpTrenutna
        i+=1    
    return trenutnaStanja

def ispis(stanja:list, da):
    for i in range(len(stanja)):
        if i < len(stanja)-1:
            print(f"{stanja[i]},",end="")
        else:
            if not da:
                print(f"{stanja[i]}|",end="")    
            else:
                print(f"{stanja[i]}")

if __name__=="__main__":
    for ulazniNiz in UlazniNizovi:
        trenutnaStanja=[pocetnoStanje]
        for simbol in ulazniNiz:
            trenutnaStanja=EpsilonPrijelaz(trenutnaStanja.copy())
            trenutnaStanja.sort()
            ispis(trenutnaStanja, False)
            sljedecaStanja=[]
            for stanje in trenutnaStanja:
                if stanje in funkcijePrijelaza.keys():
                    if simbol in funkcijePrijelaza[stanje].keys():
                        sljedecaStanja+=funkcijePrijelaza[stanje][simbol]
                    else:
                        sljedecaStanja+=["#"]
                    tmpSljedeca=[]
                    sljedecaStanja.sort()
                    for sljedece in sljedecaStanja:
                        if sljedece not in tmpSljedeca:
                            if not(sljedece == "#" and sljedecaStanja[-1] != "#"):
                                tmpSljedeca.append(sljedece)
                    sljedecaStanja=tmpSljedeca
            sljedecaStanja.sort()
            if not sljedecaStanja:
                sljedecaStanja.append("#")
            trenutnaStanja=sljedecaStanja
        trenutnaStanja=EpsilonPrijelaz(trenutnaStanja.copy())
        trenutnaStanja.sort()
        ispis(trenutnaStanja, True)



