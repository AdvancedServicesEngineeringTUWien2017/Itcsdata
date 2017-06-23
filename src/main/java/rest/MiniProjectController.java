package rest;


import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import json.BusInformation;
import json.ChartData;
import json.ChartTyp;
import mail.Mailer;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MiniProjectController {
	
	@Value("${mail.smtpHost}")
	private String smtpHost;
	
	@Value("${mail.tlspwm}")
	private String tlspwm;
	
	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.to}")
	private String to;
	
	@Value("${itcs.maxBusses}")
	private int MaxBusses;
	
	long errorCount = 0;
	
	long noGpsCount = 0;
	
	boolean sentMaxBussesReached = false;
	
	String uuid = "";
	
	Map<Integer, BusInformation> busses = new HashMap<Integer, BusInformation>();
	Map<String, ChartData> chartData = new HashMap<String, ChartData>();

	@RequestMapping(value="/NewData", method=RequestMethod.POST, consumes = "application/json")
    public void NewData(@RequestBody String json) throws Exception {
		
		if (busses.size() > MaxBusses)
		{
			return;
		}
		BusInformation busInformation = new BusInformation(json);
		if (busInformation.GeraetNr != 0)
		{
			if (busses.containsKey(busInformation.GeraetNr))
			{
				BusInformation existingBus = busses.get(busInformation.GeraetNr);
				if (existingBus.PositionLatitude != busInformation.PositionLatitude ||
						existingBus.PositionLongitude != busInformation.PositionLongitude)
				{
					busses.put(busInformation.GeraetNr, busInformation);
				}
			}else
			{
				busses.put(busInformation.GeraetNr, busInformation);
			}
			AddChartData(ChartTyp.BussCount);
			if (busses.size() > MaxBusses && !sentMaxBussesReached)
			{
				SendMaxBussesMail();
			}
		}
    }
	
	private void SendMaxBussesMail()
	{
	    uuid = UUID.randomUUID().toString();
		String text = "Die maximale Anzahl der Busse wurde überschritten, es können keine weiteren Busse hinzugefügt werden. \n \n "+
				" Mit diesem Link vergrößern Sie ihr Kontigent um weitere " + MaxBusses + " Busse.\n \n " +
				" http://localhost:8443/AddMaxBusses?uuid=" + uuid;
		Mailer.sendEmail( to,from, "Anzahl der maximalen Busse überschritten.", 
				text, smtpHost, tlspwm);
		sentMaxBussesReached = true;
	}
	
    @RequestMapping(value="/GetBusses", method=RequestMethod.GET)
    public ResponseEntity<Collection<BusInformation>> GetBusses() {
    	
    	return new ResponseEntity<Collection<BusInformation>>(busses.values(), HttpStatus.OK);	
    }
    
    @RequestMapping(value="/SendMail", method=RequestMethod.GET)
    public void SendMail() {
    	SendMaxBussesMail();
    }
    
    @RequestMapping(value="/GetChartData", method=RequestMethod.GET)
    public ResponseEntity<List<ChartData>> GetChartData() {
    	Comparator<ChartData> comparator = new Comparator<ChartData>() {
    	    public int compare(ChartData left, ChartData right) {
    	        return left.time.compareTo(right.time);
    	    }
    	};
    	List<ChartData> list = new ArrayList(chartData.values());
    	Collections.sort(list, comparator);
    	return new ResponseEntity<List<ChartData>>(list, HttpStatus.OK);	
    }
    
    @RequestMapping(value="/GetNoGps", method=RequestMethod.GET)
    public long getNoGps() {
    	
    	return noGpsCount;
    }
    	
    
    @RequestMapping(value="/GetError", method=RequestMethod.GET)
    public long getError() {
    	
    	return errorCount;
    }
    
    @RequestMapping(value="/GetBussesCount", method=RequestMethod.GET)
    public long GetBussesCount() {
    	
    	return busses.size();
    }
    
    @RequestMapping(value="/noGpsCount", method=RequestMethod.GET)
    public void noGpsCound() {
    	
    	noGpsCount ++;
    	AddChartData(ChartTyp.NoGSPCount);
    }
    	
    @RequestMapping(value="/error", method=RequestMethod.GET)
    public void error() {
    	errorCount ++;
    	AddChartData(ChartTyp.ErrorCount);
    }
    
    public void AddChartData(ChartTyp chartTyp)
    {
    	ZoneId zone = ZoneId.of("Europe/Berlin");
    	LocalTime now = LocalTime.now(zone);
    	String time =  Integer.toString(now.getHour()) + ':' + Integer.toString(now.getMinute());
    	String key = chartTyp.name() + time;
    	if (chartData.containsKey(key))
    	{
    		chartData.get(key).AddCount();
    	}else
    	{
    		chartData.put(key, new ChartData(chartTyp, time));
    	}
    }
    
    
    @RequestMapping(value="/AddMaxBusses", method=RequestMethod.GET)
    public void AddMaxBusses(@RequestParam(value = "uuid", required = true)  String _uuid) {
    	if (sentMaxBussesReached && _uuid.equals(uuid) && !uuid.equals(""))
    	{
    		MaxBusses += MaxBusses;
    		sentMaxBussesReached = false;
    		uuid = "";
    	}
    }
}
