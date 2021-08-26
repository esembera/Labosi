                       `ORG 0 
00000000  07 00 00 EA      B GLAVNI 
                       `ORG 18 
00000018  10 00 00 EA      B PREKIDNI 
                       GLAVNI 
0000001C  01 D8 A0 E3      MOV R13, #1<16 
00000020  30 30 9F E5      LDR R3, RTC 
00000024  01 44 A0 E3      MOV R4, #1<8 
00000028  04 40 83 E5      STR R4, [R3, #4] 
0000002C  01 40 A0 E3      MOV R4, #1 
00000030  10 40 83 E5      STR R4, [R3, #10] 
00000034  0D 00 A0 E3      MOV R0, #0D 
00000038  26 00 00 EB      BL LCDWR 
0000003C  00 00 0F E1      MRS R0, CPSR 
00000040  80 00 C0 E3      BIC R0, R0, #80 
00000044  00 F0 21 E1      MSR CPSR_c, R0 
                        
                        
                       PETLJA  
00000048  00 00 00 EA      B PETLJA 
                        
                        
0000004C  00 FF FF FF  GPIO `DW 00,FF,FF,FF 
00000050  00 FE FF FF  RTC `DW 00,FE,FF,FF 
00000054  00 00 00 00  INDEX `DW 00,00,00,00 
                            
                        
                       PREKIDNI 
00000058  1F 40 2D E9      STMFD R13!, {R0, R1, R2, R3, R4, R14} 
0000005C  04 44 A0 E3      MOV R4, #4<8 
00000060  F4 1F 1F E5      LDR R1, INDEX 
00000064  EC 3F 1F E5      LDR R3, RTC 
00000068  08 00 83 E5      STR R0, [R3, #8] 
0000006C  00 00 A0 E3      MOV R0, #0 
00000070  0C 00 83 E5      STR R0, [R3, #0C]  
00000074  0D 00 A0 E3      MOV R0, #0D 
00000078  16 00 00 EB      BL LCDWR 
0000007C  3E 00 A0 E3      MOV R0, #3E 
00000080  14 00 00 EB      BL LCDWR 
00000084  13 00 00 EB      BL LCDWR 
00000088  12 00 00 EB      BL LCDWR 
0000008C  04 00 91 E7      LDR R0, [R1, R4] 
00000090  00 00 50 E3      CMP R0, #0 
00000094  0B 00 00 0A      BEQ SKOK 
00000098  0E 00 00 EB      BL LCDWR 
0000009C  3C 00 A0 E3      MOV R0, #3C 
000000A0  0C 00 00 EB      BL LCDWR 
000000A4  0B 00 00 EB      BL LCDWR 
000000A8  0A 00 00 EB      BL LCDWR 
000000AC  0A 00 A0 E3      MOV R0, #0A 
000000B0  08 00 00 EB      BL LCDWR 
000000B4  01 10 81 E2      ADD R1, R1, #1 
000000B8  9C 1F 0F E5      STR R1, INDEX 
000000BC  03 00 00 EA      B KRAJ 
                        
                        
                       SKOK 
000000C0  00 00 A0 E3      MOV R0, #0 
000000C4  90 0F 0F E5      STR R0, INDEX 
                        
                        
                       KRAJ 
000000C8  1F 40 BD E8      LDMFD R13!, {R0, R1, R2, R3, R4, R14}  
000000CC  04 F0 5E E2      SUBS PC, R14, #4    
                        
                        
                       LCDWR 
000000D0  7C 2F 1F E5      LDR R2, GPIO 
000000D4  7F 00 00 E2      AND R0, R0, #7F 
000000D8  04 00 C2 E5      STRB R0, [R2, #4] 
000000DC  80 00 80 E3      ORR R0, R0, #80 
000000E0  04 00 C2 E5      STRB R0, [R2, #4] 
000000E4  7F 00 00 E2      AND R0, R0, #7F 
000000E8  04 00 C2 E5      STRB R0, [R2, #4] 
000000EC  0E F0 A0 E1      MOV PC, LR 
                        
                        
                       `ORG 400 
00000400  49 6E 74 65  `DW 49,6E,74,65,72,6E,61,74,69,6F,6E,61,6C,69,73,61,74,69
          72 6E 61 74  
          69 6F 6E 61  
          6C 69 73 61  
          74 69 6F 6E  
          00           
