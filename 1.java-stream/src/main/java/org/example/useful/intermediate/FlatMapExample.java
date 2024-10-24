package org.example.useful.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream의 중간 메서드 flatMap()은 스트림에 있는 요소들을 매핑 메서드를 통해 새로운 스트림을 만들고, 그 스트림들의 요소들을 리턴하는 연산입니다.
 * 아래 첫번째 예제는 flatMap()을 사용하지 않아 IntStream의 Stream을 가지고 있지만,
 * flatMap()을 사용하면, 배열들의 모든 요소를 가지고 있는 하나의 IntStream을 가지는 것을 볼 수 있습니다.
 * 또한 배열뿐만이 아닌 String과 같은 클래스에서도 사용할 수 있음을 볼 수 있습니다.
 */
public class FlatMapExample {
    public static void main(String[] args) {

        List<int[]> deltas = Arrays.asList(
                new int[]{1,2},
                new int[]{3,4},
                new int[]{5,6},
                new int[]{7,8,9}
        );

        Stream<int[]> noFlatMapStream = deltas.stream();
        Stream<IntStream> intStreams = noFlatMapStream.map(Arrays::stream);
        intStreams.forEach(ins ->ins.forEach(System.out::println));
        /*
            java.util.stream.IntPipeline$Head@7e9e5f8a
            java.util.stream.IntPipeline$Head@8bcc55f
            java.util.stream.IntPipeline$Head@58644d46
            java.util.stream.IntPipeline$Head@14dad5dc
         */


        Stream<int[]> stream = deltas.stream();
        IntStream intStream =
                stream.flatMapToInt(Arrays::stream);
        intStream.forEach(System.out::println);




        List<String> words = Arrays.asList(
                "Hello brother",
                "Wow this is crazy"
        );

        Stream<String> s = words.stream();
        Stream<String> s2 = s.flatMap(word -> Arrays.stream(word.split(" ")));
        s2.forEach(System.out::println);


    }

}
