import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, KeyListener{

	private int space;
	private int speed;
	private final int WIDTH1 = 500; 	//Dimension of frame
	private final int HEIGHT1 = 700;	
	private final int WIDTH2 = 80;		//Dimension of car
	private final int HEIGHT2 = 70;
	private int move = 25;				//Speed of car 			
	private int count = 1;
	private Rectangle car;
	private ArrayList<Rectangle> wave;
	private Random rand;
	Timer timer;
	
	public Road(){
		wave = new ArrayList<Rectangle>();
		rand = new Random();
		car = new Rectangle(WIDTH1/2-90, HEIGHT1-100, WIDTH2, HEIGHT2);
		space = 300;		//Space between spawn cars
		speed = 4;			//Speed of spawn cars
		addKeyListener(this);
		setFocusable(true);
		spawnCars(true);
		spawnCars(true);
		spawnCars(true);
		spawnCars(true);
		spawnCars(true);
		spawnCars(true);
		timer = new Timer(20, this);
		timer.start();
	}
	
	public void spawnCars(Boolean first) {		
		int pos = rand.nextInt(2);
		int x = 0;
		int y = 0;
		int w = WIDTH2;
		int h = HEIGHT2;
		
		if (pos == 0) {
			x = WIDTH1/2 - 90;
		}
		else {
			x = WIDTH1/2 + 10;
		}
		
		if (first) {
			wave.add(new Rectangle(x, y - 100 - (wave.size() * space), w, h));
		}
		else {
			wave.add(new Rectangle(x, wave.get(wave.size() - 1).y - 300, w, h));
		}
		
	}
	
	public void moveUp() {
		if (car.y - move < 0) {}
		else {
			car.y -= move;
		}
	}
	public void moveDown() {
		if(car.y + move + car.height > HEIGHT1 - 1) {}
		else {
			car.y += move;
		}
	}
	public void moveLeft() {
		if(car.x - move < WIDTH1 / 2 - 90) {}
		else {
			car.x -= move;
		}
	}
	public void moveRight() {
		if(car.x + move > WIDTH1 / 2 + 10) {}
		else {
			car.x += move;
		}
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.cyan);
		g.fillRect(0,0, WIDTH1, HEIGHT1);
		
		g.setColor(Color.gray);
		g.fillRect(WIDTH1/2 - 100, 0, 200, HEIGHT1);
		
		g.setColor(Color.red);
		g.fillRect(car.x, car.y, car.width, car.height);
		
		g.setColor(Color.BLUE);
		g.drawLine(WIDTH1/2, 0, WIDTH1/2, HEIGHT1);
		
		g.setColor(Color.green);
		for(Rectangle rect:wave) {
			g.fillRect(rect.x,rect.y,rect.width,rect.height);
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Rectangle rect;
		count++;
		for(int i=0; i<wave.size(); i++) {
			rect=wave.get(i);
            if(count%1000 == 0){
                if(move < 20){
                    speed++;
                    move += 20;
                }
            }
            rect.y += speed;
		}
		
		for(Rectangle r:wave) {
			if(r.intersects(car)) {
				car.y = r.y + HEIGHT2;
			}
		}
		
		for(int i = 0; i < wave.size(); i++) {
			rect = wave.get(i);
			if(rect.y + rect.height > HEIGHT1) {
				wave.remove(rect);
				spawnCars(false);
			}
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		default:
			break;		
		}		
	}
}
