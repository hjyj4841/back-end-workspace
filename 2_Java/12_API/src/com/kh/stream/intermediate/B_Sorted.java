package com.kh.stream.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.kh.stream.model.Student;

/*
 * 정렬
 * - 스트림은 최종 처리 되기 전에 중간 단계에서 요소들을 정렬해서 최종 처리 순서를 변경할 수 있다.
 * - Stream<T>은 요소 객체가 Comparable 인터페이스를 구현해야 정렬할 수 있다.
 */

public class B_Sorted {
	
	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("김현호", "우현성", "김경주", "주준영", "조창현", "김진주");
		
		names.stream()
			.sorted() // 오름차순 정렬
			.sorted(Comparator.reverseOrder()) // 내림차순 정렬
			.forEach(name -> System.out.print(name + " "));
		
		System.out.println();
		
		List<Student> students = Arrays.asList(
				new Student("김현호", 20, "남자", 80, 50),
				new Student("우현성", 19, "남자", 75, 60),
				new Student("김경주", 18, "여자", 50, 100),
				new Student("주준영", 21, "남자", 60, 45),
				new Student("조창현", 19, "남자", 70, 90),
				new Student("김진주", 22, "여자", 80, 50));
		
		students.stream()
			.sorted()
			.forEach(student -> System.out.println(student));
		
	}

}
