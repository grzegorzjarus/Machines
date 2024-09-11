package com.example.machines;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.machines.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MachinesApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		List<Machine> machines = new ArrayList<Machine>();
		List<OfferByOwner> offers = new ArrayList<>();

		Owner owner = new Owner(
				1,
				"owner",
				"surname",
				"companyName",
				"12345",
				"test@example.com",
				null,
				machines,
				offers);




		OfferByOwner offerByOwner = OfferByOwner.createOfferByOwner(owner,
				new Date(),
				new Date(),
				BigDecimal.TEN,
				BigDecimal.TEN,
				false,
				null);

		Machine machine = new Machine(2,
				MachineType.BASKET_LIFT,
				"machine",
				"description",
				owner,
				MachineStatus.ON_AUCTION,
				offerByOwner);

		List<ResponseByRenter> responsesByRenters = new ArrayList<>();

		Renter renter = new Renter(4,
				"test@example.com",
				"renter",
				"renter",
				"company",
				"123456",
				null,
				responsesByRenters);

		ResponseByRenter responseByRenter = new ResponseByRenter(3,
				new Date(),
				new Date(),
				BigDecimal.TEN,
				ResponseByRenterStatus.RENTER,
				renter,
				offerByOwner);

		responsesByRenters.add(responseByRenter);

		offerByOwner.setMachine(machine);
		machines.add(machine);
		offers.add(offerByOwner);

		String ownerJson =  new ObjectMapper().writeValueAsString(owner);
		String offerJson =  new ObjectMapper().writeValueAsString(offerByOwner);
		String machineJson =  new ObjectMapper().writeValueAsString(machine);
		String renterJson =  new ObjectMapper().writeValueAsString(renter);
		String responseByRenterJson =  new ObjectMapper().writeValueAsString(responseByRenter);


	}

}
