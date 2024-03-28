package seunghee.event.apply.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Duplications {
	private String period;
	private String reIssued;
	private long joinLimit;
	private long totalLimit;
	private String errorMessage;
}
