package json;

import java.util.Date;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Coordinates.GPSCalculator;
import Coordinates.Geokoordinate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BusInformation {
	
	public int GeraetNr;
	
    public double PositionLongitude;
    
    public double PositionLatitude;
    
    public Date created;
    
    public BusInformation(String json)
    {
    	SetData(json);
    	created = new Date();
    }
    
	
    private void SetData(String json)
    {
		Gson gson = new GsonBuilder().create();

		try{
			FahrerAbmeldung fahrerAbmeldung = gson.fromJson(json, FahrerAbmeldung.class);
			GeraetNr = fahrerAbmeldung.GeraetNr;
			if (fahrerAbmeldung.PositionLatitude > 0 && fahrerAbmeldung.PositionLongitude > 0)
			{
				SetGPS((int)fahrerAbmeldung.PositionLatitude, (int)fahrerAbmeldung.PositionLongitude);
			}
		}catch(Exception e1){
			try{
				FahrerAnmeldung fahrerAnmeldung = gson.fromJson(json, FahrerAnmeldung.class);
				GeraetNr = fahrerAnmeldung.GeraetNr;
				if (fahrerAnmeldung.PositionLatitude > 0 && fahrerAnmeldung.PositionLongitude > 0)
				{
					SetGPS((int)fahrerAnmeldung.PositionLatitude, (int)fahrerAnmeldung.PositionLongitude);
				}
			}catch(Exception e2){
				try{
					FahrzeugMeldung fahrzeugMeldung = gson.fromJson(json, FahrzeugMeldung.class);
					GeraetNr = fahrzeugMeldung.GeraetNr;
					if (fahrzeugMeldung.PositionLatitude > 0 && fahrzeugMeldung.PositionLongitude > 0)
					{
						SetGPS((int)fahrzeugMeldung.PositionLatitude, (int)fahrzeugMeldung.PositionLongitude);
					}
				}catch(Exception e3){
					try{
						FahrtAbmeldung fahrtAbmeldung = gson.fromJson(json, FahrtAbmeldung.class);
						GeraetNr = fahrtAbmeldung.GeraetNr;
						if (fahrtAbmeldung.PositionLatitude > 0 && fahrtAbmeldung.PositionLongitude > 0)
						{
							SetGPS((int)fahrtAbmeldung.PositionLatitude, (int)fahrtAbmeldung.PositionLongitude);
						}
					}catch(Exception e4){
						try{
							FahrtAnmeldung fahrtAnmeldung = gson.fromJson(json, FahrtAnmeldung.class);
							GeraetNr = fahrtAnmeldung.GeraetNr;
							if (fahrtAnmeldung.PositionLatitude > 0 && fahrtAnmeldung.PositionLongitude > 0)
							{
								SetGPS((int)fahrtAnmeldung.PositionLatitude, (int)fahrtAnmeldung.PositionLongitude);
							}
						}catch(Exception e5){
							try{
								GeraeteAbmeldung geraeteAbmeldung = gson.fromJson(json, GeraeteAbmeldung.class);
								GeraetNr = geraeteAbmeldung.GeraetNr;
								if (geraeteAbmeldung.PositionLatitude > 0 && geraeteAbmeldung.PositionLongitude > 0)
								{
									SetGPS((int)geraeteAbmeldung.PositionLatitude, (int)geraeteAbmeldung.PositionLongitude);
								}
							}catch(Exception e6){
								try{
									GeraeteAnmeldung geraeteAnmeldung = gson.fromJson(json, GeraeteAnmeldung.class);
									GeraetNr = geraeteAnmeldung.GeraetNr;
									if (geraeteAnmeldung.PositionLatitude > 0 && geraeteAnmeldung.PositionLongitude > 0)
									{
										SetGPS((int)geraeteAnmeldung.PositionLatitude, (int)geraeteAnmeldung.PositionLongitude);
									}
								}catch(Exception e7){
								};	
							};	
						};	
					};	
				};	
			};
		};
    }
    
    private void SetGPS(int positionLatitude, int positionLongitude)
    {
		Geokoordinate geo = new Geokoordinate();
		geo.Breitengrad = positionLatitude;
		geo.Laengengrad =  positionLongitude;
		PositionLatitude = Math.floor(GPSCalculator.EvaluateBreite(geo) * 100000) / 100000;
		PositionLongitude =  Math.floor(GPSCalculator.EvaluateLaenge(geo) * 100000) / 100000;

    }
}
