Write-Output "--Automatsko ispitivanje funkcionalnosti koda--"
Write-Output  "`n`nInicijaliziranje alata:"
Write-Output  "python ./tajnik.py init masterPassword`n"
python ./tajnik.py init masterPassword
Read-Host -Prompt "Pritisnite Enter za nastavak..."
Write-Output  "`nUpis adrese i lozinke:"
Write-Output  "python ./tajnik.py put masterPassword www.fer.hr password`n"
python ./tajnik.py put masterPassword www.fer.hr password
Read-Host -Prompt "Pritisnite Enter za nastavak..."
Write-Output  "`nDohvacanje pohranjene lozinke:"
Write-Output "python ./tajnik.py get masterPassword www.fer.hr"
python ./tajnik.py get masterPassword www.fer.hr
Write-Output  "`n"
Read-Host -Prompt "Pritisnite Enter za nastavak..."
Write-Output  "`nIzmjena postojece lozinke:"
Write-Output  "python ./tajnik.py put masterPassword www.fer.hr JakPassword`n"
python ./tajnik.py put masterPassword www.fer.hr JakPassword
Read-Host -Prompt "Pritisnite Enter za nastavak..."
Write-Output  "`nDokaz da je lozinka izmijenjena:"
Write-Output "python ./tajnik.py get masterPassword www.fer.hr"
python ./tajnik.py get masterPassword www.fer.hr
Write-Output  "`n"
Read-Host -Prompt "Pritisnite Enter za nastavak..."
Write-Output  "`nPokusaj dohvata lozinke sa krivim master passwordom:"
Write-Output "python ./tajnik.py get wrongPassword www.fer.hr"
python ./tajnik.py get wrongPassword www.fer.hr
Write-Output  "`n`n--Kraja rada automatskog ispitivanja funkcionalnosti koda--"
Read-Host -Prompt "Pritisnite enter za izlaz iz skripte"