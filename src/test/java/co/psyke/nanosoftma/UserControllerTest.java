package co.psyke.nanosoftma;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.AppointmentForm;
import co.psyke.nanosoftma.models.DoctorType;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.models.UserType;
import jakarta.annotation.Resource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	
	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public static String writeObject(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
		   .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(o);
		} catch ( JsonProcessingException je){
			String stackt=je.getStackTrace().toString();
			return String.format("""
					{
						"error":"%s",
						"stack":"%s"
					}
					""",je.getMessage(),stackt.substring(0, stackt.length()>100?100:stackt.length()));
		}
	}

	@BeforeAll
	public void setup() throws Exception{
		mockMvc=MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/register")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(writeObject(new UserForm("Psyke","test@test.com","toor",UserType.USER,null)))
		).andExpect(status().isOk());
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/register")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(writeObject(new UserForm("Dottore","test@doctor.com","toor",UserType.DOCTOR,DoctorType.ENDOCRINOLOGY)))
		).andExpect(status().isOk());
	}

	@Test
	@Order(1)
	@WithMockUser(username = "test@test.com", password = "toor", roles = "USER")
	void newAppointmentTest() throws Exception{
		Doctor d  = new Doctor("test@doctor.com", null, DoctorType.ENDOCRINOLOGY);

		LocalDateTime adate= LocalDateTime.of(2023, Month.DECEMBER, 21, 18, 30, 0); 

		AppointmentForm af = new AppointmentForm(d, adate); 
		
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/user/newappointment")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(writeObject(af))
		).andExpect(status().isOk()).andExpect(content().string("1"));
	}

	@Test
	@Order(2)
	@WithMockUser(username = "test@test.com", password = "toor", roles = "USER")
	void myAppointmentsTest() throws Exception{
		Doctor d  = new Doctor("testd@msn.com", new User("test@doctor.com", "Dottore"), DoctorType.ENDOCRINOLOGY);

		LocalDateTime adate= LocalDateTime.of(2023, Month.DECEMBER, 31, 18, 30, 0); 
		
		User us = new User("test@test.com", "Psyke");

		Appointment a = new Appointment(1L,us,d, adate);
		
		String s = writeObject(a);
		mockMvc.perform(
			MockMvcRequestBuilders
				.get("/user/myappointments")
		).andExpect(status().isOk()).andExpect(content().string(s));
	}


	@AfterAll
	@Test
	void setOff() throws Exception { 
		mockMvc.perform(
			MockMvcRequestBuilders
				.delete("/user")
		).andExpect(status().isOk());
				mockMvc.perform(
			MockMvcRequestBuilders
				.delete("/user")
		).andExpect(status().isOk());
	}
}
