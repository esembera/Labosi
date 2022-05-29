#! /bin/sh

IPT=/sbin/iptables

$IPT -P INPUT DROP
$IPT -P OUTPUT DROP
$IPT -P FORWARD DROP

$IPT -F INPUT
$IPT -F OUTPUT
$IPT -F FORWARD

$IPT -A INPUT   -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPT -A OUTPUT  -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPT -A FORWARD -m state --state ESTABLISHED,RELATED -j ACCEPT

# 
# za potrebe testiranja dozvoljen je ICMP (ping i sve ostalo)
#
#$IPT -A INPUT   -p icmp -j ACCEPT
#$IPT -A FORWARD -p icmp -j ACCEPT
#$IPT -A OUTPUT  -p icmp -j ACCEPT


# ================ Dodajte ili modificirajte pravila na oznacenim mjestima #
# "anti spoofing" (eth0)
#
$IPT -A INPUT   -i eth0 -s 127.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth0 -s 127.0.0.0/8  -j DROP
#
# <--- Dodajte ili modificirajte pravila
#prvi zahtjev
$IPT -A FORWARD -i eth1 -o eth0 -j ACCEPT
$IPT -A FORWARD -i eth1 -o eth2 -j ACCEPT

#drugi zahtjev
$IPT -A FORWARD -i eth0 -o eth1 -j DROP

#tre?i zahtjev
$IPT -A FORWARD -i eth0 -p tcp --dport 22 -d 203.0.113.10 -j ACCEPT
$IPT -A FORWARD -i eth0 -p tcp --dport 53 -d 203.0.113.10 -j ACCEPT
$IPT -A FORWARD -i eth0 -p udp --dport 53 -d 203.0.113.10 -j ACCEPT

#?etvrti zahtjev
$IPT -A FORWARD -s 203.0.113.10 -p tcp --dport 53 -o eth0 -j ACCEPT
$IPT -A FORWARD -s 203.0.113.10 -p udp --dport 53 -o eth0 -j ACCEPT

#peti zahtjev
$IPT -A FORWARD -s 203.0.113.10 -d 10.0.0.11 -p tcp --dport 22 -j ACCEPT

#šesti zahtjev
$IPT -A INPUT -s 10.0.0.10 -p tcp --dport 22 -j ACCEPT 

#sedmi zahtjev
$IPT -A INPUT   -i eth1 -s 127.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth1 -s 127.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth2 -s 127.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth2 -s 127.0.0.0/8  -j DROP

$IPT -A INPUT   -i eth0 -s 0.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth0 -s 0.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth0 -s 10.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth0 -s 10.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth0 -s 172.16.0.0/12  -j DROP
$IPT -A FORWARD -i eth0 -s 172.16.0.0/12  -j DROP
$IPT -A INPUT   -i eth0 -s 192.168.0.0/16  -j DROP
$IPT -A FORWARD -i eth0 -s 192.168.0.0/16  -j DROP
$IPT -A INPUT   -i eth0 -s 224.0.0.0/3  -j DROP
$IPT -A FORWARD -i eth0 -s 224.0.0.0/3  -j DROP

$IPT -A INPUT   -i eth1 -s 0.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth1 -s 0.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth1 -s 10.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth1 -s 10.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth1 -s 172.16.0.0/12  -j DROP
$IPT -A FORWARD -i eth1 -s 172.16.0.0/12  -j DROP
$IPT -A INPUT   -i eth1 -s 192.168.0.0/16  -j DROP
$IPT -A FORWARD -i eth1 -s 192.168.0.0/16  -j DROP
$IPT -A INPUT   -i eth1 -s 224.0.0.0/3  -j DROP
$IPT -A FORWARD -i eth1 -s 224.0.0.0/3  -j DROP

$IPT -A INPUT   -i eth2 -s 0.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth2 -s 0.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth2 -s 10.0.0.0/8  -j DROP
$IPT -A FORWARD -i eth2 -s 10.0.0.0/8  -j DROP
$IPT -A INPUT   -i eth2 -s 172.16.0.0/12  -j DROP
$IPT -A FORWARD -i eth2 -s 172.16.0.0/12  -j DROP
$IPT -A INPUT   -i eth2 -s 192.168.0.0/16  -j DROP
$IPT -A FORWARD -i eth2 -s 192.168.0.0/16  -j DROP
$IPT -A INPUT   -i eth2 -s 224.0.0.0/3  -j DROP
$IPT -A FORWARD -i eth2 -s 224.0.0.0/3  -j DROP