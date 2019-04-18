package com.fcb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Practice14 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String args []) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("outputPractice14.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    static String abbreviation(String a, String b) {
        if(a.length() < b.length()){
            return "No";
        }

        int bPointer = 0;
        int aPointer = 0;
        int currentASize = a.length();
        while(aPointer < a.length()){
            char tmpB = bPointer < b.length() ? b.charAt(bPointer) : ' ';
            char tmpA = a.charAt(aPointer);
            if( tmpA == tmpB){
                bPointer++;
                aPointer++;
                System.out.println(tmpA + " - " + tmpB + " =");
            }else{
                if(isLowerCase(tmpA)){
                    if(toUpperCase(tmpA) == tmpB){
                        bPointer++;
                        aPointer++;
                        System.out.println(tmpA + " - " + tmpB + " =Upper");
                    }else{
                        currentASize--;
                        aPointer++;
                        if(currentASize < b.length()){
                            return "NO";
                        }
                        System.out.println(tmpA + " - " + tmpB + " =Delete");
                    }
                }else{
                    return "NO";
                }
            }
        }
        return currentASize == b.length() ? "YES" : "NO";
    }

    static char toLowerCase(char c){
        return (char)(c + 32);
    }
    static char toUpperCase(char c){
        return (char)(c - 32);
    }
    static boolean isLowerCase(char c){
        return c >= 97 && c <= 122;
    }

    /*

    10
XXVVnDEFYgYeMXzWINQYHAQKKOZEYgSRCzLZAmUYGUGILjMDET
XXVVDEFYYMXWINQYHAQKKOZEYSRCLZAUYGUGILMDETQVWU
PVJSNVBDXABZYYGIGFYDICWTFUEJMDXADhqcbzva
PVJSNVBDXABZYYGIGFYDICWTFUEJMDXAD
QOTLYiFECLAGIEWRQMWPSMWIOQSEBEOAuhuvo
QOTLYFECLAGIEWRQMWPSMWIOQSEBEOA
DRFNLZZVHLPZWIupjwdmqafmgkg
DRFNLZZVHLPZWI
SLIHGCUOXOPQYUNEPSYVDaEZKNEYZJUHFXUIL
SLIHCUOXOPQYNPSYVDEZKEZJUHFXUIHMGFP
RYASPJNZEFHEORROXWZFOVDWQCFGRZLWWXJVMTLGGnscruaa
RYASPJNZEFHEORROXWZFOVDWQCFGRZLWWXJVMTLGG
AVECtLVOXKPHIViTZViLKZCZAXZUZRYZDSTIHuCKNykdduywb
AVECLVOXKPHIVTZVLKZCZAXZUZRYZDSTIHCKN
wZPRSZwGIMUAKONSVAUBUgSVPBWRSTJZECxMTQXXA
ZPRSZGIMUAKONSVAUBUSVPBWRSTJZECMTQXXA
SYIHDDSMREKXOKRFDQAOZJQXRIDWXPYINFZCEFYyxu
SYIHDDSMREKXOKRFDQAOZJQXRIDWXPYINFZCEFY
EIZGAWWDCSJBBZPBYVNKRDEWVZnSSWZIw
EIZGAWWDCSJBBZPBYVNKRDEWVZSSWZI
     */

}
