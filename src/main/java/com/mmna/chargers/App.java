package com.mmna.chargers;





import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.mmna.evchargers.wsdl.GetCPNInstancesResponse;
import com.mmna.evchargers.wsdl.GetPublicStationStatusResponse;
import com.mmna.evchargers.wsdl.GetPublicStationsResponse;
import com.mmna.evchargers.wsdl.OStatusdata;
import com.mmna.evchargers.wsdl.Ocpninstances;
import com.mmna.evchargers.wsdl.StationData;

import com.mmna.chargers.EvChargersClient;


@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	//RUN WITH mvnw spring-boot:run ON CONSOLE
		@Bean
		CommandLineRunner lookup(EvChargersClient evQuoteClient) {
			return args -> {
				String zip = "48093";

				if (args.length > 0) {
					zip = args[0];
				}
				GetCPNInstancesResponse response = evQuoteClient.getCPNInstances("");
				
				//System.err.println("CPNInstances: "+ response.GET);
				for(Ocpninstances inst : response.getCPN()){
					System.err.println("********************");
					System.err.println("Tests getCpnName: "+ inst.getCpnName());
					System.err.println("Tests getCpnDescription: "+ inst.getCpnDescription());
					System.err.println("Tests: getCpnID "+ inst.getCpnID());
					System.err.println("********************");
				}
				
				
				
				GetPublicStationsResponse publicStationRes = evQuoteClient.getPublicStationResponse(zip);
				
				
				System.err.println("Public station CODE: "+ publicStationRes.getResponseCode());
				
				for(StationData sd : publicStationRes.getStationData()){
					System.err.println("********PUBLIC STATION************");
					System.err.println("Public station: "+ sd.getAddress());
					System.err.println("Public station: "+sd.getCountry());
					System.err.println("Public station: "+ sd.getPostalCode() );
					System.err.println("********************");
					
				}
			
				Gson gson = new Gson();
				
				String json = gson.toJson(publicStationRes);
				
				System.err.println(json);
				
				
				GetPublicStationStatusResponse statusResponse = evQuoteClient.getPublicStationStatusResponse("90210");
				System.err.println("Public station status CODE: "+ statusResponse.getResponseCode());
				
				for(OStatusdata od : statusResponse.getStationStatusData()){
					System.err.println("********PUBLIC STATION status************");
					System.err.println("Public station status: "+ od.getStationID());
					System.err.println("********************");
					
				}
				
				gson = new Gson();
				
				json = gson.toJson(statusResponse);
				
				System.err.println(json);
				
				
				//RUN WITH ./mvnw spring-boot:run
				
				///System.err.println(response.getCPN());
			};
		}
	

}
