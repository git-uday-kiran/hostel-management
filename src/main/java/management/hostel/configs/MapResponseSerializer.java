package management.hostel.configs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import management.hostel.utils.MapResponse;

import java.io.IOException;

public class MapResponseSerializer extends StdSerializer<MapResponse> {

	public MapResponseSerializer() {
		this(null);
	}

	protected MapResponseSerializer(Class<MapResponse> t) {
		super(t);
	}

	@Override
	public void serialize(MapResponse value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(value.getMap());
	}
}
