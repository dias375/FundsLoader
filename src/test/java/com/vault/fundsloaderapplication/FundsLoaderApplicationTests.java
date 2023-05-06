package com.vault.fundsloaderapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vault.fundsloaderapplication.controller.FundsLoaderController;
import com.vault.fundsloaderapplication.model.*;
import com.vault.fundsloaderapplication.repository.FundsLoaderOperationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@SpringBootTest
class FundsLoaderApplicationTests {

	RawLoadRequest PAYLOAD_1 = new RawLoadRequest(1,2,"$2000.00", Date.from(Instant.now()));
	RawLoadRequest PAYLOAD_2 = new RawLoadRequest(2,2,"$6000.00", Date.from(Instant.now()));
	RawLoadRequest PAYLOAD_3 = new RawLoadRequest(3,2,"$2000.00", Date.from(Instant.now()));

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FundsLoaderOperationRepository fundsLoaderOperationRepository;

	@MockBean
	private FundsLoaderController fundsLoaderController;

	@Test
	void contextLoads() {
	}

	@Test
	void postSingleOperation() throws Exception {


		LoadResponse loadResponse = new LoadResponse(PAYLOAD_1.getId(), PAYLOAD_1.getCustomer_id(), true);
		//given(fundsLoaderController.postLoadRequest(PAYLOAD_1)).willReturn(loadResponse);

		MvcResult result = mockMvc.perform(post("/api/fundsloader").header("Authorization").contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":\"1\",\"customer_id\":\"1\",\"load_amount\":\"$1000.00\",\"time\":\"2000-02-12T13:45:18Z\"}"))
            .andExpect(status().isAccepted())
            .andReturn();
	}

	@Test
	void postSingleOperationOverLimit() throws ParseException {
		LoadResponse loadResponse = new LoadResponse(PAYLOAD_2.getId(), PAYLOAD_2.getCustomer_id(), false);
		given(fundsLoaderController.postLoadRequest(PAYLOAD_2)).willReturn(loadResponse);
	}

}
