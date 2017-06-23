package json;


public class FahrtAnmeldung extends BaseItcs{
    
	private static final long serialVersionUID = -837478486699744976L;

	public String Betreiber;
    
    public long FahrplanID;
    
    public int FahrplanNr;
    
    public long DienstplanID;
    
    public long DienstplaneintragsID;
    
    public int DienstplanNr;
    
    public int LinieNr;
    
    public int KursNr;
    
    public String Betriebstag;
    
    public String Fahrtrichtung;
    
    public int RichtungNr;
    
    public Boolean StartHaltestelleBekannt;
    
    public int StartHaltestelle;
    
    public Boolean EndhaltestelleBekannt;
    
    public int Endhaltestelle;
    
    public String SollAbfahrtszeit;
    
    public Boolean Verstaerker;
    
    public String FahrzeugNr;
    
    public String FahrerNr;
    
    public String KmErfassung;
    
    public int Kilometerstand;
    
    public Boolean PositionBekannt;
    
    public long PositionLongitude;
    
    public long PositionLatitude;
    
    public Boolean PositionNorth;
    
    public Boolean PositionEast;
    
    public String SendeTag;
    
    public String Fahrtart;
    
    public int Leistungsartnummer;
    
    public Boolean SonderfahrtAnzulegen;
}
