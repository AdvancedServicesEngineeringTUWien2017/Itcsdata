package json;

import java.io.Serializable;

public class ChartData implements Serializable{
	
	private static final long serialVersionUID = -4385421302754623587L;
	
	public ChartTyp chartTyp;
	public String time;
	public int count = 0;
	
	public ChartData(ChartTyp _chartTyp, String _time)
	{
		chartTyp = _chartTyp;
		time = _time;
		count ++;
	}
	
	public void AddCount()
	{
		count ++;
	}
	
}
