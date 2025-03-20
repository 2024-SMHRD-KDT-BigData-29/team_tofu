package kr.smhrd.myapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//기본 생성자, 사용자정의생성자, Getter&Setter, toString 생성하기 위한 설정
// -> lombok 활용
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	private String email;
	private String pw;
	private String tel;
	private String address;
	
	
}
