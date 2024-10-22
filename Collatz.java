import javax.swing.*;
import java.awt.*;
//import java.awt.geom.AffineTransform;

public class Collatz {
	int[] collatzNumbers;
	
	public static void main(String[] args) {
		new Collatz();
	}
	
	public Collatz() {
		collatzNumbers = new int[1000];
		collatzNumbers[1] = 1;
		for(int n = 2; n < 1000; n++) {
			
			int max = n;
			int number = n;
			while(number != 1) {
				if(number % 2 == 0)
					number /= 2;
				else {
					number = 3 * number + 1;
					if(number > max)
						max = number;
				}
			}
			
			collatzNumbers[n] = max;
		}
		
		new Window();
	}
}

class Window {
	int[] collatzNumbers;
	Rectangle r = new Rectangle();
	int h = r.height;
	int w = r.width;
	
	public Window() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Collatz Conjecture");
		window.setSize(1500, 800);
		window.setVisible(true);
		
		
	}
	
	public void paint(Graphics g) {
		int x = 0, y = -h;
		g.setColor(Color.black);
		g.drawLine(120, 360, 720, 1080);
	}

	//i want to first create x and y axes (invert them using the AffineGeom)
	//then try to create a sort of histogram for the Collatz Conjecture
}
