import javax.swing.*;
import java.awt.*;

public class Collatz {
	int[] collatzNumbers;
	
	public static void main(String[] args) {
		new Collatz();
	}
	
	public Collatz() {
		collatzNumbers = new int[150];
		collatzNumbers[1] = 1;
		for(int n = 2; n < 143; n++) {
			
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
		
		new Window(collatzNumbers);
	}
}

class Window extends JFrame {
	int[] collatzNumbers;
	int window_height = 800, window_width = 1540;
	int x = 60, y = 60;
	
	public Window(int[] collatzNumbers) {
		this.collatzNumbers = collatzNumbers;
		setTitle("Collatz Conjecture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(window_width, window_height);
		setResizable(false);
		move(120, 120);
        setVisible(true);
    }
	
	@Override
    public void paint(Graphics g) {
        super.paint(g); 
        drawAxes(g);
		graph(g);
    }
	
	public void drawAxes(Graphics g) {
		//drawing both axes
		g.drawLine(x, y, x, window_height - 60); 
		g.drawLine(x, window_height - 60, window_width - 60, window_height - 60);
		
		//making and numbering the X axe
		int current_height = window_height - 60, start = x;
		for(int x = start, i = 0; x <= window_width - 60; x += 10, i++) {
			g.drawLine(x, current_height + 5, x, current_height - 5);
			if(i % 10 == 0)
				g.drawString(i + "", x - 10, window_height - 35);
		}
		
		//making a grid on the Y axe
		for(int y = current_height; y >= 60; y -= 10)
			g.drawLine(x - 5, y, x + 5, y);
		
		//numbering the Y axe
		for(int num = 0; num <= 9000; num += 1000)
			g.drawString((-num + 9000) + "", x - 40, 120 + 69 * num / 1000);
		g.drawString("9232", x - 40, 67);	
	}
	
	public void graph(Graphics g) {
		int coordY = window_height - 60;
		
		
		for(int i = 1, coordX = x; i < collatzNumbers.length; i++, coordX += 10) {
			double currentNumber = (collatzNumbers[i] / 9232.0) * 680;

			g.setColor(Color.BLUE);
			g.fillRect(coordX, (int)(coordY - currentNumber), 10, (int)currentNumber);
		}
	}
}


