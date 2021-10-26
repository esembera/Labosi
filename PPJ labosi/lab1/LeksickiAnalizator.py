mapaKljRijeci = {'za': 'KR_ZA', 'od': 'KR_OD', 'do': 'KR_DO', 'az': 'KR_AZ'}
mapaOperatora = {'=': 'OP_PRIDRUZI', '+': 'OP_PLUS', '-': 'OP_MINUS', '*': 'OP_PUTA', '/': 'OP_DIJELI', '(': 'L_ZAGRADA',')': 'D_ZAGRADA'}
brojevi = '0123456789'
abeceda = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'

def MakniRazmake(ulaz):
    radno=[i for i in ulaz]
    i=0
    while i < len(radno):
        if(radno[i] == ' '):
            j=i+1
            while(radno[j] == ' '):
                radno[j] = ''
                j+=1
        i+=1
    return ''.join(radno)

def DodajRazmake(ulaz):
    radno=[i for i in ulaz.strip().replace('\t', ' ')]
    i=0
    while i < len(radno):
        if(radno[i] in mapaOperatora):
            if(i-1 >= 0) and radno[i-1] != '/' and (radno[i-1] != ' '):
                radno.insert(i, ' ')
                i+=1
            if i+1 < len(radno) and radno[i+1] != '/' and (radno[i+1] != ' '):
                radno.insert(i+1,' ')
                i+=1
        i+=1
    return ''.join(radno)

def PraznoPoslijeBroja(ulaz):
    radno=[i for i in ulaz]
    i=0
    while i < len(radno):
        if(radno[i] in brojevi and i+1 < len(radno) and radno[i+1] in abeceda):
            j=i+1
            while(radno[j] in brojevi):
                j+=1
            radno.insert(j, ' ')
        i+=1
    return ''.join(radno)

def jelBroj(brojcina):
    try:
        int(brojcina)
    except ValueError:
        return False
    return True    
    
tempUlaz1 = input()
red=1
try:
    while True:
        tempUlaz2 = DodajRazmake(tempUlaz1)
        tempUlaz3 = MakniRazmake(tempUlaz2)
        Ulaz = PraznoPoslijeBroja(tempUlaz3)
        Ulaz = Ulaz.split(' ')
        for skup in Ulaz:
            if skup in mapaKljRijeci.keys():
                print(f"{mapaKljRijeci[skup]} {red} {skup}")
            elif skup in mapaOperatora.keys():
                print(f"{mapaOperatora[skup]} {red} {skup}")
            elif jelBroj(skup):
                print(f"BROJ {red} {skup}")
            elif skup == "//":
                break
            elif skup == "":
                break
            else:
                print(f"IDN {red} {skup}")
        tempUlaz1=input()
        red+=1
except Exception:
    pass