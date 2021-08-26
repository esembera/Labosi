ulazniNiz = input()
br = 0
ulaz = ulazniNiz[br]

def funkcA():
    global ulaz
    global br
    global ulazniNiz
    if ulaz == 'b':
        br+=1
        if not (br > len(ulazniNiz)-1):
            ulaz = ulazniNiz[br]
        else: ulaz = ""
        print("A",end="")
        funkcC()
    elif ulaz == 'a':
        br+=1
        if not(br > len(ulazniNiz)-1):
            ulaz = ulazniNiz[br]
        else: ulaz = ""
        print("A",end="")
    else:
        print("A")
        print("NE")
        exit()


def funkcB():
    global ulaz
    global br
    global ulazniNiz
    if ulaz == 'c':
        br+=1
        if not(br > len(ulazniNiz)-1):
            ulaz = ulazniNiz[br]
        else: ulaz = ""
        if ulaz == 'c':
            br+=1
            if not (br > len(ulazniNiz)-1):
                ulaz = ulazniNiz[br]
            else: ulaz = ""
            print("B",end="")
            funkcS()
            if ulaz == 'b':
                br+=1
                if not(br > len(ulazniNiz)-1):
                    ulaz = ulazniNiz[br]
                else: ulaz = ""
                if ulaz == 'c':
                    br+=1
                    if not(br > len(ulazniNiz)-1):
                        ulaz = ulazniNiz[br]
                    else: ulaz = ""
                else: 
                    print("B",end="")
            else: 
                print("B",end="")
        else: 
            print("B",end="")
    elif not ulaz:
        print("B",end="")
    else: 
        print("B",end="")



def funkcC():
    print("C",end="")
    funkcA()
    funkcA()


def funkcS():
    global ulaz
    global br
    global ulazniNiz
    if ulaz == 'a':
        br+=1
        if not(br > len(ulazniNiz)-1):
            ulaz = ulazniNiz[br]
        else: ulaz = ""
        print("S",end="")
        funkcA()
        funkcB()
    elif ulaz == 'b':
        br+=1
        if not(br > len(ulazniNiz)-1):
            ulaz = ulazniNiz[br]
        else: ulaz = ""
        print("S",end="")
        funkcB()
        funkcA()
    else:
        print("S")
        print("NE")
        exit() 

if __name__=="__main__":
    funkcS()
    if ulaz:
        print("")
        print("NE")
    else:
        print("")
        print("DA")