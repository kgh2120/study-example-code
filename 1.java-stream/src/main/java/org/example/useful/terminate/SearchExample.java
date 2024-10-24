package org.example.useful.terminate;

import java.util.*;

/**
 * Stream의 최종 연산 중 검색 연산을 다룹니다.
 * 검색 연산은 총 5개가 있습니다.
 * 그 중에서 3개는 boolean을 리턴하는 연산입니다.
 * allMatch, anyMatch, noneMatch는 조건과 요소와의 관계를 기준으로 모든 요소가 만족하는 경우, 하나라도 만족하는 경우, 모두 만족하지 않는 경우에 true를 리턴합니다.
 *
 * 남은 2개의 연산은 Optional<T>를 리턴합니다.
 * findAny와 findFirst가 있는데, 이 예제는 findAny와 findFirst의 서로 다른 결과를 보여주는 예제입니다.
 * findAny는 조건을 만족하는 어떤 객체던지 리턴을 하고, findFirst는 가장 첫 번째 순서의 객체를 리턴하는 연산입니다.
 * 일반적인 순차 스트림에서는 둘이 같은 결과가 나타났고, 병렬 스트림을 사용한 경우 다른 결과가 나타나는 것을 확인했습니다.
 */
public class SearchExample {

    public static void main(String[] args) {


        String[] names = {"신장구", "김철수", "유리", "훈이", "맹구", "흰둥이"};

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int nameIndex = new Random().nextInt(names.length);
            students.add(new Student(names[nameIndex], i, i));

        }

        for (int i = 0; i < 100; i++) {
            Optional<Student> student = students.parallelStream().findAny();
            Optional<Student> student2 = students.parallelStream().findFirst();
            System.out.println("any : " + student.get() + " first : " + student2.get());
        }

        /*
            any : Student{name='흰둥이', age=65, studentNo=65} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='유리', age=32, studentNo=32} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='유리', age=32, studentNo=32} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='흰둥이', age=65, studentNo=65} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='유리', age=32, studentNo=32} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='흰둥이', age=65, studentNo=65} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='유리', age=32, studentNo=32} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='유리', age=32, studentNo=32} first : Student{name='신장구', age=0, studentNo=0}
            any : Student{name='흰둥이', age=65, studentNo=65} first : Student{name='신장구', age=0, studentNo=0}
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
    }
}
