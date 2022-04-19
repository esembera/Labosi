from Crypto.Hash import SHA256
from Crypto.Protocol.KDF import scrypt
from Crypto.Random import get_random_bytes
from getpass import getpass

import os
import sys
import pickle
import re

filename = "passwordi"
ulaz = sys.argv
reg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{10,}$"
match_re = re.compile(reg)
if ulaz[1] == "add":
    if not os.path.isfile(filename):
        passwordi = {}
        with open (filename, 'wb') as outfile:
            pickle.dump(passwordi, outfile)
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash1 = SHA256.new()
    hash1.update(ulaz[2].encode())
    username = hash1.digest()
    password1 = getpass('Password (At least one capital letter, one number, and length should be at least 10):')
    if(not re.search(match_re, password1)):
        print('Password did not meet the requirements.')
        exit(0)
    password2 = getpass('Repeat Password:')
    if(password1 != password2):
        print('User add failed. Password mismatch.')
        exit()
    salt = get_random_bytes(16)
    password = scrypt(password1, salt, 16, 2**16, 8, 1)
    forcepass = False
    passwordi[username] = (password, salt, forcepass)
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)
    print('User add successfuly added.')

if ulaz[1] == 'passwd':
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash1 = SHA256.new()
    hash1.update(ulaz[2].encode())
    username = hash1.digest()
    password1 = getpass('Password (At least one capital letter, one number, and length should be at least 10):')
    if(not re.search(match_re, password1)):
        print('Password change failed. Password did not meet the requirements.')
        exit(0)
    password2 = getpass('Repeat Password:')
    if(password1 != password2):
        print('Password change failed. Password mismatch.')
        exit()
    passHashed = scrypt(password1, passwordi[username][1], 16, 2**16, 8, 1)
    if(passwordi[username][0] == passHashed):
        print('Password change failed. Entered password is the same as the old one.')
        exit()
    salt = get_random_bytes(16)
    password = scrypt(password1, salt, 16, 2**16, 8, 1)
    forcepass = False
    passwordi[username] = (password, salt, forcepass)
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)
    print('Password change successful.')

if ulaz[1] == 'forcepass':
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash1 = SHA256.new()
    hash1.update(ulaz[2].encode())
    username = hash1.digest()
    tempList = list(passwordi[username])
    tempList[2] = True
    passwordi[username] = tempList
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)
    print('User will be requested to change password on next login.')

if ulaz[1] == 'del':
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash1 = SHA256.new()
    hash1.update(ulaz[2].encode())
    username = hash1.digest()
    passwordi.pop(username)
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)
    print('User successfuly removed.')