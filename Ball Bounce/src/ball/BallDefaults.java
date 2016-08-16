package ball;
import universal.PanelDimensions;

public class BallDefaults {
	
	private static final int X = 200;
	private static final int Y = 200;
	private static final int DIAMETER = 25;
	
	private BallDefaults() { }
	
	static {
		if (X + DIAMETER > PanelDimensions.getX() || Y + DIAMETER > PanelDimensions.getY()) {
			throw new IllegalStateException("Ball defaults are outside panel");
		}
	}
	
	public static int getX() 
		{ return X; }
	
	public static int getY() 
		{ return Y; }
	
	public static int getDiameter() 
		{ return DIAMETER; }
}
