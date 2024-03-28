package seunghee.event.apply.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {
	private String type;
	private String roles;
	private String errorMessage;
}
