package com.kk;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
    Scanner와 BufferedReader의 성능차이를 알아보는 예제 중 Scanner에 대한 예제입니다.
    Scanner에서 30MB 가량의 텍스트 파일을 읽어 모두 출력하는데 1159ms의 시간이 걸렸습니다.

 */
public class ScannerExample {
    public static void main(String[] args) throws IOException {
        File file = new File("sample.txt");
        Scanner sc = new Scanner(file);
        long start = System.currentTimeMillis();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start); // 1159ms
    }
}