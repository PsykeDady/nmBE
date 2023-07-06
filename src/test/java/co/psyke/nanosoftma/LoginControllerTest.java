package co.psyke.nanosoftma;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.models.UserType;
import jakarta.annotation.Resource;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginControllerTest {

	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public static String writeObject(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(o);
		} catch ( JsonProcessingException je){
			return String.format("""
					{
						"error":"%s",
						"stack":"%s"
					}
					""",je.getMessage(),je.getStackTrace().toString().substring(0, 100));
		}
	}

	@BeforeAll
	public void setup(){
		mockMvc=MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
	}
	
	@Test
	void testRegister() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/register")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(writeObject(new UserForm("Psyke","test@test.com","toor",UserType.USER,null)))
		).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test@test.com", password = "toor", roles = "USER")
	void testDelete() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders
				.delete("/user")
		).andExpect(status().isOk());
	}
}
