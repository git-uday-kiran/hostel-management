package management.hostel.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import management.hostel.configs.MapResponseSerializer;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize(using = MapResponseSerializer.class)
public class MapResponse {

	private final HashMap<String, Object> data = new HashMap<>();

	public MapResponse set(String key, Object value) {
		data.put(key, value);
		return this;
	}

	public static MapResponse create() {
		return new MapResponse();
	}

	public Map<String, Object> getMap() {
		return data;
	}
}
