import processing.core.PApplet;

public class MyDisplay extends PApplet{
public void setup()
{
size(400,400);
background(200,200,200);
}
public void draw()
{
fill(255,255,0);
ellipse(200,200,390,390);
fill(0,0,0);
ellipse(125,125,50,70);
fill(0,0,0);
ellipse(275,125,50,70);
arc(200,280,100,100,0,PI);
}
}
