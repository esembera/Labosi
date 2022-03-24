#!/bin/bash

rm -rf lab1py &> /dev/null
rm 0123456789.zip &> /dev/null

mkdir lab1py
cp solution.py lab1py/
zip -r 0123456789.zip lab1py/ &> /dev/null
cp 0123456789.zip ../autograder/solutions/0123456789/

pushd ../autograder &> /dev/null

python autograder.py lab1

popd &> /dev/null

rm -rf lab1py
rm 0123456789.zip &> /dev/null
