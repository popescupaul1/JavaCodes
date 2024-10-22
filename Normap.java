import java.awt.*;

public class Normap extends Frame implements Runnable {
	//atributele clasei
	Thread t;
	Color graficColor = Color.red, axeColor = Color.black, fillColor = Color.green;
	Image im;
	Graphics imgr;
	int w = 400, h = 400;
	Font f = new Font("TimesRoman", 0, 16);
	Font f1 = new Font("TimesRoman", 0, 11);
	FontMetrics fm = getFontMetrics(f1);
	boolean flag = true, flaginit = true;
	double p = 0;
		
	public static void main(String args[]) {
		new Normap();
	}
	

	public Normap() {
		//metodele clasei
		Dimension res = getToolkit().getScreenSize();
		setBackground(Color.white);
		setResizable(false);
		resize(w + 100, h + 100);
		move((int)((res.width-(w + 100))/2),(int)((res.height - (h + 100))/2));
		show();
		im = createImage(w, h);
		imgr = im.getGraphics();
		start();
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}
	
	public void run() {
		while(true) {
			repaint();
			try{Thread.sleep(500);}
			catch(InterruptedException e){}
		}
	}
	
	public void paint(Graphics g) {
		if(flaginit) {
			imgr.setColor(Color.white);
			imgr.fillRect(0, 0, w, h);
			imgr.setFont(f);
			axe(imgr);
			g.drawImage(im, 50, 50, this);
			flaginit=!flaginit;
			try{Thread.sleep(500);}
			catch(InterruptedException e){}
		}
		if(flag)
			for(p = 0.15d; p <= 111d; p+=p<1?0.01d:p<2?0.025d:p<10?0.1d:p<20?0.25d:p<40?0.5d:p<80?5d:10d)
				desen(g, p);
		else
			for(p = 0.15d; p <= 111d; p-=p<1?0.01d:p<2?0.025d:p<10?0.1d:p<20?0.25d:p<40?0.5d:p<80?5d:10d)
				desen(g, p);
		
		flag =!flag;
	}
	
	void axe(Graphics g) {
		g.setColor(axeColor);
		g.setFont(f);
		g.drawLine(0, h/2, w, h/2);
		g.drawLine(w/2, 0, w/2, h);
		g.drawLine(w/2, 0, w/2 - 3, 5);
		g.drawLine(w/2, 0, w/2 + 3, 5);
		g.drawLine(w, h/2, w-5, h/2 - 3);
		g.drawLine(w, h/2, w-5, h/2 + 3);
		g.drawString("(0,0)", w/2+4, h/2 + 14);
		g.drawString("x", w-8, h/2 + 14);
		g.drawString("y", w/2 + 6, 8);
		g.drawString("(0,0)", w/2+4, h/2 + 14);
		g.setFont(f);
		for(int i = 20; i < w; i += 20)
			g.drawLine(i, h/2-2, i, h/2+2);
		for(int i = 20; i < h; i += 20)
			g.drawLine(w/2-2, i, w/2+2, i);
	}
	
	void patrat(Graphics g, int[] x, int[] y, boolean flag) {
		if(flag) {
			g.setColor(fillColor);
			g.fillPolygon(x, y, 4);
			g.setColor(graficColor);
			g.drawPolygon(x, y, 4);
			axe(g);
		} else {
			g.setColor(axeColor);
			g.drawPolygon(x, y, 4);
		}
	}
	
	void patrat1(Graphics g) {
		int[] x = {0, w/2, w, w/2}; int[] y = {h/2, 0, h/2, h};
		patrat(g, x, y, true);
	}
	
	void patrat1_(Graphics g) {
		int[] x = {0, w/2, w, w/2}; int[] y = {h/2, 0, h/2, h};
		patrat(g, x, y, false);
	}
	
	void patrat2(Graphics g) {
		int[] x = {0, w, w, 0}; int[] y = {0, 0, h, h};
		patrat(g, x, y, true);
	}
	
	void patrat2_(Graphics g) {
		int[] x = {0, w, w, 0}; int[] y = {0, 0, h, h};
		patrat(g, x, y, false);
	}
	
	void disc(Graphics g) {
		g.setColor(fillColor);
		g.fillOval(0, 0, w, h);
		g.setColor(graficColor);
		g.drawOval(0, 0, w, h);
		axe(g);
	}
	
	void cerc(Graphics g) {
		g.setColor(axeColor);
		g.drawOval(0, 0, w, h);
	}
	
	void grafic(Graphics g, double p) {
		int vertex_count = 2 * w;
		int[] vertex_x = new int[vertex_count];
		int[] vertex_y = new int[vertex_count];
		for(int x = 0; x < w; x++) {
			double f = f(x - w/2, p);
			vertex_x[x] = x;
			vertex_y[x] = (int)(f + h/2);
			vertex_x[vertex_count - x - 1] = x;
			vertex_y[vertex_count - x - 1] = (int)(-f + h/2);
		}
		
		g.setColor(fillColor);
		g.fillPolygon(vertex_x, vertex_y, vertex_count);
		g.setColor(graficColor);
		g.drawPolygon(vertex_x, vertex_y, vertex_count);
		g.setColor(axeColor);
		g.drawString("p = " + (float)p, w/2 + 40, h/2 - 40);
		axe(g);
	}
	
	double f(double x, double p) {
		return Math.pow((Math.pow(200, p) - Math.pow(Math.abs(x), p)), 1/p); //200 - raza
	}
	
	void desen(Graphics g, double p) {
		imgr.setColor(Color.white);
		imgr.fillRect(0, 0, w, h);
		imgr.setFont(f);
		if(p <= 0.99d) {
			imgr.setColor(axeColor);
			grafic(imgr,p);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(500);}
			catch(InterruptedException e){}
		} else if(0.99d < p && p < 1.01d) {
			imgr.setColor(fillColor=Color.yellow);
			patrat1(imgr);
			imgr.setColor(axeColor);
			imgr.drawString("p = 1", w/2+40, h/2-40);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(100);}
			catch(InterruptedException e){}
			imgr.setColor(fillColor = Color.green);
		} else if(p < 1.95d) {
			grafic(imgr, p);
			patrat1_(imgr);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(1000);}
			catch(InterruptedException e){}
		} else if(1.95d < p && p <=2.05d) {
			imgr.setColor(fillColor=Color.yellow);
			disc(imgr);
			patrat1_(imgr);
			imgr.setColor(axeColor);
			imgr.drawString("p = 2", w/2+40, h/2-40);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(100);}
			catch(InterruptedException e){}
			imgr.setColor(fillColor = Color.green);
		} else if(p >= 2.05d && p < 11d) {
			grafic(imgr, p);
			patrat1_(imgr);
			cerc(imgr);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(500);}
			catch(InterruptedException e){}
		} else {
			imgr.setColor(fillColor=Color.yellow);
			patrat2(imgr);
			patrat1_(imgr);
			cerc(imgr);
			imgr.setColor(axeColor);
			imgr.drawString("p = \u221E", w/2+40, h/2-40);
			g.drawImage(im, 50, 50, this);
			try{Thread.sleep(1000);}
			catch(InterruptedException e){}
			imgr.setColor(fillColor = Color.green);
		}
		
		String P = "";
		if(0.97d <= p && p <= 1.03d) P = "" + 1;
		else if(1.92d <= p && p <= 2.08d) P  = "" + 2;
		else if(99d < p) P = "\u221E";
		else P = "" + (float)p;
		setTitle("Sfera S((0,0),200), relativ\u0103 la norma ||.||p, unde p = " + P);
	}
	
}