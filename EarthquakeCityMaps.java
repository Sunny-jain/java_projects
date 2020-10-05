import java.util.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

public class EarthquakeCityMaps extends PApplet {

private UnfoldingMap map;
private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

public void setup()
{
size(960,600,OPENGL); //drawing up canvas
map=new UnfoldingMap(this,200,50,700,500,new Google.GoogleMapProvider()); //building map
map.zoomToLevel(2); //for zoom in click level
MapUtils.createDefaultEventDispatcher(this, map);
List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL); //for the features of the loctions in data set
List<Marker> markers=new ArrayList<Marker>(); //list of the marker of loctions in data set
for(PointFeature i:earthquakes)
{
markers.add(createMarker(i)); //passing through the function for creating markers
}
map.addMarkers(markers); // displaying markers to the map
}
private Marker createMarker(PointFeature i) {
//creating markers
SimplePointMarker marker = new SimplePointMarker(i.getLocation());

//getting value of magnitude
float mag=(float) i.getProperty("magnitude");


//applying condition to the markers
if(mag<4.5)
{
marker.setColor(color(255,255,0));
marker.setRadius(10);
}
else if(mag>=4.5 && mag<6)
{
marker.setColor(color(0,0,255));
marker.setRadius(15);
}
else
{
marker.setColor(color(255,0,0));
marker.setRadius(20);
}
return marker;
}
public void draw()
{
background(210); //background color black
map.draw(); //for rendering map
addkey(); //rectangle for the info
}
private void addkey()
{
//for rectangular box
rectMode(CORNER);
fill(70, 70, 70);
rect(25, 50, 150, 250);
textSize(12);
fill(255);
text("Earthquake Key", 50, 75, 200, 400);
// red
fill(255, 0, 0);
ellipse(50, 125, 15, 15);
fill(255);
text("6.0+ Magnitude", 60, 125);
// yellow
fill(0, 0, 255);
ellipse(50, 175, 10, 10);
fill(255);
text("4.5+ Magnitude", 60, 175);
// blue
fill(255, 255, 0);
ellipse(50, 225, 6, 6);
fill(255);
text("Below 4.5", 60, 225);
}
}

