#!/bin/bash

for i in {31..39..2}
do
	echo "Testing $i"
	python3 SintaksniAnalizator.py < tests/$i*/test.in | diff tests/$i*/test.out -
done
