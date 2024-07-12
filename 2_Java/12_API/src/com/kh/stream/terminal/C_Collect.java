package com.kh.stream.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kh.stream.model.Student;

/*
 * 수집
 * - 최종 처리 기능으로 필터링 또는 매핑한 요소들을 새로운 컬렉션으로 담아서 리턴하는
 * 		collect() 제공
 */

public class C_Collect {

	public static void main(String[] args) {
		List<Student> students = Arrays.asList(
				new Student("김현호", 20, "남자", 80, 50),
				new Student("우현성", 19, "남자", 75, 60),
				new Student("김경주", 18, "여자", 50, 100),
				new Student("주준영", 21, "남자", 60, 45),
				new Student("조창현", 19, "남자", 70, 90),
				new Student("김진주", 19, "여자", 80, 50));
		
		// List 컬렉션으로 수집 -> 수학 점수가 80점 이상인 사람들만
		List<Student> list = students.stream().filter(student -> student.getMath() >= 80)
				.collect(Collectors.toCollection(() -> new ArrayList<Student>()));
		System.out.println(list);
		
		// Set 컬렉션으로 수집 -> 고등학생인 사람들만, HashSet
		Set<Student> set = students.stream().filter(student -> student.getAge() <= 19 && student.getAge() >= 17)
				.collect(Collectors.toCollection(() -> new HashSet<Student>()));
		System.out.println(set);
		
		// Map 컬렉션으로 수집 -> 키 : 이름, 값 : 나이 (toMap)
		Map<String, Integer> map = students.stream()
				.collect(Collectors.toMap(student -> student.getName(), student -> student.getAge()));
		System.out.println(map);
	}

}
