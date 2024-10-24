package org.example.useful.intermediate;

import java.util.List;

/**
 * Stream의 중간 연산 중 중복을 제거하는 Distinct 연산입니다.
 * 이를 사용하기 위해선 equals()와 hashcode() 메서드를 재정의하는 것이 중요합니다.
 * 아래 예제에서는 equals()와 hashcode()가 재정의된 Integer의 경우 정상적으로 중복 제거가 이뤄졌지만,
 * Student의 경우 실제 주소값이 같은 mg만 중복 제거가 이뤄짐을 확인할 수 있습니다.
 */
public class DistinctExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,1,2,2,3,3,4,4,5,5);
        numbers.stream().distinct()
                .forEach(System.out::println); // 1 2 3 4 5

        Student mg = new Student("맹구", 5);
        List<Student> students = List.of(
                new Student("김철수", 5),
                new Student("신짱구", 5),
                new Student("맹구", 5),
                new Student("맹구", 5),
                mg, mg
        );

        students.stream().distinct()
                .forEach(System.out::println);
        /*
            Student{name='김철수', age=5}
            Student{name='신짱구', age=5}
            Student{name='맹구', age=5}
            Student{name='맹구', age=5}
            Student{name='맹구', age=5}
         */
    }

    static class Student{
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
