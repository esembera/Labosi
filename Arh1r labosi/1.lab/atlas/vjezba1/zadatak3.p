00000000  58 00 80 04  	MOVE LABELA, R1 
00000004  00 00 81 07      MOVE 10000, SP 
00000008  00 10 00 07      MOVE 1000, R6 
                        
                       PETLJA 
0000000C  00 00 10 A5      LOADH R2, (R1) 
00000010  02 00 90 24      ADD R1, 2, R1 
00000014  00 00 90 A5      LOADH R3, (R1) 
00000018  02 00 90 24      ADD R1, 2, R1 
                        
0000001C  00 80 20 6C      CMP R2, 8000 
00000020  2C 00 00 C6      JP_NE DALJE 
00000024  00 80 30 6C      CMP R3, 8000 
00000028  54 00 C0 C5      JP_EQ KRAJ 
                       DALJE 
0000002C  10 00 20 55      SHL R2, %D 16, R2 
00000030  10 00 20 65      ASHR R2, %D 16, R2 
                        
00000034  10 00 B0 55      SHL R3, %D 16, R3 
00000038  10 00 B0 65      ASHR R3, %D 16, R3 
                        
0000003C  00 00 00 89      PUSH R2 
00000040  00 00 80 89      PUSH R3 
                        
00000044  78 00 00 CC      CALL COMP 
                        
00000048  00 00 60 BC      STORE R0, (R6) 
0000004C  04 00 60 27      ADD R6, 4, R6 
                        
00000050  0C 00 00 C4      JP PETLJA 
                        
                       KRAJ 
00000054  00 00 00 F8      HALT 
                        
00000058  21 43 2C 43  LABELA `DW 21,43,2C,43,FF,00,BA,7D,8B,54,8B,B7,DA,BA,DA,D
          FF 00 BA 7D  
          8B 54 8B B7  
          DA BA DA DE  
          8A 7C 39 1B  
          00 80 00 80  
00000070  00 00 00 FF  TMP_VAL `DW 00,00,00,FF 
00000074  00 00 00 00  CALL_ADDR `DW 00,00,00,00 
                        
                       COMP 
00000078  00 00 80 82      POP R5 
                        
0000007C  00 00 00 04      MOVE 0, R0 
                        
00000080  74 00 80 BA      STORE R5, (CALL_ADDR) 
                        
00000084  00 00 80 82      POP R5 
00000088  00 00 00 82      POP R4 
                        
0000008C  70 00 80 B1      LOAD R3, (TMP_VAL) 
00000090  00 00 00 05      MOVE 0, R2 
                        
00000094  00 00 4A 1A      XOR R4, R5, R4 
                        
                       PETLJA2 
00000098  00 00 C6 12      AND R4, R3, R5 
0000009C  00 00 50 6C      CMP R5, 0 
000000A0  AC 00 C0 C5      JP_EQ JEDNAKI 
000000A4  BB 00 00 0C      OR R0, 0BB, R0 
000000A8  B0 00 00 C4      JP DALJE2 
                        
                       JEDNAKI 
000000AC  AA 00 00 0C      OR R0, 0AA, R0 
                       DALJE2 
000000B0  03 00 20 6C      CMP R2, 3 
000000B4  C8 00 C0 C5      JP_EQ KRAJFUNC 
                        
000000B8  08 00 00 54      SHL R0, 8, R0 
000000BC  08 00 40 56      SHL R4, 8, R4 
                        
000000C0  01 00 20 25      ADD R2, 1, R2 
000000C4  98 00 00 C4      JP PETLJA2 
                        
                       KRAJFUNC 
                        
000000C8  74 00 80 B2      LOAD R5, (CALL_ADDR) 
000000CC  00 00 80 8A      PUSH R5 
000000D0  00 00 00 D8      RET 
