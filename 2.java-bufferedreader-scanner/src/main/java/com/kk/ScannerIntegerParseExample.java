package com.kk;

import java.util.Scanner;

/**
 * Scanner가 정규표현식을 이용해서 파싱하는 것에 대한 예제입니다.
 * 본래 Integer.parseInt()에서 불가능한 값인 1,234,567을 파싱하는 것을 볼 수 있습니다.
 */
public class ScannerIntegerParseExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner("1,234,567");
        int number = sc.nextInt();
        System.out.println(number);
    }
}
