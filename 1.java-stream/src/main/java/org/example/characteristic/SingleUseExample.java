package org.example.characteristic;

import java.util.List;
import java.util.stream.IntStream;

public class SingleUseExample {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        IntStream intStream = numbers.stream()
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    System.out.println("e : " + e);
                    return 1;
                });

        intStream.forEach(System.out::println);
        try {
            intStream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // stream has already been operated upon or closed
        }

        /*
            final <R> R evaluate(TerminalOp<E_OUT, R> terminalOp) {
                assert getOutputShape() == terminalOp.inputShape();
                if (linkedOrConsumed)
                    throw new IllegalStateException(MSG_STREAM_LINKED);
                linkedOrConsumed = true; <- 한번 사용하면 true가 되어서 다시 사용하게 되면 위의 조건문에 걸린다.

                return isParallel()
                       ? terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()))
                       : terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
            }
         */

    }
}
