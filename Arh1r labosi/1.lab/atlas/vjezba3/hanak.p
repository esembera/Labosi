                       PRIMI_1 `EQU 0FFFF1000 ; lokacija za primanje podataka s 
                       SALJI_2 `EQU 0FFFF2000 ; lokacija za slanje na uvj2  
                       STANJE_2 `EQU 0FFFF2004 ; lokacija za provjeru stanja uvj
                        
                       PVJ3POD `EQU 0FFFF3000 ; lokacija za prijenos podataka pv
                       PVJ3IACK `EQU 0FFFF3004 ; lokacija za dojavu prihvata pre
                       PVJ3IEND `EQU 0FFFF3008 ; lokacija za dojavu kraja poslu≈
                       PVJ3STOP `EQU 0FFFF300C ; lokacija za zaustavljanje  pvj3
                        
                       `ORG 0 
00000000  00 00 81 07  	MOVE 10000, SP ; inicijalizacija stoga 
00000004  0C 00 00 C4  	JP GLAVNI ; preskakanje vektora 
                       `ORG 8 ; vektor za INT mora biti na adresi 8  
00000008  00 02 00 00  `DW 00,02,00,00 ; vektor je proizvoljan, ovdje je to 200 
                       GLAVNI   
0000000C  10 00 10 04  	MOVE %B 10000, SR; prekid GIE zastavica 
00000010  01 00 00 04  	MOVE 1, R0	 
00000014  0C 30 0F B8  	STORE R0, (PVJ3STOP); omoguci rad vanjske jedinice 
00000018  04 20 0F B0  LOOP 	LOAD R0, (STANJE_2); provjerava stanje uvj2; 
0000001C  00 00 00 08  	OR R0, R0, R0; postavljanje zastavica 
00000020  F4 FF CF D5  	JR_Z LOOP 
                       	 
00000024  28 02 00 B0  	LOAD R0, (BROJ) 
00000028  00 20 0F B8  	STORE R0, (SALJI_2) 
0000002C  04 20 0F B8  	STORE R0, (STANJE_2) 
00000030  2C 02 00 B0  	LOAD R0, (COUNT) 
00000034  01 00 00 24  	ADD R0, 1, R0 
00000038  2C 02 00 B8  	STORE R0, (COUNT) 
0000003C  18 00 00 C4  	JP LOOP 
                        
                       `ORG 200 
00000200  00 00 00 88  	PUSH R0 
00000204  00 00 20 00  	MOVE SR, R0 
00000208  00 00 00 88  	PUSH R0 
                       		 
0000020C  04 30 0F B8  	STORE R0, (PVJ3IACK) 
00000210  00 10 0F B0  	LOAD R0, (PRIMI_1) 
00000214  00 00 00 08  	OR R0, R0 ,R0 
00000218  14 00 80 D4  	JR_P POZ 
0000021C  00 00 00 04  	MOVE 0, R0 
00000220  0C 30 0F B8  	STORE R0, (PVJ3STOP) 
00000224  00 00 00 F8  	HALT 
                        
00000228  00 00 00 00  BROJ `DW 00,00,00,00 
0000022C  00 00 00 00  COUNT `DW 00,00,00,00 
                        
00000230  00 00 00 88  POZ	PUSH R0 
00000234  5C 02 00 CC  	CALL OBRADI 
00000238  28 02 00 B8  	STORE R0, (BROJ) 
0000023C  2C 02 00 B0  	LOAD R0, (COUNT) 
00000240  00 30 0F B8  	STORE R0, (PVJ3POD) 
00000244  04 00 F0 27  	ADD SP, 4, SP 
00000248  00 00 00 80  	POP R0 
0000024C  00 00 10 00  	MOVE R0, SR 
00000250  00 00 00 80  	POP R0 
00000254  08 30 0F B8  	STORE R0,(PVJ3IEND) 
00000258  01 00 00 D8  	RETI 
                        
                        
                       OBRADI 
0000025C  00 00 80 88      PUSH R1 
00000260  08 00 F0 B4      LOAD R1, (SP+08) 
00000264  02 00 00 04      MOVE %D 2, R0 
00000268  01 00 90 34      SUB R1, 1, R1 
0000026C  00 00 02 50      SHL R0, R1, R0 
00000270  00 00 80 80      POP R1 
00000274  00 00 00 D8      RET 
                       	 
                       	 
                        
