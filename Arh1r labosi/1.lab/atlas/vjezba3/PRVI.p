                       VJ1_DATA `EQU 0FFFF1000 
                       VJ2_DATA `EQU 0FFFF2000 
                       VJ2_BIST `EQU 0FFFF2004 
                       VJ3_DATA `EQU 0FFFF3000 
                       VJ3_IACK `EQU 0FFFF3004 
                       VJ3_IEND `EQU 0FFFF3008 
                       VJ3_STOP `EQU 0FFFF300C 
                        
                       `ORG 0 
00000000  00 00 81 07      MOVE 10000, SP 
00000004  0C 00 00 C4      JP GLAVNI 
                       `ORG 8 
00000008  00 01 00 00  `DW 00,01,00,00 
                        
                       GLAVNI 
0000000C  10 00 10 04      MOVE %B 10000, SR 
00000010  01 00 00 04  	MOVE 1, R0 
00000014  0C 30 0F B8  	STORE R0, (VJ3_STOP) 
                       LOOP 
00000018  04 20 0F B0      LOAD R0, (VJ2_BIST)  
0000001C  00 00 00 08      OR R0, R0, R0 
00000020  18 00 C0 C5      JP_Z LOOP 
                            
00000024  50 01 00 B0      LOAD R0, (BROJ) 
00000028  00 20 0F B8      STORE R0, (VJ2_DATA) 
0000002C  04 20 0F B8      STORE R0, (VJ2_BIST) 
00000030  54 01 00 B0      LOAD R0, (COUNTER) 
00000034  01 00 00 24      ADD R0, 1, R0 
00000038  54 01 00 B8      STORE R0, (COUNTER) 
0000003C  D8 FF 0F D4      JR LOOP 
                            
                       `ORG 100 
00000100  00 00 00 88      PUSH R0 
00000104  00 00 10 00      MOVE R0, SR 
00000108  00 00 00 88      PUSH R0 
0000010C  04 30 0F B8      STORE R0, (VJ3_IACK) 
00000110  00 10 0F B0      LOAD R0, (VJ1_DATA) 
00000114  00 00 00 08      OR R0, R0, R0 
00000118  48 01 40 C4      JP_N KRAJ 
0000011C  00 00 00 88      PUSH R0 
00000120  58 01 00 CC      CALL OBRADI 
00000124  50 01 00 B8      STORE R0, (BROJ) 
00000128  54 01 00 B0      LOAD R0, (COUNTER) 
0000012C  00 30 0F B8      STORE R0, (VJ3_DATA) 
00000130  04 00 F0 27      ADD R7, 4, R7 
00000134  00 00 00 80      POP R0 
00000138  00 00 10 00      MOVE R0, SR 
0000013C  00 00 00 80      POP R0 
00000140  08 30 0F B8      STORE R0, (VJ3_IEND) 
00000144  01 00 00 D8      RETI 
                        
                       KRAJ  
00000148  00 00 10 04  	MOVE 0, SR 
0000014C  00 00 00 F8  	HALT 
                             
00000150  00 00 00 00  BROJ `DW 00,00,00,00 
00000154  00 00 00 00  COUNTER `DW 00,00,00,00 
                        
                       OBRADI 
00000158  00 00 80 88      PUSH R1 
0000015C  08 00 F0 B4      LOAD R1, (SP+8) 
00000160  01 00 00 04      MOVE 1, R0 
00000164  00 00 02 50      SHL R0, R1, R0 
00000168  00 00 80 80      POP R1 
0000016C  00 00 00 D8      RET 
                        
