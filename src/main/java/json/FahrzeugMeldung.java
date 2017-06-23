package json;

public class FahrzeugMeldung extends BaseItcs {
    
	private static final long serialVersionUID = 3007106236456340862L;

	public int LinieNr;
    
    public int KursNr;
    
    public String Betriebstag;
    
    public int Haltestelle;
    
    public Boolean AufHaltestelle;
    
    public Boolean FahrplanlageGemessen;
    
    public Boolean ManuelleEingabe ;
    
    public String Fahrplanlagetyp;
    
    public String Fahrplanlage;
    
    public int FahrplanlageEinfahrt;
    
    public Boolean PositionBekannt;
    
    public long PositionLongitude;
    
    public long PositionLatitude;
    
    public Boolean PositionNorth;
    
    public Boolean PositionEast;
    
    public String SendeTag;
    
    public String KfzKennzeichen;
    
    public int Haltestellennummer;
    
    public String Aktualisierungsgrund;
    
    public Boolean HaltestelleManuellGesetzt;
    
    public String OrtsNummerVorher;
    
    public String OrtstypVorher;
    
    public String OrtsNummerNachher;
    
    public String OrtsTypNachher;
    
    public int RelEntfernungWegebandMeter;
    
    public int AbsoluteEntfernungWegebandMeter;
    
    public String ZustandOrtung;
    
    public int AnzahlGpsSatelliten;
    
    public Boolean DifferentiellesGps;
    
    public int GpsGeschwindigkeitInKmH;
    
    public int TachoGeschwindigkeitIn10TelKmH;
    
    public int GpsFahrtrichtungIn100StelGrad;
    
    public String TagLetzteGpsPosition;
    
    public String ZeitLetzteGpsPosition;
    
    public String KmErfassung;
    
    public int KilometerstandInKm;
    
    public int KilometerstandInMeter;
    
    public int RadimpulszaehlerStand;
    
    public Boolean TuerOffen;
    
    public Boolean MotorZuendungEin;
    
    public Boolean StromversorgungOk;
}
