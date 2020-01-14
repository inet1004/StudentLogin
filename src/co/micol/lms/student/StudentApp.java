package co.micol.lms.student;  // App은 html, jsp로 작업함

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp { // 전체 학생보기
	StudentDto dto;
	StudentServiceImpl student = new StudentServiceImpl();
	Scanner sc = new Scanner(System.in);

	public void selectAll() {
		List<StudentDto> list = new ArrayList<StudentDto>();
		list = student.allStudent();
		studentList(list);
	}

	public void select() {
		dto = new StudentDto();
		System.out.println("검색할 학생의 학번을 입력");
		dto.setStudent_id(sc.next());
		dto = student.select(dto);
		searchStudent(dto);
		sc.next();
	}

	public void insert() {
		dto = new StudentDto();
		do { // 입력받은 아이디 체크하기
			System.out.println("입력할 학번 입력");
			dto.setStudent_id(sc.nextLine());
			boolean b = student.isCheckId(dto.getStudent_id());
			if (!b) {
				System.out.println("이미 존재 아이디입니다");
			} else {
				System.out.println("입력된 아이디는 사용가능한 학번임");
				break;
			}
		} while (true);
		System.out.println("입력할 학생이름 입력");
		dto.setStudent_name(sc.nextLine());
		System.out.println("입력할 학과코드 입력");
		dto.setStudent_dept(sc.nextLine());
		System.out.println("입력할 생년월일 입력");
		dto.setStudent_birthday(Date.valueOf(sc.nextLine()));
		int n = student.insert(dto);
		if (n != 0) {
			System.out.println("정상 입력하였음");
			selectAll();
		} else {
			System.out.println("입력 실패");
		}
	}
	
	public void login() {
		System.out.println("학번 입력하세요");
		String id=sc.nextLine();
		System.out.println("패스워드 입력하세요");
		String pw=sc.nextLine();
		String name=student.loginCheck(id, pw);
		if(name==null) 
			System.out.println("로그인 실패 했습");
		else 
			System.out.println(name+"님 환영합니다");
	}
	
	

	private void searchStudent(StudentDto dto) {

		System.out.print(dto.getStudent_id() + " ");
		System.out.print(dto.getStudent_name() + " ");
		System.out.print(dto.getStudent_dept() + " ");
		System.out.print(dto.getDept_name() + " ");
		System.out.println(dto.getStudent_birthday() + " ");

	}

	private void studentList(List<StudentDto> list) {
		for (StudentDto dto : list) {
			System.out.print(dto.getStudent_id() + " ");
			System.out.print(dto.getStudent_name() + " ");
			System.out.print(dto.getStudent_dept() + " ");
			System.out.print(dto.getDept_name() + " ");
			System.out.println(dto.getStudent_birthday() + " ");
		}

	}
}
