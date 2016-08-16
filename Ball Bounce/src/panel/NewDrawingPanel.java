package panel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class NewDrawingPanel {
	
	private JFrame frame;
	private JPanel panel;
	private Graphics graphics;	
	
	public NewDrawingPanel(Dimension dimensions) {
		BufferedImage image = new BufferedImage(dimensions.width, dimensions.height , BufferedImage.TYPE_INT_ARGB);
		graphics = image.getGraphics();
		graphics.setColor(Color.BLACK);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(dimensions);
		
		frame = new JFrame("Ball Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Graphics getGraphics() {
		return graphics;
	}
	
	public void setBackground(Color newBackgroundColor) {
		panel.setBackground(newBackgroundColor);
	}
	
	public void setVisible(boolean visibility) {
		frame.setVisible(visibility);
	}
}