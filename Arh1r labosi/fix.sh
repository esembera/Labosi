#!/bin/bash
for i in /usr/local/lib/atlas/lib/frisc.{2,4}/*.cdl; do
	[ -f "$i" ] || break
	sed -i 's/\xe8/c/g; s/\xe6/c/g; s/\x9a/s/g; s/\x9e/z/g' $i
done
