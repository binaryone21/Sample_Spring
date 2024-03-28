package seunghee.event.apply.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyEventManage {
	private long applyEventSeq;
	private String applyEventName;
	private String useYn;
	private String startDate;
	private String endDate;
	private ValidationOptions validationOptions;
	private String validationOptionsJson;

	public ValidationOptions getValidationOptions() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			this.validationOptions = objectMapper.readValue(getValidationOptionsJson(), ValidationOptions.class);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			throw new RuntimeException(e);
		} finally {
			return validationOptions;
		}

	}

}
