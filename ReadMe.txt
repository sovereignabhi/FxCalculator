Assumptions and Instructions
1. JDK 11 has been used for this coding exercise.
2. To run the program, run FXCalculatorApplication's main method. Input/Outputs are displayed on console.
3. Assumption - Currency rates and Cross Currency Tables are provided for this solution.
4. Currency Rates and Cross-Over Currency values provided are stored within Java Code. For larger sample size, these should be stored in DB tables.
5. For final converted amount, rounding is done using the default HALF_EVEN mode.
6. For certain test assertions, conversion rate for currencies has been derived using Calculator to verify that code calculations are accurate.
7. For big numbers, input/output numbers might be displayed in scientific notation, also known as exponential notation.
8. The logic used to determine cross-currency is based on deductions made from Cross Currency matrix. The entire matrix is not used.
   The information is trimmed to capture only what is needed to make all conversion calculations. 
   That's why cross currency table is not implemented like AUD/NZD -> USD mapping.
   I can explain the logic, should there be a need.

