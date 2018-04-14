#!/bin/bash
# This wrapper runs each of the tests in the ##_[TestName].sh 
#  format within the current directory and prints out the
#  success or failure for each test.

TESTLOG="TestLog.txt"

rm -f $TESTLOG
printf "Test of Histogram.java at $(date)\n\n" > $TESTLOG

./01_OutputFileGenerated.sh >> $TESTLOG
./02_HistogramManualMatch.sh >> $TESTLOG

printf "\nTest completed at $(date)" >> $TESTLOG

cat $TESTLOG