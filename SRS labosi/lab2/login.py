from Crypto.Hash import SHA256
from Crypto.Protocol.KDF import scrypt
from Crypto.Random import get_random_bytes
from getpass import getpass
from Crypto.Hash import HMAC

import sys
import pickle
import re

filename = "passwordi"
ulaz = sys.argv
reg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{10,}$"
match_re = re.compile(reg)
with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
hash1 = SHA256.new()
hash1.update(ulaz[1].encode())
username = hash1.digest()
try:
    if not passwordi[username][2]:
        while True:
            password1 = getpass()
            password = scrypt(password1, passwordi[username][1], 16, 2**16, 8, 1)
            if passwordi[username][0] == password:
                print('Login successful.')
                sys.exit(1)
            print('Username or password incorrect.')
    else:
        while True:
            password1 = getpass()
            password = scrypt(password1, passwordi[username][1], 16, 2**16, 8, 1)
            if passwordi[username][0] == password:
                password1 = getpass('New password (At least one capital letter, one number, and length should be at least 10):')
                if(not re.search(match_re, password1)):
                    print('Password did not meet the requirements.')
                    continue
                password2 = getpass('Repeat new Password:')
                if(password1 != password2):
                    print('Password change failed. Password mismatch.')
                    continue
                passHashed = scrypt(password1, passwordi[username][1], 16, 2**16, 8, 1)
                if(passwordi[username][0] == passHashed):
                    print('Password change failed. Entered password is the same as the old one.')
                    continue
                salt = get_random_bytes(16)
                password = scrypt(password1, salt, 16, 2**16, 8, 1)
                forcepass = False
                passwordi[username] = (password, salt, forcepass)
                with open (filename, 'wb') as outfile:
                    pickle.dump(passwordi, outfile)
                print('Login successful.')
                sys.exit(1)
except Exception:
    print('User not found.')