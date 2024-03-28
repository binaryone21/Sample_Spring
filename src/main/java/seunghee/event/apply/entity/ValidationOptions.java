package seunghee.event.apply.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationOptions {
	private List<Requirement> requirements;
	private Duplications duplications;
	private DrawInfo drawInfo;
}
