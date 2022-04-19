#!/bin/bash
echo "--Automatsko ispitivanje funkcionalnosti koda--"
echo -e "\n\nDodavanje novog korisnika:"
echo -e "python3 usermgmt.py add testUser"
python3 usermgmt.py add testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nNeuspjesna izmjena lozinke postojecem korisniku zbog neispunjavanja minimalnih zahtjeva:"
echo -e "python3 usermgmt.py passwd testUser"
python3 usermgmt.py passwd testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nNeuspjesna izmjena lozinke postojecem korisniku zbog upisa razlicitog passworda prilikom potvrde:"
echo -e "python3 usermgmt.py passwd testUser"
python3 usermgmt.py passwd testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nNeuspjesna izmjena lozinke postojecem korisniku zbog koristenja istog passworda kao i prije:"
echo -e "python3 usermgmt.py passwd testUser"
python3 usermgmt.py passwd testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nPrisilno tjeranje korisnika da promijeni password prilikom sljedeceg logina:"
echo "python3 usermgmt.py forcepass testUser\n"
python3 usermgmt.py forcepass testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nLogin nakon trazenja izmjene lozinke:"
echo "python3 login.py testUser"
python3 login.py testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nBrisanje korisnika:"
echo -e "python3 usermgmt.py del testUser"
python3 usermgmt.py del testUser
read -n 1 -p $"Pritisnite Enter za nastavak..."
echo -e "\nPokusaj logina nepostojeceg korisnika:"
echo "python3 login.py testUser1"
python3 login.py testUser1
echo -e "\n\n--Kraja rada automatskog ispitivanja funkcionalnosti koda--"
exec bash;
