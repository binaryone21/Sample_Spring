package seunghee.sample;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SampleService {
	public static List<Map<String, Object>> getDataList() {
		List<Map<String, Object>> dataList = new LinkedList<>();
		for(int i=0; i<3; i++) {
			Map<String, Object> data = new HashMap<>();
			data.put("name", "이름" + i);
			data.put("age", 20 + i);
			dataList.add(data);
		}
		return dataList;
	}
}
