package com.computergurukul.covidtracker.services;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.computergurukul.covidtracker.model.LocationStats;

@Service
public class CoronaVirusDataServices {

private final  static String VIRUS_DATA_URL="https://api.covid19india.org/csv/latest/state_wise_daily.csv";
private List<LocationStats> allStats=new ArrayList<>();
@PostConstruct
//@Scheduled(cron="**1***")
public void fetchVirusData()throws Exception {
	
	List<LocationStats>newStats  =new ArrayList<>();
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
	
	HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
	//System.out.println(httpResponse.body());
	StringReader csvBodyReader = new StringReader(httpResponse.body());
	//Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csvBodyReader);
	//String[] headers= CSVFormat.DEFAULT.withFirstRecordAsHeader().getHeader();	//int count=0;
	//System.out.println(records.get);
	CSVParser parser = CSVParser.parse(csvBodyReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
	
	List<CSVRecord> records =parser.getRecords();
	int size = records.size();
	List<String> headers = parser.getHeaderNames();
//	CSVRecord headers = records.iterator().next();
	for(int i=3;i<headers.size();i++) {
	LocationStats locationStat= new LocationStats();
	locationStat.setState(headers.get(i));
	CSVRecord deceased=records.get(size-1);
	locationStat.setLatestDeceased((Integer.parseInt(deceased.get(i))));
	CSVRecord recovered=records.get(size-2);
	locationStat.setLatestRecovered((Integer.parseInt(recovered.get(i))));
	CSVRecord confirmed=records.get(size-3);
	locationStat.setLatestConfirmed((Integer.parseInt(confirmed.get(i))));
	newStats.add(locationStat);
	System.out.println(locationStat);
	}
		//System.out.println(headers.get(i));
//	CSVRecord deceased=records.get(size-1);
	
	this.allStats=newStats;
	
	
	
	
	
	//CSVRecord values = records.listIterator().next();

	   // String total = record.get("Status");
	   
	   // String customerNo = record.get("CustomerNo");
	    //String name = record.get("Name");
}
public List<LocationStats> getAllStats() {
	return allStats;
}
public void setAllStats(List<LocationStats> allStats) {
	this.allStats = allStats;
}

}