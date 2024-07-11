package com.kh.time;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

/*
 * java.time 패키지
 * - Date와 Calendar의 단점을 개선한 새로운 클래스들을 제공 (JDK 1.8)
 * - LocalDate, LocalTime, LocalDateTime, ZonedDateTime 클래스 포함
 * - 날짜와 시간에 대한 다양한 메서드를 제공
 */

public class B_Time {
	
	public void method1() {
		
		/*
		 * LocalDateTime
		 * - 날짜와 시간 정보를 모두 저장
		 */
		LocalDateTime now = LocalDateTime.now();
		System.out.println("today : " + now);
		
		// 날짜 지정
		LocalDateTime when = LocalDateTime.of(2024, 7, 12, 17, 50, 0);
		System.out.println(when);
		
		// ZonedDateTime : LocalDateTime + 시간대
		System.out.println("ZonedDateTime : " + ZonedDateTime.now());
		
		// 년, 월, 일, 요일, 시, 분, 초
		System.out.println(now.getYear() + "년");
		System.out.println(now.getMonth()); // JULY
		System.out.println(now.getMonthValue() + "월"); // 7월
		System.out.println(now.getDayOfMonth() + "일");
		System.out.println(now.getDayOfWeek()); // THURSDAY
		System.out.println(now.getHour() + "시");
		System.out.println(now.getMinute() + "분");
		System.out.println(now.getSecond() + "초");
		
		System.out.println();
		
		// 날짜 조작
		LocalDateTime plusDays = now.plusDays(1).plusMonths(2).plusYears(1);
		System.out.println("plus : " + plusDays);
		
		LocalDateTime minusDays = now.minusYears(1).minusMonths(1).minusDays(1);
		System.out.println("minus : " + minusDays);
		
		LocalDateTime withDays = now.withYear(2025).withMonth(8).withDayOfMonth(10);
		System.out.println("with : " + withDays);
		
		System.out.println("isAfter : " + now.isAfter(withDays)); // 오늘이 해당 날짜보다 이후인가?
		System.out.println("isBefore : " + now.isBefore(withDays)); // 오늘이 해당 날짜보다 이전인가?
		
		// LocalDate : 날짜 정보를 저장
		LocalDate localDate = LocalDate.now();
		System.out.println("LocalDate : " + localDate);
		localDate = LocalDate.of(2024, 11, 13);
		System.out.println("LocalDate2 : " + localDate);
		
		// LocalTime : 시간 정보만 저장
		LocalTime localTime = LocalTime.now();
		System.out.println("LocalTime : " + localTime);
		localTime = LocalTime.of(17, 49, 59);
		System.out.println("LocalTime2 : " + localTime);
	}
	
	/*
	 * Period와 Duration
	 * - 날짜와 시간 간격을 표현하기 위한 클래스
	 * - Period : 두 날짜 간의 차이
	 * - Duration : 시간의 차이
	 */
	public void method2() {
		
		LocalDate date1 = LocalDate.of(2024, 1, 1);
		LocalDate date2 = LocalDate.of(2025, 12, 31);
		
		Period pe = Period.between(date1, date2);
		System.out.println("pe : " + pe);
		
		System.out.println("years : " + pe.getYears());
		System.out.println("month : " + pe.getMonths());
		System.out.println("days : " + pe.getDays());
		
		System.out.println("years : " + pe.get(ChronoUnit.YEARS));
		System.out.println("month : " + pe.get(ChronoUnit.MONTHS));
		System.out.println("days : " + pe.get(ChronoUnit.DAYS));
		
		LocalTime time1 = LocalTime.of(1, 2, 3);
		LocalTime time2 = LocalTime.of(11, 59, 59);
		
		Duration du = Duration.between(time1, time2);
		System.out.println("du : " + du);
		
		System.out.println("hours : " + du.toHours());
		System.out.println("minutes : " + du.toMinutes());
		System.out.println("seconds : " + du.toSeconds());
		
		// 문자열을 LocalDate 객체로 파싱
		LocalDate date = LocalDate.parse("2024-11-13");
		System.out.println(date);
		
		// DateTimeFormatter
		// 날짜와 시간을 포맷팅(Formatting)된 문자열로 변환하는 메서드를 제공하는 클래스
		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String formatDate = today.format(dtf);
		System.out.println(formatDate);
	}
	
	/*
	 * D-Day 계산기 : 년월일(각각)을 입력하여 디데이 계산
	 * ChronoUnit.DAYS.between(날짜, 날짜)
	 * 또는 날짜.until(날짜, ChronoUnit.DAYS)
	 */
	Scanner sc = new Scanner(System.in);
	public void method3() {
		LocalDate now = LocalDate.now();
		int year, month, day;
		while (true) {
			try {
				while(true) {
					System.out.print("해당 연도 입력 : ");
					year = Integer.parseInt(sc.nextLine());
					
					if (year < 100) {
						System.out.println("1. 해당 연도가 " + year + "년");
						System.out.println("2. 해당 연도가 10" + year + "년");
						System.out.println("3. 해당 연도가 20" + year + "년");
						System.out.print("입력 : ");
						int num = Integer.parseInt(sc.nextLine());
						
						if(num == 1) break;
						else if (num == 2) {
							year += 1000;
							break;
						}
						else if (num == 3) {
							year += 2000;
							break;
						}
						else System.out.println("잘못 입력했습니다. 다시 입력하세요.");
					}else break;
				}
				while (true) {
					System.out.print("해당 월 입력 : ");
					month = Integer.parseInt(sc.nextLine());

					if (month > 0 && month < 13) break;
					else System.out.println("1 ~ 12 사이의 숫자를 입력하세요.");
				}
				while (true) {
					System.out.print("해당 일 입력 : ");
					day = Integer.parseInt(sc.nextLine());

					if (day > 0 && day < 32) break;
					else System.out.println("1 ~ 31 사이의 숫자를 입력하세요.");
				}

				LocalDate dDay = LocalDate.of(year, month, day);
				long resultDay = ChronoUnit.DAYS.between(now, dDay);
				
				if (now.isBefore(dDay)) {
					System.out.println("D-" + ChronoUnit.DAYS.between(now, dDay));
					break;
				}
				else {
					System.out.println("D+" + ChronoUnit.DAYS.between(dDay, now));
					break;
				}
			} catch (DateTimeException e) {
				System.out.println("해당 날짜는 없는 날짜 입니다, 다시 입력하세요.");
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력하세요.");
			}
		}
		
	}
	
	public static void main(String[] args) {
		B_Time b = new B_Time();
//		b.method1();
//		b.method2();
		b.method3();
	}

}
