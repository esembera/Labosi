                       GLAVNI  
00000000  81 D4 A0 E3      MOV R13, #81<8 
00000004  01 34 A0 E3      MOV R3, #1<8 
00000008  04 24 A0 E3      MOV R2, #4<8 
0000000C  02 10 A0 E3      MOV R1, #2 
                       PETLJA 
00000010  04 40 93 E4      LDR R4, [R3], #4 
00000014  08 0E 54 E3      CMP R4, #8<28 
00000018  0B 00 00 0A      BEQ KRAJ 
0000001C  10 00 2D E9      STMFD R13!, {R4} 
00000020  0A 00 00 EB      BL KUB 
00000024  04 D0 8D E2      ADD R13, R13, #4 
00000028  01 50 55 E2      SUBS R5, R5, #1 
0000002C  94 01 06 E0      MUL R6, R4, R1 
00000030  60 00 2D E9      STMFD R13!, {R5,R6} 
00000034  0C 00 00 EB      BL DIV 
00000038  08 D0 8D E2      ADD R13, R13, #8 
0000003C  04 50 82 E4      STR R5, [R2], #4 
00000040  F4 FF FF EA      B PETLJA 
                        
                       KRAJ  
00000044  56 34 12 EF  	SWI 123456 
                        
                        
                        
                        
                       KUB 
00000048  02 00 2D E9      STMFD R13!, {R1} 
0000004C  04 50 8D E2      ADD R5, R13, #4 
00000050  02 00 95 E8      LDMFD R5, {R1} 
00000054  91 01 05 E0      MUL R5, R1, R1 
00000058  95 01 05 E0      MUL R5, R5, R1 
0000005C  02 00 BD E8      LDMFD R13!, {R1} 
00000060  0E F0 A0 E1      MOV PC,LR 
                        
                       DIV 
00000064  0E 00 2D E9      STMFD R13!, {R1, R2, R3} 
00000068  00 30 A0 E3      MOV R3, #0 
0000006C  0C 50 8D E2      ADD R5, R13, #0C 
00000070  06 00 95 E8      LDMFD R5, {R1,R2} 
00000074  01 10 91 E1      ORRS R1, R1, R1 
00000078  02 00 00 5A      BPL DRUGI 
0000007C  00 10 61 E2      RSB R1, R1, #0 
                       DRUGI 
00000080  00 00 52 E3  	CMP R2, #0 
00000084  0B 00 00 0A  	BEQ NULICA 
00000088  02 20 92 E1      ORRS R2, R2, R2 
0000008C  02 00 00 5A      BPL LOOP 
00000090  00 20 62 E2      RSB R2, R2, #0 
                       LOOP  
00000094  02 10 51 E0      SUBS R1, R1, R2 
00000098  03 00 00 4A      BMI VAN 
0000009C  01 30 83 E2      ADD R3, R3, #1 
000000A0  FD FF FF 8A      BHI LOOP 
                       VAN 
000000A4  03 50 A0 E1  	MOV R5, R3 
000000A8  0E 00 BD E8       LDMFD R13!, {R1,R2,R3} 
000000AC  0E F0 A0 E1      MOV PC,LR 
                       NULICA 
000000B0  00 50 A0 E3  	MOV R5, #0 
000000B4  FC FF FF EA  	B VAN 
                        
                       `ORG 100 
00000100  00 00 00 00  `DW 00,00,00,00,03,00,00,00,06,00,00,00,FF,FF,FF,FF,FA,FF
          03 00 00 00  
          06 00 00 00  
          FF FF FF FF  
          FA FF FF FF  
          00 00 00 80  
                        
                        
                        
                        
