import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Car extends JFrame{

	public static void main(String[] args) {
		JFrame frame = new JFrame("Cars 2D");
		Road r = new Road();
		frame.add(r);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	

	}

}
