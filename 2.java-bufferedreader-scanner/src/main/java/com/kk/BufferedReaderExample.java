package com.kk;

import java.io.*;

/*
    Scanner와 BufferedReader의 성능차이를 알아보는 예제 중 BufferedReader의 대한 예제입니다.
    BufferedReader에서 30MB 가량의 텍스트 파일을 읽어 모두 출력하는데 570ms의 시간이 걸렸고,
    Scanner와 동일한 1024의 버퍼 사이즈를 갖더라도 611ms로 Scanner보다 빠르다는 것을 볼 수 있습니다.
   이를 통해 BufferedReader와 Scanner의 성능 차이의 본질은 Buffer의 사이즈는 아니라는 것을 알 수 있습니다.
 */
public class BufferedReaderExample {
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
        BufferedReader br = new BufferedReader(new FileReader("sample.txt"), 1024);

        long start = System.currentTimeMillis();
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        System.out.println(System.currentTimeMillis() - start);
        // default(8192) 570ms buffer-size 1024 -> 611ms
    }
}
