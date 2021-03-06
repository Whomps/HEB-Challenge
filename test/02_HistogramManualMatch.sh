#!/bin/bash
# This test verifies that the histogram generated matches one manually calculated

# Reset for this test
rm -f output.txt

# Trial 1
java -classpath "../bin/" Histogram Input_01.txt output.txt

# Diff against output 1
diff "output.txt" "Solution_01.txt" > /dev/null 2>&1
if [ $? -eq 0 ]; then
    printf "PASS 02a: Solutions match.\n"
else
    printf "FAILURE 02a: Solutions do not match. \n"
fi

# Trial 2
java -classpath "../bin/" Histogram Input_02.txt output.txt

# Diff against output 2
diff "output.txt" "Solution_02.txt" > /dev/null 2>&1
if [ $? -eq 0 ]; then
    printf "PASS 02b: Solutions match.\n"
else
    printf "FAILURE 02b: Solutions do not match. \n"
fi 