tempLista=input().split("|")
UlazniNizovi = [i.split(",") for i in tempLista]
skupStanja=input().split(",")
ulazniZnakovi=input().split(",")
znakoviStoga=input().split(",")
prihvatljivaStanja=input().split(",")
pocetnoStanje=input()
pocetniZnakStoga=input()
funkcijePrijelaza = {} 
tempPrijelazi = input().split("->",1)
try:
    while tempPrijelazi:
        if tempPrijelazi[0] == "van": 
            break
        tempTuple=tuple(tempPrijelazi[0].split(","))
        funkcijePrijelaza[tempTuple]=tempPrijelazi[1].split(",")
        tempPrijelazi=input().split("->",1)
except Exception:
    pass

stack = [pocetniZnakStoga]

def epsilonPrijelaz(trenutnoStanje,fail,ispit):
    kljuc=(trenutnoStanje, "$", stack.pop() if stack else "$")
    while(kljuc in funkcijePrijelaza.keys() and not(trenutnoStanje in prihvatljivaStanja and ispit)):
        trenutnoStanje=funkcijePrijelaza[kljuc][0]
        for i in reversed(funkcijePrijelaza[kljuc][1]):
            if(i!="$"):
                stack.append(i)    
        if not fail:
            ispis(trenutnoStanje, stack)
        kljuc=(trenutnoStanje,"$",stack.pop() if stack else "$")
    stack.append(kljuc[2])
    return trenutnoStanje

def ispis(stanje,stack:list):
    print(f"{stanje}#{''.join(reversed(stack)) if stack else '$'}|",end="")

    

if __name__=="__main__":
    for ulazniNiz in UlazniNizovi:
        fail=0
        stack=[pocetniZnakStoga]
        trenutnoStanje=pocetnoStanje
        ispis(trenutnoStanje,stack)
        for simbol in ulazniNiz:
            trenutnoStanje=epsilonPrijelaz(trenutnoStanje,fail,False)
            tempKljuc=(trenutnoStanje,simbol,stack.pop() if stack else "$")
            if tempKljuc in funkcijePrijelaza.keys():
                    trenutnoStanje=funkcijePrijelaza[tempKljuc][0]
                    for i in reversed(funkcijePrijelaza[tempKljuc][1]):
                        if(i!="$"):
                            stack.append(i) 
                    ispis(trenutnoStanje, stack)
            else:
                fail=1
                print("fail|",end="")
                break
        trenutnoStanje=epsilonPrijelaz(trenutnoStanje,fail,True)
        if trenutnoStanje in prihvatljivaStanja and not fail:
            print("1")
        else:
            print("0")



