package ball;

import java.awt.Color;
import java.awt.Point;

public interface Ball {
	
	public void moveBall();
	public Point getCoordinates();
	public int getDiameter();
	public Color getColor();
	
}
