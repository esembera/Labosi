#!/bin/bash
echo "--Automatsko ispitivanje funkcionalnosti koda--"
echo -e "\n\nInicijaliziranje alata:"
echo -e "python tajnik.py init masterPassword\n"
python tajnik.py init masterPassword
read -n 0 -p "Pritisnite Enter za nastavak..."
echo -e "\nUpis adrese i lozinke:"
echo -e "python tajnik.py put masterPassword www.fer.hr password\n"
python tajnik.py put masterPassword www.fer.hr password
read -n 0 -p "Pritisnite Enter za nastavak..."
echo -e "\nDohvacanje pohranjene lozinke:"
echo "python tajnik.py get masterPassword www.fer.hr"
python tajnik.py get masterPassword www.fer.hr
echo -e "\n"
read -n 0 -p "Pritisnite Enter za nastavak..."
echo -e "\nIzmjena postojece lozinke:"
echo -e "python tajnik.py put masterPassword www.fer.hr JakPassword\n"
python tajnik.py put masterPassword www.fer.hr JakPassword
read -n 0 -p "Pritisnite Enter za nastavak..."
echo -e "\nDokaz da je lozinka izmijenjena:"
echo "python tajnik.py get masterPassword www.fer.hr"
python tajnik.py get masterPassword www.fer.hr
echo -e "\n"
read -n 0 -p "Pritisnite Enter za nastavak..."
echo -e "\nPokusaj dohvata lozinke sa krivim master passwordom:"
echo "python tajnik.py get wrongPassword www.fer.hr"
python tajnik.py get wrongPassword www.fer.hr
echo -e "\n\n--Kraja rada automatskog ispitivanja funkcionalnosti koda--"
exec bash;
