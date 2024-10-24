package org.example.useful.terminate;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Stream의 최종 연산 중 Collect와 관련된 연산을 다룬 예제입니다.
 * Collect와 관련된 연산은 총 3가지로, 컬렉션 반환, 그룹화, 통계 반환이 있습니다.
 * 그 순서대로 예제가 구성되어 있습니다.
 */
public class CollectExample {
    public static void main(String[] args) {

        String[] names = {"신장구", "김철수", "유리", "훈이", "맹구", "흰둥이"};

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int nameIndex = new Random().nextInt(names.length);
            int age = new Random().nextInt(100);
            students.add(new Student(names[nameIndex], age, i));

        }

        students.stream()
//                .collect(Collectors.toList())// ArrayList로 변환
//                .collect(Collectors.toSet())// HashSet으로 변환
//                .collect(Collectors.toMap(student -> student.studentNo, student -> student.age)); // HashMap으로 변환
                .collect(Collectors.toCollection(TreeSet::new)); // 원하는 구현체 적용


//        Map<Integer, List<Student>> ageCollector =
        Map<Integer, Set<Student>> ageCollector = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.toSet()));
        Map<String, Double> nameAgeAvgCollector = students.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getAge)));


//        Map<Boolean, List<Student>> agePartitionList =
//        Map<Boolean, Set<Student>> agePartitionSet =
        Map<Boolean, Double> averageAge = students.stream()
//                .collect(Collectors.partitioningBy(student -> student.age > 50));
//                .collect(Collectors.partitioningBy(student -> student.age > 50, Collectors.toSet()));
                .collect(Collectors.partitioningBy(student -> student.age > 50, Collectors.averagingInt(Student::getAge)));

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);

        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(number -> number).summaryStatistics();

        System.out.println(intSummaryStatistics); // IntSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}


        /*
            toList
            toSet
            joining Stringㅡㄹ 만든다.

            partition 주어진 조건의 참, 거짓을 기준으로 MAPㅡㄹ 만든다.
            groupingBy -> 주어진 키 값으로 그룹화하여 Map을 만든다.

           averagingInt 5.0
            summingInt 45
            summarizingInt IntSummaryStatistics{count=9, sum=45, min=1, average=5.000000, max=9}
         */
    }

    static class Student implements Comparable<Student> {
        String name;
        int age;
        int studentNo;

        public Student(String name, int age, int studentNo) {
            this.name = name;
            this.age = age;
            this.studentNo = studentNo;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.studentNo, o.studentNo);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", studentNo=" + studentNo +
                    '}';
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getStudentNo() {
            return studentNo;
        }
    }


}
