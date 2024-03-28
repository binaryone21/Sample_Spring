package seunghee.module;

import lombok.Data;

@Data
public class LoginVO {
	private long seq;
	private String id;
	private String role;
	private boolean logined;
}
