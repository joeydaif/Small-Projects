package ball;
import java.awt.Color;
import java.awt.Point;

public class MutableBall extends BallGeneral {
		
	private int mutableDiameter;
	private Color mutableColor;
	
	/**
	 * Creates a ball with default starting position, diameter, and random color.
	 */
	public MutableBall() {
		this(defaultCoordinates(), BallDefaults.getDiameter());
	}
	
	/**
	 * 
	 * @param coordinates
	 */
	public MutableBall(Point coordinates) {
		this(coordinates, BallDefaults.getDiameter());
	}
	
	/**
	 * 
	 * @param coordinates
	 * @param diameter
	 */
	public MutableBall(Point coordinates, int diameter) {
		super(coordinates, diameter);
		trueFieldsSetter();
	}
	
	/**
	 * 
	 * @param builder
	 */
	private MutableBall(Builder builder) {
		super(builder);
		trueFieldsSetter();
	}
	
	public static class Builder extends BallGeneral.BallBuilder {
		@Override public Ball build() 
			{ return new MutableBall(this); }
	}
	
	/**
	 * 
	 */
	private void trueFieldsSetter() {
		this.mutableDiameter = super.getDiameter();
		this.mutableColor = super.getColor();
	}
	
	/**
	 * Changes the radius of the ball to a new value.
	 * @param newDiameter The new value to change the radius to.
	 * @exception IllegalArgumentException Thrown if radius is less than 0.
	 * @exception IllegalArgumentException Thrown if new radius is out of bounds.
	 */
	public void setDiameter(int newDiameter) {
		if (newDiameter < 0) {
			throw new IllegalArgumentException("Diameter cannot be less than 0");
		}
		checkBounds(getCoordinates(), newDiameter);
		this.mutableDiameter = newDiameter;
	}
	
	public void setColor(Color newColor) 
		{ this.mutableColor = newColor; }
	
	@Override public Color getColor() 
		{ return this.mutableColor; }
	
	@Override public int getDiameter() 
		{ return this.mutableDiameter; }
	
	@Override public String toString() {
		return (mutableColor + ", mutable ball that is " + getDiameter() + " pixels in diameter.");
	}
}