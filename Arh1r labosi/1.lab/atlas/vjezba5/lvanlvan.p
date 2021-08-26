00000000  81 D4 A0 E3  	MOV R13, #81<8 
00000004  01 04 A0 E3      MOV R0, #1<8 
00000008  04 14 A0 E3      MOV R1, #4<8 
0000000C  02 20 A0 E3      MOV R2, #2 
                       	 
                       LOOP1 
00000010  04 30 90 E4      LDR R3, [R0], #4 
00000014  08 0E 53 E3      CMP R3, #8<28 
00000018  0F 00 00 0A      BEQ KRAJ 
0000001C  00 00 53 E3  	CMP R3, #0 
00000020  0B 00 00 0A  	BEQ NULA 
                       	 
00000024  08 00 2D E9      STMFD R13!, {R3} 
00000028  3C 00 00 EB      BL KUB 
0000002C  01 40 54 E2      SUBS R4, R4, #1 
00000030  93 02 05 E0      MUL R5, R3, R2 
00000034  04 D0 8D E2  	ADD R13, R13, #4 
00000038  30 00 2D E9      STMFD R13!, {R4,R5} 
0000003C  3E 00 00 EB      BL DIV 
00000040  08 D0 8D E2      ADD R13, R13, #8 
00000044  04 40 81 E4  	STR R4, [R1], #4 
00000048  F2 FF FF EA      B LOOP1 
                       NULA 
0000004C  04 30 81 E4  	STR R3, [R1], #4 
00000050  F0 FF FF EA  	B LOOP1 
                       KRAJ  
00000054  56 34 12 EF  	SWI 123456 
                        
                       `ORG 100 
00000100  00 00 00 00  `DW 00,00,00,00,03,00,00,00,06,00,00,00,FF,FF,FF,FF,FA,FF
          03 00 00 00  
          06 00 00 00  
          FF FF FF FF  
          FA FF FF FF  
          00 00 00 80  
                        
                       KUB 
00000118  01 00 2D E9      STMFD R13!, {R0} 
0000011C  04 40 8D E2      ADD R4, R13, #4 
00000120  01 00 94 E8      LDMFD R4, {R0} 
00000124  90 00 04 E0      MUL R4, R0, R0 
00000128  94 00 04 E0      MUL R4, R4, R0 
0000012C  01 00 BD E8      LDMFD R13!, {R0} 
00000130  0E F0 A0 E1      MOV PC,LR 
                        
                       DIV 
00000134  0E 00 2D E9      STMFD R13!, {R1, R2, R3} 
00000138  00 40 E0 E3      MVN R4, #0 
0000013C  0C 30 8D E2      ADD R3, R13, #0C 
00000140  06 00 93 E8      LDMFD R3, {R1,R2} 
00000144  01 10 91 E1      ORRS R1, R1, R1 
00000148  02 00 00 5A      BPL DJELITELJ 
0000014C  00 10 61 E2      RSB R1, R1, #0 
                       DJELITELJ 
00000150  02 20 92 E1      ORRS R2, R2, R2 
00000154  02 00 00 5A      BPL LOOP2 
00000158  00 20 62 E2      RSB R2, R2, #0 
                       LOOP2 
0000015C  01 40 84 E2  	ADD R4, R4, #1 
00000160  02 10 51 E0      SUBS R1, R1, R2 
00000164  02 00 00 4A      BMI NULANEG 
00000168  FD FF FF 2A      BHS LOOP2 
                       	 
                       NULANEG 
0000016C  0E 00 BD E8      LDMFD R13!, {R1,R2,R3} 
00000170  0E F0 A0 E1      MOV PC,LR 
