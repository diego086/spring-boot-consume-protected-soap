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
		
		//It works weel to with autowired
		
		responseCPN = (GetCPNInstancesResponse) webServiceTemplate.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
				gcpRequest,
				new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getCPNInstances"));
		
		
		/* SHOULD WORK MUST BE CLOSE TO MAKE IT WORK THIS WAY
		responseCPN = (GetCPNInstancesResponse) webServiceTemplate.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
				gcpRequest,
				new SoapRequestHeaderModifier("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getCPNInstances"));
		*/
		//WebServiceTemplate template = getWebServiceTemplate();
		//template.setMessageSender(new WebServiceMessageSenderWithAuth());
		/*// works well https://spring.io/guides/gs/consuming-web-service/#initial
		response = (GetPublicStationsResponse) getWebServiceTemplate().marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
						st,
						new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getPublicStations"));
		 */
		
		//response = (GetPublicStationsResponse)webServiceTemplate.marshalSendAndReceive(st);
		
		/*
		responseCPN = (GetCPNInstancesResponse)webServiceTemplate.marshalSendAndReceive(st, new WebServiceMessageCallback() {

	          @Override
	          public void doWithMessage(WebServiceMessage message) {
	            try {
	              // get the header from the SOAP message
	              SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();

	              // create the header element
	              GetCPNInstances st = new GetCPNInstances();
	              
	              /*
	              JAXBElement<GetCPNInstances> headers =
	            		  st.createListFlightsSoapHeaders(listFlightsSoapHeaders);
				* /
	              
	              JAXBContext context = JAXBContext.newInstance(GetCPNInstances.class);
	              Marshaller marshaller = context.createMarshaller();

	              // marshal the headers into the specified result
	              //marshaller.marshal(headers, soapHeader.getResult());
	              
	              /*
	              ListFlightsSoapHeaders listFlightsSoapHeaders =
	                  factory.createListFlightsSoapHeaders();
	              listFlightsSoapHeaders.setClientId("abc123");

	              JAXBElement<ListFlightsSoapHeaders> headers =
	                  factory.createListFlightsSoapHeaders(listFlightsSoapHeaders);

	              // create a marshaller
	              JAXBContextt context = JAXBContext.newInstance(ListFlightsSoapHeaders.class);
	              Marshaller marshaller = context.createMarshaller();

	              // marshal the headers into the specified result
	              marshaller.marshal(headers, soapHeader.getResult());
	              ** * /
	            } catch (Exception e) {
	              //LOGGER.error("error during marshalling of the SOAP headers", e);
	            }
	            
	          }
	        });
		*/
		//response= (GetPublicStationsResponse) webServiceTemplate.marshalSendAndReceive(st);

		log.info("Requesting quote for " + zip);
		/*
		WebServiceTemplate template = getWebServiceTemplate();
		//template.setMessageSender(new WebServiceMessageSenderWithAuth());
		
		response = (GetPublicStationsResponse) template.marshalSendAndReceive("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/",
						st,
						new SoapActionCallback("https://webservices.chargepoint.com/webservices/chargepoint/services/5.0/getPublicStations"));
		 */
		return responseCPN;
	}
	

}
