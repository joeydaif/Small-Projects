package ball;

import java.awt.Color;
import java.util.Random;
import java.awt.Point;

import universal.PanelDimensions;

public class BallGeneral implements Ball {

	private Point coordinates;
	private final Color color;
	private final int diameter;
	private int dx;
	private int dy;
	
	private static final Random COLOR_RNG = new Random();
	private static final int DIRECTION_DEFAULT = 1;
	
	/**
	 * Creates a ball with default starting position
	 * and diameter, and a random color.
	 * @exception IllegalArgumentException Thrown if ball is out of bounds.
	 */
	public BallGeneral() {
		this(defaultCoordinates(), BallDefaults.getDiameter());
	}
	
	/**
	 * Creates a ball with custom starting coordinates,
	 * a default diameter, and a random color.
	 * @param coordinates
	 * @exception IllegalArgumentException Thrown if ball is out of bounds.
	 */
	public BallGeneral(Point coordinates) {
		this(coordinates, BallDefaults.getDiameter());
	}
	
	/**
	 * Creates a ball with custom starting coordinates,
	 * diameter, and a random color.
	 * @param coordinates
	 * @param diameter
	 * @exception IllegalArgumentException Thrown if ball is out of bounds.
	 */
	public BallGeneral(Point coordinates, int diameter) {
		checkBounds(coordinates, diameter);
		this.coordinates 	= coordinates;
		this.color 			= new Color(COLOR_RNG.nextInt(256), COLOR_RNG.nextInt(256), COLOR_RNG.nextInt(256));
		this.diameter 		= diameter;
		setDirectionInitial(DIRECTION_DEFAULT, DIRECTION_DEFAULT);
	}
	
	public static class BallBuilder {
		
		Point coordinates;
		int diameter;
		Color color;
		int dx;
		int dy;
		
		public BallBuilder() {
			this.coordinates 	= defaultCoordinates();
			this.diameter 		= BallDefaults.getDiameter();
			this.color 			= new Color(COLOR_RNG.nextInt(256), COLOR_RNG.nextInt(256), COLOR_RNG.nextInt(256));
			this.dx 			= DIRECTION_DEFAULT;
			this.dy 			= DIRECTION_DEFAULT;
		}
		
		public BallBuilder setdiameter(int val) 			{ this.diameter = val;		return this; }
		
		public BallBuilder setStartPosition(Point point) 	{ this.coordinates = point;	return this; }
		
		public BallBuilder setColor(Color newColor) 		{ this.color = newColor;	return this; }
		
		public BallBuilder setDx(int val) 					{ this.dx = val; 			return this; }
		
		public BallBuilder setDy(int val) 					{ this.dy = val;			return this; }
		
		public Ball build() { return new BallGeneral(this); }
	}
	
	/**
	 * The constructor for the BallBuilder
	 * @param builder The BallBuilder
	 * @exception IllegalArgumentException Thrown if ball is out of bounds.
	 */
	BallGeneral(BallBuilder builder) {
		checkBounds(builder.coordinates, builder.diameter);
		this.coordinates 	= builder.coordinates;
		this.diameter 		= builder.diameter;
		this.color 			= builder.color;
		setDirectionInitial(builder.dx, builder.dy);
	}
	
	/**
	 * Sets the initial direction of the ball.
	 * If the ball is on the boundary, ensures its direction is inwards.
	 * @param dx The x direction of the ball.
	 * @param dy The y direction of the ball.
	 */
	private void setDirectionInitial(int dx, int dy) {
		this.dx = boundaryDirectionChecker(this.coordinates.x, dx, PanelDimensions.getX());
		this.dy = boundaryDirectionChecker(this.coordinates.y, dy, PanelDimensions.getY());
	}
	
	/**
	 * Checks the direction of the ball on a boundary to ensure it doesn't go off the panel.
	 * @param position The current position of the ball.
	 * @param direction The direction the ball is traveling.
	 * @param maxDimension The farthest boundary of the ball.
	 * @return The updated direction of the ball.
	 */
	private int boundaryDirectionChecker(int position, int direction, int maxDimension) {
		if (direction == 1 && position == 0 || direction == -1 && position + getDiameter() == maxDimension) {
			return direction *= -1;
		}
		return direction;
	}
	
	public Point getCoordinates() 
		{ return new Point(this.coordinates.x, this.coordinates.y); }
	
	public Color getColor() 
		{ return this.color; }
	
	public int getDiameter() 
		{ return this.diameter; }
	
	/**
	 * Checks the bounds of a ball to make sure it is within the panel.
	 * @param coordinates The coordinates to check.
	 * @param diameter The diameter to check.
	 */
	static void checkBounds(Point coordinates, int diameter) {
		if (coordinates.x < 0 || coordinates.y < 0 || 
				coordinates.x > PanelDimensions.getX() - diameter || 
				coordinates.y > PanelDimensions.getY() - diameter) {
			throw new IllegalArgumentException("Position out of bounds");
		}
	}
	
	/**
	 * Moves the ball one tick. Changes their direction if they are touching a wall.
	 */
	public void moveBall() {
		if (this.coordinates.x == 0 || this.coordinates.x + getDiameter() == PanelDimensions.getX()) {
			this.dx *= -1;
		}
		if (this.coordinates.y == 0 || this.coordinates.y + getDiameter() == PanelDimensions.getY()) {
			this.dy *= -1;
		}
		this.coordinates.translate(dx, dy);
	}
	
	/**
	 * Creates a string representation of the ball.
	 */
	@Override public String toString() {
		return (color + " ball that is " + getDiameter() + " pixels in diameter.");
	}
	
	static Point defaultCoordinates() {
		return new Point(BallDefaults.getX(), BallDefaults.getY());
	}
}