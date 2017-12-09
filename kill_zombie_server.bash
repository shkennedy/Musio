#!/bin/bash

netstat -ano | findstr :8080 > tmp.txt

pid=$(grep 'LISTENING' tmp.txt | cut -d\   -f2)

echo $pid

rm tmp.txt

tskill $pid
