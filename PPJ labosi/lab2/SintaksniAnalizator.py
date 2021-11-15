br_razmak = 0
ulaz = input().split(' ')
ispis = []


def err():
    if ulaz[0] == "":
        print("err kraj")
    else:
        print("err " + " ".join(ulaz))
    exit(0)


def ispis1():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer = " " * br_razmak
    ispis.append(printer + ' '.join(ulaz))
    br_razmak -= 1


def lista_naredbi():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<lista_naredbi>")
    if (ulaz[0] == "IDN" or ulaz[0] == "KR_ZA"):
        naredba()
        lista_naredbi()
    elif (ulaz[0] == "KR_AZ" or ulaz[0] == ""):
        printer += " "
        ispis.append(printer + "$")
    br_razmak -= 1


def program():
    global br_razmak
    global ulaz
    global ispis
    ispis.append("<program>")
    if (ulaz[0] == "IDN" or ulaz[0] == "KR_ZA" or ulaz[0] == ""):
        lista_naredbi()
    else:
        err()


def naredba():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<naredba>")
    if (ulaz[0] == "IDN"):
        naredba_pridruzivanja()
    elif (ulaz[0] == "KR_ZA"):
        za_petlja()
    else:
        err()
    br_razmak -= 1


def naredba_pridruzivanja():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<naredba_pridruzivanja>")
    ispis1()
    ulaz = input().split(' ')
    if (ulaz[0] == "OP_PRIDRUZI"):
        ispis1()
        try:
            ulaz = input().split(" ")
        except Exception:
            ulaz = [""]
            pass
        e()
    else:
        err()
    br_razmak -= 1


def za_petlja():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += " " * br_razmak
    ispis.append(printer + "<za_petlja>")
    ispis1()
    ulaz = input().split(' ')
    if (ulaz[0] == "IDN"):
        ispis1()
        try:
            ulaz = input().split(" ")
        except Exception:
            ulaz = [""]
            pass
        if (ulaz[0] == "KR_OD"):
            ispis1()
            try:
                ulaz = input().split(" ")
            except Exception:
                ulaz = [""]
                pass
            e()
            if (ulaz[0] == "KR_DO"):
                ispis1()
                try:
                    ulaz = input().split(" ")
                except Exception:
                    ulaz = [""]
                    pass
                e()
                lista_naredbi()
                if (ulaz[0] == "KR_AZ"):
                    ispis1()
                    try:
                        ulaz = input().split(' ')
                    except Exception:
                        ulaz = [""]
                        pass
                else:
                    err()
            else:
                err()
        else:
            err()
    else:
        err()
    br_razmak -= 1


def e():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<E>")
    if (ulaz[0] == "IDN" or ulaz[0] == "BROJ" or ulaz[0] == "OP_PLUS" or ulaz[0] == "OP_MINUS" or ulaz[
        0] == "L_ZAGRADA"):
        t()
        e_lista()
    else:
        err()
    br_razmak -= 1


def e_lista():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<E_lista>")
    if (ulaz[0] == "OP_PLUS" or ulaz[0] == "OP_MINUS"):
        ispis1()
        try:
            ulaz = input().split(" ")
        except Exception:
            ulaz = [""]
            pass
        e()
    elif (ulaz[0] == "IDN" or ulaz[0] == "KR_ZA" or ulaz[0] == "KR_DO" or ulaz[0] == "KR_AZ" or ulaz[
        0] == "D_ZAGRADA" or ulaz[0] == ""):
        printer += " "
        ispis.append(printer + "$")
    else:
        err()
    br_razmak -= 1


def t():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<T>")
    if (ulaz[0] == "IDN" or ulaz[0] == "BROJ" or ulaz[0] == "OP_PLUS" or ulaz[0] == "OP_MINUS" or ulaz[
        0] == "L_ZAGRADA"):
        p()
        t_lista()
    else:
        err()
    br_razmak -= 1


def t_lista():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<T_lista>")
    if (ulaz[0] == "OP_PUTA" or ulaz[0] == "OP_DIJELI"):
        ispis1()
        try:
            ulaz = input().split(" ")
        except Exception:
            ulaz = [""]
            pass
        t()
    elif (ulaz[0] == "IDN" or ulaz[0] == "KR_ZA" or ulaz[0] == "KR_DO" or ulaz[0] == "KR_AZ" or ulaz[
        0] == "D_ZAGRADA" or ulaz[0] == "" or ulaz[0] == "OP_PLUS" or ulaz[0] == "OP_MINUS"):
        printer += " "
        ispis.append(printer + "$")
    else:
        err()
    br_razmak -= 1


def p():
    global ulaz
    global br_razmak
    global ispis
    br_razmak += 1
    printer = ""
    printer += ' ' * br_razmak
    ispis.append(printer + "<P>")
    if (ulaz[0] == "OP_PLUS" or ulaz[0] == "OP_MINUS"):
        ispis1()
        ulaz = input().split(" ")
        p()
    elif (ulaz[0] == "L_ZAGRADA"):
        ispis1()
        ulaz = input().split(" ")
        e()
        if (ulaz[0] == "D_ZAGRADA"):
            ispis1()
            try:
                ulaz = input().split(" ")
            except Exception:
                ulaz = [""]
                pass
        else:
            err()
    elif (ulaz[0] == "IDN" or ulaz[0] == "BROJ"):
        ispis1()
        try:
            ulaz = input().split(" ")
        except Exception:
            ulaz = [""]
            pass
    else:
        err()
    br_razmak -= 1

if __name__=="__main__":
    program()
    print("\n".join(ispis))
