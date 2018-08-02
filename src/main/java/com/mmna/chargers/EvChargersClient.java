package com.mmna.chargers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.mmna.evchargers.wsdl.GetCPNInstances;
import com.mmna.evchargers.wsdl.GetCPNInstancesResponse;
import com.mmna.evchargers.wsdl.GetPublicStationStatus;
import com.mmna.evchargers.wsdl.GetPublicStationStatusResponse;
import com.mmna.evchargers.wsdl.GetPublicStations;
import com.mmna.evchargers.wsdl.GetPublicStationsResponse;
import com.mmna.evchargers.wsdl.StationSearchRequest;
import com.mmna.evchargers.wsdl.StatusSearchdata;

@Component
public class EvChargersClient  extends WebServiceGatewaySupport {
	private static final Logger log = LoggerFactory.getLogger(EvChargersClient.class);
	
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	public GetPublicStationStatusResponse getPublicStationStatusResponse(String zip) {
		
		GetPublicStationStatus st = new GetPublicStationStatus();
		StatusSearchdata ssdata = new StatusSearchdata();
		
		ssdata.setPostalCode(zip);
		st.setSearchQuery(ssdata);
		
		GetPublicStationStatusResponse response;
		
		response = (GetPublicStationStatusResponse) webServiceTemplate.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
				st,
				new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getPublicStationStatus"));
		
		return response;
		
	}
	
	public GetPublicStationsResponse getPublicStationResponse(String zip) {
		GetPublicStations st = new GetPublicStations();
		StationSearchRequest ss = new StationSearchRequest();
		
		ss.setPostalCode(zip);
		st.setSearchQuery(ss);
		GetPublicStationsResponse response;
		
		response = (GetPublicStationsResponse) webServiceTemplate.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
				st,
				new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getPublicStations"));
		
		return response;
		
	}

	public GetCPNInstancesResponse getCPNInstances(String zip) {

		GetPublicStations st = new GetPublicStations();
		StationSearchRequest ss = new StationSearchRequest();
		
		ss.setPostalCode(zip);
		st.setSearchQuery(ss);
		GetPublicStationsResponse response;
		
		
		GetCPNInstances gcpRequest = new GetCPNInstances();
		GetCPNInstancesResponse responseCPN;
		
		
		responseCPN = (GetCPNInstancesResponse) webServiceTemplate.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
				gcpRequest,
				new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getCPNInstances"));
		
		
		
		return responseCPN;
	}
	

}
