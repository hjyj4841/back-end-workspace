package com.kh.stream.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import com.kh.stream.model.Student;

// 매핑 - 중간 처리 기능으로 스트림의 요소를 다른 요소로 대체하는 역할
public class C_Mapping {
	
	// mapXXX : 요소를 대체하는 요소로 구성된 새로운 스트림 생성
	public void method1() {
		List<Student> students = Arrays.asList(
				new Student("김현호", 20, "남자", 80, 50),
				new Student("우현성", 19, "남자", 75, 60),
				new Student("김경주", 18, "여자", 50, 100),
				new Student("주준영", 21, "남자", 60, 45),
				new Student("조창현", 19, "남자", 70, 90),
				new Student("김진주", 22, "여자", 80, 50));
		students.stream().map(student -> student.getName())
			.forEach(name -> System.out.println(name));
		
		// 수학 점수의 평균(average)
		Double avg =  students.stream().mapToInt(student -> student.getMath())
			.average().getAsDouble();
		
		System.out.println(avg);
	}
	
	// flatMapXXX : 하나의 요소를 복수의 요소들로 구성된 새로운 스트림을 생성한다.
	public void method2() {
		List<String> list = Arrays.asList("Java Lambda", "Stream Filtering Sorted Mapping");
		
		list.stream()
			.flatMap((String str) -> {
				String[] arr = str.split(" ");
				return Arrays.stream(arr);
			})
//			.flatMap(str -> Arrays.stream(str.split(" ")))
			.forEach(System.out::println);
//			.forEach(str -> System.out.println(str));
	}
	
	/*
	 * asDoubleStream(), asLongStream(), boxed()
	 * - asDoubleStream : IntStream, LongSteam을 DoubleStream으로 변환
	 * - asLongStream : IntStream을 LongStream으로 변환
	 * - boxed : int, long, double 요소를 Integer, Long, Double 요소로 박싱
	 */
	public void method3() {
		int[] array1 = {1, 2, 3, 4, 5};
		double[] array2 = {1.1, 2.2, 3.3, 4.4, 5.5};
		
		Arrays.stream(array1)
			.asDoubleStream()
			.forEach(System.out::println);
		
		System.out.println();
		
		Arrays.stream(array2)
			.boxed()
			.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		C_Mapping c = new C_Mapping();
//		c.method1();
//		c.method2();
		c.method3();
	}

}
