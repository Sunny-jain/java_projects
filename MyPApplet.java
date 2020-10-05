import processing.core.PApplet;
import processing.core.PImage;
	


public class MyPApplet extends PApplet{
private PImage backgroundImg;
public void setup()
{
size(500,500); //setting the size of canvas
backgroundImg=loadImage("palmTrees.jpg","jpg"); //setting the background image
}
public void draw()
{
image(backgroundImg,0,0); //displaying the bimage
backgroundImg.resize(0,height); //resizing the bimage
int[] color=suncolorset(second());
fill(color[0],color[1],0); //filling color
ellipse(width/4,height/5,width/5,height/5); //drawing sun
}
public int[] suncolorset(float second)
{
int[] rgb=new int[3]; //for shades of color

float diffFrom30 = Math.abs(30-second);

float ratio = diffFrom30/30; //ratio for the shades according to time in seconds
rgb[0] = (int)(255*ratio); //getting the shade of color
rgb[1] = (int)(255*ratio);
rgb[2] = 0;

return rgb;
}
}
