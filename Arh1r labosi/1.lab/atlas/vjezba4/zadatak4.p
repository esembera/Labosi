                       CTCR `EQU 0FFFF0000 
                       CTLR `EQU 0FFFF0004 
                       CTACK `EQU 0FFFF0008 
                       CTEND `EQU 0FFFF000C 
                       DMA_SRC `EQU 0FFFF1000 
                       DMA_DEST `EQU 0FFFF1004 
                       DMA_SIZE `EQU 0FFFF1008 
                       DMA_CTRL `EQU 0FFFF100C 
                       DMA_START `EQU 0FFFF1010 
                       DMA_BS `EQU 0FFFF1014 
                       BVJ `EQU 0FFFFFFFC 
                        
                       `ORG 0 
00000000  00 00 81 07  	MOVE 10000, SP 
00000004  44 00 00 C4  	JP GLAVNI 
                        
                       `ORG 0C 
0000000C  00 00 00 88  	PUSH R0 
00000010  00 00 20 00  	MOVE SR, R0 
00000014  00 00 00 88  	PUSH R0 
00000018  14 10 0F B8  	STORE R0, (DMA_BS) 
0000001C  04 00 20 34  	SUB R2, 4, R0 
00000020  FF FF 8F 04  	MOVE -1, R1 
00000024  00 00 80 BC  	STORE R1, (R0) 
00000028  B0 00 00 B0  	LOAD R0, (BLOKOVI) 
0000002C  01 00 00 24  	ADD R0, 1, R0 
00000030  B0 00 00 B8  	STORE R0, (BLOKOVI) 
00000034  00 00 00 80  	POP R0 
00000038  00 00 10 00  	MOVE R0, SR 
0000003C  00 00 00 80  	POP R0 
                       	 
00000040  03 00 00 D8  	RETN 
                       	 
                       GLAVNI 
00000044  00 10 00 05  	MOVE 1000, R2 
00000048  E8 03 00 04  	MOVE %D 1000, R0 
0000004C  04 00 0F B8  	STORE R0, (CTLR) 
00000050  01 00 00 04  	MOVE 1, R0 
00000054  00 00 0F B8  	STORE R0, (CTCR) 
                       PETLJA 
00000058  B0 00 00 B0  	LOAD R0, (BLOKOVI) 
0000005C  05 00 00 6C  	CMP R0, 5 
00000060  A0 00 C0 C5  	JP_EQ KRAJ 
                       	 
00000064  08 00 0F B0  	LOAD R0, (CTACK) 
00000068  01 00 00 14  	AND R0, 1, R0 
0000006C  E8 FF CF D5  	JR_Z PETLJA 
                       	 
00000070  08 00 0F B8  	STORE R0, (CTACK) 
                        
                       	 
00000074  FC FF 0F 04  	MOVE 0FFFFFFFC, R0 
00000078  00 10 0F B8  	STORE R0, (DMA_SRC) 
0000007C  04 10 0F B9  	STORE R2, (DMA_DEST) 
00000080  28 00 20 25  	ADD R2, 28, R2 
00000084  09 00 00 04  	MOVE 9, R0 
00000088  08 10 0F B8  	STORE R0, (DMA_SIZE) 
0000008C  07 00 00 04  	MOVE %B 0111, R0 
00000090  0C 10 0F B8  	STORE R0, (DMA_CTRL) 
00000094  10 10 0F B8  	STORE R0, (DMA_START) 
00000098  0C 00 0F B8  	STORE R0, (CTEND) 
0000009C  58 00 00 C4  	JP PETLJA 
                       	 
                       	 
                        
                       KRAJ 
000000A0  00 00 00 04  	MOVE 0, R0 
000000A4  00 00 0F B8  	STORE R0, (CTCR) 
000000A8  0C 10 0F B8  	STORE R0, (DMA_CTRL) 
000000AC  00 00 00 F8  	HALT 
                        
                        
                        
000000B0  00 00 00 00  BLOKOVI `DW 00,00,00,00 
