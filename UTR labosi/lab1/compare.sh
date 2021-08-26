#!/bin/bash

for i in {01..33}
do
	echo "Testing $i"
	python3 SimEnka.py < testExamples/test$i/test.a | diff testExamples/test$i/test.b -
done
