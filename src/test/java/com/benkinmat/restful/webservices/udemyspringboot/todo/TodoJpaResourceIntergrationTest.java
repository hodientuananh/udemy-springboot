package com.benkinmat.restful.webservices.udemyspringboot.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.benkinmat.restful.webservices.udemyspringboot.jwt.JwtTokenUtil;
import com.benkinmat.restful.webservices.udemyspringboot.jwt.JwtUserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TodoJpaResourceIntergrationTest {

	private static String token;
	private static String authorization;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Before
	public void shouldGenerateAuthToken() throws Exception {
		JwtUserDetails user = new JwtUserDetails(1L, "benkinmat",
				"$2a$10$GJLVGdPO1qM.peTNL9dBMuD0VKvdbqn.sN05IZPdcxPahUtxGP09u", "ROLE_USER_2");

		token = jwtTokenUtil.generateToken(user);
		authorization = "Bearer " + token;
	}

	@Test
	public void givenToken_whenGetNotFoundedResource_thenStatus404() throws Exception {
		mvc.perform(get("/test").header("Authorization", authorization)).andExpect(status().isNotFound());
	}

	@Test
	public void givenNoToken_whenGetResource_thenStatus401() throws Exception {
		mvc.perform(get("/test")).andExpect(status().isUnauthorized());
	}

	@Test
	public void whenGetTodo_thenStatus200() throws Exception {
		mvc.perform(get("/jpa/users/{username}/todos/{id}", "benkinmat", Long.valueOf(1000)).header("Authorization", authorization))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is("benkinmat")));
	}

}
