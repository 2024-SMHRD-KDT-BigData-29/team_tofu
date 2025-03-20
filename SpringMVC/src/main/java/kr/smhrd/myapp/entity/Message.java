package kr.smhrd.myapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data /* Getter, Setter, toString 등 메소드 생성해주는 기능 */
public class Message {
	private int idx;
	private String sendName;
	private String receiveEmail;
	private String content;
	private String sendDate;
}
