                         
00000000  18 00 00 04      MOVE 18, R0 
00000004  05 10 80 07      MOVE 1005, R7 
00000008  FF 00 00 07      MOVE 0FF, R6 
                       PETLJA 
0000000C  00 05 80 B4      LOAD R1, (R0+500) 
00000010  00 00 92 08      OR R1,R1,R1 
00000014  50 00 40 C4      JP_N NEGATIVNI 
                            
                       KOMPARIRANJE1 
00000018  7F 00 10 6C      CMP R1, %D 127 
0000001C  1C 00 00 D7      JR_SLE MANJE_JEDNAK 
                            
                       KOMPER 
00000020  7F 00 10 6C      CMP R1,%D 127 
00000024  34 00 00 D7      JR_SLE MANJA 
                            
                       VECI   
00000028  00 00 70 9F      STOREB R6, (R7) 
0000002C  01 00 F0 37      SUB R7, 1, R7 
00000030  04 00 00 34      SUB R0, 4, R0 
00000034  0C 00 00 C6      JP_NZ PETLJA 
00000038  00 00 00 F8      HALT 
                       MANJE_JEDNAK 
0000003C  00 00 F0 9C      STOREB R1, (R7) 
00000040  01 00 F0 37      SUB R7, 1, R7 
00000044  04 00 00 34      SUB R0, 4, R0 
00000048  0C 00 00 C6      JP_NZ PETLJA 
0000004C  00 00 00 F8      HALT 
                       NEGATIVNI     
00000050  FF FF 9F 1C      XOR R1, -1, R1 
00000054  01 00 90 24      ADD R1, 1, R1 
00000058  20 00 00 C4      JP KOMPER 
                            
                       MANJA 
0000005C  80 00 90 0C      OR R1, 80, R1 
00000060  00 00 F0 9C      STOREB R1, (R7) 
00000064  01 00 F0 37      SUB R7, 1, R7 
00000068  04 00 00 34      SUB R0, 4, R0 
0000006C  0C 00 00 C6      JP_NZ PETLJA 
00000070  00 00 00 F8      HALT 
                            
                            
                       `ORG 500 
00000500  06 00 00 00  `DW 06,00,00,00,FF,FF,FF,FF,02,00,00,00,23,01,00,00,25,00
          FF FF FF FF  
          02 00 00 00  
          23 01 00 00  
          25 00 00 00  
          44 FD FF FF  
          DF FF FF FF  
