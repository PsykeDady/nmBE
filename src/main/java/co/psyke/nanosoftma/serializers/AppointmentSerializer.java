package co.psyke.nanosoftma.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import co.psyke.nanosoftma.entities.Appointment;

public class AppointmentSerializer extends StdSerializer<Appointment> {

	public AppointmentSerializer() {
		this(null);
	}

	public AppointmentSerializer(Class<Appointment> t) {
		super(t);
	}

	@Override
	public void serialize(Appointment value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("user", value.getUser().getEmail());
		gen.writeStringField("doctor", value.getDoctor().getEmail());
		gen.writeObjectField("appointmentdate", value.getAppointmentDate());
		gen.writeEndObject();
	}
	
}
