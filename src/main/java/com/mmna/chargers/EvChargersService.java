package com.mmna.chargers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mmna.evchargers.wsdl.GetPublicStationsResponse;



@Service
public class EvChargersService {
	
	private static final Logger logger = Logger.getLogger(EvChargersService.class);
	
	@Autowired
	private EvChargersClient chargersClient;
	
	
	public String getPublicStationResponse(String zip){	
		GetPublicStationsResponse response = chargersClient.getPublicStationResponse(zip);
		Gson gson = new Gson();
		String json = gson.toJson(response);
		return json;
	}
	

}
