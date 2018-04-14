#!/bin/bash
# This test simply verifies an output file is generated by the program

# Reset for this test
rm -f output.txt

# Trial
java -classpath "../bin/" Histogram Input_01.txt output.txt

# Verify
if [ ! -f /tmp/foo.txt ]; then
    printf "\nFAILURE 01: Output file not generated.\n"
else
    printf "\nPASS 01: Output file generated.\n"
fi

