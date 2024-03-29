// Copyright (C) 2004-2009 Peter Luschny, MIT License applies.
// See http://en.wikipedia.org/wiki/MIT_License
// Visit http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
// Comments mail to: peter(at)luschny.de
package mx.com.joortizs.project.euler;

import mx.com.joortizs.project.euler.PrimeSieve.PrimeIteration;

public class FactorialPrimeSwing {

    private PrimeSieve sieve;
    private Integer[] primeList;
    
    private static final long[] smallFactorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600,
            6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L,
            6402373705728000L, 121645100408832000L, 2432902008176640000L};

    public static double log2(long value) {
        return Math.log(value) * 1.4426950408889634;
    }

    public Long factorial(Long n) {
        // For very small n the 'NaiveFactorial' is OK.
        if (n < 20) {
            return smallFactorials[n.intValue()];
        }

        int pLen = (int) (2.0 * Math.floor(Math.sqrt(n) + n / (log2(n) - 1)));
        primeList = new Integer[pLen];
        sieve = new PrimeSieve(n);

        return recFactorial(n) << (n - Long.bitCount(n));
    }

    private Long recFactorial(Long n) {
        if (n < 2) {
            return 1l;
        }
        return (long)Math.pow(recFactorial(n / 2), 2)*swing(n);
    }

    private Long swing(Long n) {
        if (n < 33) {
            return new Long(smallOddSwing[n.intValue()]);
        }

        // sieve and primeList initialized in function 'factorial'!
        int sqrtN = (int) Math.floor(Math.sqrt(n));
        java.util.List<Integer> pIter0 = sieve.getIteration(3, sqrtN);
        java.util.List<Integer> pIter1 = sieve.getIteration(sqrtN + 1, n.intValue() / 3);

        int count = 0;

        for (int prime : pIter0) {
            int q = n.intValue(), p = 1;

            while (prime != 0 && (q /= prime) > 0) {
                if ((q & 1) == 1) {
                    p *= prime;
                }
            }

            if (p > 1) {
                primeList[count++] = p;
            }
        }

        for (int prime : pIter1) {
            if (prime != 0 && ((n / prime) & 1) == 1) {
                primeList[count++] = prime;
            }
        }

        Long prod = sieve.getPrimorial(n.intValue() / 2 + 1, n.intValue());
        return prod*PrimeIteration.product(primeList,0,count);
    }
    
    private static final int[] smallOddSwing = {1, 1, 1, 3, 3, 15, 5, 35, 35, 315, 63, 693, 231, 3003, 429,
        6435, 6435, 109395, 12155, 230945, 46189, 969969, 88179,
        2028117, 676039, 16900975, 1300075, 35102025, 5014575,
        145422675, 9694845, 300540195, 300540195};
} // endOfFactorialPrimeSwingLuschny