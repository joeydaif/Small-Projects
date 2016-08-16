import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;

import ball.*;
import ball.BallGeneral.BallBuilder;
import universal.PanelDimensions;

public class BouncyBallMain {
	
	public static final int BALLS_MAX = 100;
	public static final int BALL_DIAMETER_MAX = 80;

	public static void main(String[] args) {
		Ball[] balls = createBalls();
		DrawingPanel ballPanel = new DrawingPanel(PanelDimensions.getX(), PanelDimensions.getY());
		Graphics ballDrawer = ballPanel.getGraphics();
		
		while (true) {
			ballSimulator(balls, ballDrawer);
		}
	}
	
	/**
	 * 
	 * @param balls
	 * @param ballDrawer
	 */
	public static void ballSimulator(Ball[] balls, Graphics ballDrawer) {
		for (Ball currentBall : balls) {
			eraseBall(currentBall, ballDrawer);
			currentBall.moveBall();
			drawBall(currentBall, ballDrawer);
		}
	}
	
	/**
	 * Creates a random number of balls with random properties.
	 * @return An array of the balls.
	 */
	public static Ball[] createBalls() {
		Random rng = new Random();
		int ballCount = rng.nextInt(BALLS_MAX - 1) + 1;
		Ball[] balls = new Ball[ballCount];
		for (int i = 0; i < ballCount; i++) {
			balls[i] = ballMaker(rng);
		}
		return balls;
	}
	
	/**
	 * 
	 * @param rng
	 * @return
	 */
	public static Ball ballMaker(Random rng) {
		int ballDiameter = rng.nextInt(BALL_DIAMETER_MAX - 1) + 1;
		Point startCoordinates = new Point
				(rng.nextInt(PanelDimensions.getX() - ballDiameter), 
				 rng.nextInt(PanelDimensions.getY() - ballDiameter));
		Color color = new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
		int dx = rng.nextInt(3) - 1; // Sets to a value of {-1, 0, 1}
		int dy = rng.nextInt(3) - 1;
		BallBuilder builder = new BallGeneral.BallBuilder();
		return builder.setColor(color).setdiameter(ballDiameter).
				setStartPosition(startCoordinates).setDx(dx).setDy(dy).build();
	}
	
	/**
	 * 
	 * @param currentBall
	 * @param ballDrawer
	 */
	public static void eraseBall(Ball currentBall, Graphics ballDrawer) {
		ballDrawer.setColor(Color.WHITE);
		for (int i = -2; i <= 3; i++) {
			ballDrawer.drawOval(currentBall.getCoordinates().x - i, currentBall.getCoordinates().y - i, 
					currentBall.getDiameter() + i, currentBall.getDiameter() + i);
		}
	}
	
	/**
	 * Draws the balls in their current position, and then moves them
	 * to a different position to draw them at in the future.
	 * @param currentBall The current ball to draw and then move.
	 * @param ballDrawer The graphics drawer for the balls.
	 */
	public static void drawBall(Ball currentBall, Graphics ballDrawer) {
		ballDrawer.setColor(currentBall.getColor());
		ballDrawer.fillOval(currentBall.getCoordinates().x, currentBall.getCoordinates().y, 
				currentBall.getDiameter(), currentBall.getDiameter());
	}
}