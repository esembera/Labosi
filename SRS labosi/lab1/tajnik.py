from Crypto.Cipher import AES
from Crypto.Hash import SHA256
from Crypto.Protocol.KDF import HKDF
from Crypto.Random import get_random_bytes
import sys
import pickle


ulaz = sys.argv
if ulaz[1] == 'init':
    passwordi={}
    salt = get_random_bytes(16)
    key = HKDF(ulaz[2].encode(), 16, salt, SHA256, 1)
    hash = SHA256.new()
    hash.update(b'master')
    glavniHash = hash.digest()
    cipher = AES.new(key, AES.MODE_EAX)
    nonce = cipher.nonce
    ciphertext, tag = cipher.encrypt_and_digest(ulaz[2].encode())
    passwordi[glavniHash] = (ciphertext, nonce, tag, salt)
    filename = "passwordi"
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)

if ulaz[1] == 'put':
    filename = "passwordi"
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash = SHA256.new()
    hash.update(b'master')
    glavniHash = hash.digest()
    salt = passwordi[glavniHash][3]
    key = HKDF(ulaz[2].encode(), 16, salt, SHA256, 1)
    cipher = AES.new(key, AES.MODE_EAX, nonce=passwordi[glavniHash][1])
    decryptPass = cipher.decrypt(passwordi[glavniHash][0])
    try:
        cipher.verify(passwordi[glavniHash][2])  
    except ValueError:
        print("Master password incorrect or integrity check failed.")
        exit()
    hash = SHA256.new()
    hash.update(ulaz[3].encode())
    glavniHash = hash.digest()
    salt = get_random_bytes(16)
    key = HKDF(ulaz[2].encode(), 16, salt, SHA256, 1)
    cipher = AES.new(key, AES.MODE_EAX)
    nonce = cipher.nonce
    ciphertext, tag = cipher.encrypt_and_digest(ulaz[4].encode())
    passwordi[glavniHash] = (ciphertext, nonce, tag, salt) 
    with open (filename, 'wb') as outfile:
        pickle.dump(passwordi, outfile)

if ulaz[1] == 'get':
    filename = "passwordi"
    with open (filename, 'rb') as infile:
        passwordi = pickle.load(infile)
    hash = SHA256.new()
    hash.update(b'master')
    glavniHash = hash.digest()
    salt = passwordi[glavniHash][3]
    key = HKDF(ulaz[2].encode(), 16, salt, SHA256, 1)
    cipher = AES.new(key, AES.MODE_EAX, nonce=passwordi[glavniHash][1])
    decryptPass = cipher.decrypt(passwordi[glavniHash][0])
    try:
        cipher.verify(passwordi[glavniHash][2])  
    except ValueError:
        print("Master password incorrect or integrity check failed.")
        exit()
    hash = SHA256.new()
    hash.update(ulaz[3].encode())
    glavniHash = hash.digest()
    salt = passwordi[glavniHash][3]
    key = HKDF(ulaz[2].encode(), 16, salt, SHA256, 1)
    cipher = AES.new(key, AES.MODE_EAX, nonce=passwordi[glavniHash][1])
    decryptPass = cipher.decrypt(passwordi[glavniHash][0])
    try:
        cipher.verify(passwordi[glavniHash][2])  
    except ValueError:
        print("Master password incorrect or integrity check failed.")
        exit()
    print("Password for " + ulaz[3] + ' is: ' + decryptPass.decode())
    

