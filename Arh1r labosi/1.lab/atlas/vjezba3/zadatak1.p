                       VJ1 `EQU FFFF1000 
                       VJ2 `EQU FFFF2000 
                       VJ2_BS `EQU FFFF2004 
                       VJ3 `EQU FFFF3000 
                       VJ3_BS `EQU FFFF3004 
                       VJ3_ISDONE `EQU FFFF3008 
                       VJ3_STOP `EQU FFFF300C 
                        
                       `ORG 0 
00000000  00 00 81 07  	MOVE 10000, SP 
00000004  0C 00 00 C4  	JP GLAVNI 
                       	 
                       `ORG 8 
00000008  00 01 00 00  `DW 00,01,00,00 
                       	 
                       GLAVNI 
0000000C  10 00 10 04  	MOVE %B 10000, SR 
00000010  01 00 00 04  	MOVE 1, R0 
00000014  0C 30 0F B8  	STORE R0, (VJ3_STOP) 
                        
                       UVJETNA 
00000018  04 20 8F B0  	LOAD R1, (VJ2_BS) 
0000001C  00 00 92 08  	OR R1,R1,R1 
00000020  28 00 00 C6  	JP_NZ UVJ1 
00000024  18 00 00 C4  	JP UVJETNA 
                        
                       UVJ1 
00000028  70 01 00 B0  	LOAD R0, (BROJ) 
0000002C  74 01 80 B0  	LOAD R1, (COUNTER) 
00000030  01 00 90 24  	ADD R1, 1, R1 
00000034  74 01 80 B8  	STORE R1, (COUNTER) 
00000038  00 20 0F B8  	STORE R0, (VJ2) 
0000003C  04 20 0F B8  	STORE R0,(VJ2_BS) 
00000040  18 00 00 C4  	JP UVJETNA 
                        
                       `ORG 100 
00000100  00 00 00 88  	PUSH R0 
00000104  00 00 20 00  	MOVE SR, R0 
00000108  00 00 00 88  	PUSH R0 
0000010C  04 30 0F B8  	STORE R0, (VJ3_BS) 
00000110  00 10 0F B0  	LOAD R0, (VJ1) 
00000114  00 00 00 08  	OR R0,R0,R0 
00000118  64 01 40 C4  	JP_N KRAJ 
0000011C  00 00 00 88  	PUSH R0 
00000120  48 01 00 CC  	CALL OBRADI 
00000124  70 01 00 B8  	STORE R0, (BROJ) 
00000128  74 01 00 B0  	LOAD R0, (COUNTER) 
0000012C  00 30 0F B8  	STORE R0, (VJ3) 
00000130  04 00 F0 27  	ADD SP, 4, SP 
00000134  00 00 00 80  	POP R0 
00000138  00 00 10 00  	MOVE R0, SR 
0000013C  00 00 00 80  	POP R0 
00000140  08 30 0F B8  	STORE R0, (VJ3_ISDONE) 
00000144  01 00 00 D8  	RETI 
                        
                       OBRADI 
00000148  00 00 80 88  	PUSH R1 
0000014C  02 00 00 04  	MOVE %D 2, R0 
00000150  08 00 F0 B4  	LOAD R1, (SP+8) 
00000154  01 00 90 34  	SUB R1, 1, R1 
00000158  00 00 02 50  	SHL R0, R1, R0 
0000015C  00 00 80 80  	POP R1 
00000160  00 00 00 D8  	RET 
                       	 
                       KRAJ 
00000164  00 00 00 04  	MOVE 0, R0 
00000168  0C 30 0F B8  	STORE R0, (VJ3_STOP) 
0000016C  00 00 00 F8  	HALT 
                       	 
                       	 
                       	 
                        
00000170  00 00 00 00  BROJ `DW 00,00,00,00 
00000174  00 00 00 00  COUNTER `DW 00,00,00,00 
