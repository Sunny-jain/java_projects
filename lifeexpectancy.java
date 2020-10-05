import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class lifeexpectancy extends PApplet{
	UnfoldingMap map;
	Map<String,Float> life;
	List<Feature> countries;
	List<Marker> countriesmarker;
	public void setup()
	{
		size(800,600,OPENGL);		//setting the canvas
		//creating dimensions for the unfolding map
		map=new UnfoldingMap(this,50,50,700,500,new Google.GoogleMapProvider());

		//for events on the map
		MapUtils.createDefaultEventDispatcher(this, map);

		//calling function for getting data from the file
		life=loadLifeExceptency("C:\\Users\\dell\\Desktop\\UCSDUnfoldingMaps\\data\\LifeExpectancyWorldBankModule3.csv");
		
		//getting countries geometric codinates
		countries=GeoJSONReader.loadData(this,"data/countries.geo.json");

		//creating markers
		countriesmarker=MapUtils.createSimpleMarkers(countries);

		//adding markers to map
		map.addMarkers(countriesmarker);

		//modifing markerwith color
		shadeCountries();
	}
	private void shadeCountries()
	{
		for(Marker i:countriesmarker)
		{
			String cid=i.getId();
			if(life.containsKey(cid))
			{
				float lifeexp=life.get(cid);
				int colorl=(int) map(lifeexp,40,90,10,255);
				i.setColor(color(255-colorl,100,colorl));
			}
			else
			{
				i.setColor(color(150,150,150));
			}
		}
	}
	private Map<String, Float> loadLifeExceptency(String file)
	{
		Map<String,Float> life=new HashMap<String,Float>();
		String[] rows=loadStrings(file);
		for(String i:rows)
		{
			String[] column=i.split(",");
			float value=Float.parseFloat(column[4]);
			life.put(column[5], value);
		}
		return life;
	}
	public void draw()
	{
		map.draw();
	}
}
