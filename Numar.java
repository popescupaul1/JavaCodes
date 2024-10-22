import java.awt.*;

public class Numar{
	int asc, desc;
	int[] kaprekar;
	
	public static void main(String args[]){
		new Numar();
	}
	
	public Numar() {
		kaprekar = new int[10000];
		
		for(int i = 0; i <= 9999; i++) {
			int frecv = 0, res = i, prev_res = -1;
			
			if(i < 10)
				res = i * 1000;
			else if(i < 100)
				res = i * 100;
			else if(i < 1000)
				res = i * 10;
			
			while(res != prev_res) {
				frecv++;
				functie(res);
				prev_res = res;
				res = desc - asc;
			}
			
			kaprekar[i] = frecv;
			System.out.print(kaprekar[i] - 1 + " ");
		}
		
		new Fereastra(kaprekar);
	}
	
	static void merge(int x[], int l, int m, int r) {
		int size1 = m - l + 1;
		int size2 = r - m;
		
		int[] L = new int[size1];
		int[] R = new int[size2];
		
		for(int i = 0; i < size1; i++)
			L[i] = x[l + i];
		for(int i = 0; i < size2; i++)
			R[i] = x[m + 1 + i];
		
		
		int i = 0, j = 0;
		int k = l;
		
		while(i < size1 && j < size2) {
			if(L[i] <= R[j])
				x[k] = L[i++];
			else 
				x[k] = R[j++];
			k++;
		}
		
		while(i < size1)
			x[k++] = L[i++];
		
		while(j < size2)
			x[k++] = R[j++];
	}
	
	static void mergeSort(int x[], int l, int r) {
		if(l < r) {
			int m = l + (r - l) / 2;
			
			mergeSort(x, l, m);
			mergeSort(x, m + 1, r);
			
			merge(x, l, m, r);
		}
	}
	
	public void functie(int n) {
		int[] numar = new int[5];
		int k = 0;
		
		while(n > 0) {
			numar[k++] = n % 10;
			n /= 10;
		}
		
		mergeSort(numar, 0, k - 1);

		asc = 0; desc = 0;
		for(int i = 0; i < k; i++) {
			asc = asc * 10 + numar[i];
			desc = desc * 10 + numar[k - 1 - i];
		}
	}
}

class Fereastra extends Frame{
	int[] kaprekar;
	
	public Fereastra(int[] kaprekar) {
		this.kaprekar = kaprekar;
		this.setSize(954, 1000);
		this.setVisible(true);
		this.setTitle("Kaprekar's Number - Paul");
	}

	public void paint(Graphics g) {
		AfisareTitlu(g);
		
		int x = 27, y = 75;
		for(int i = 0; i < kaprekar.length; i++) {
			
            Color culoare = CuloarePatrat(kaprekar[i] - 1);
			g.setColor(culoare);
			g.fillRect(x, y, 9, 9);
			
			x += 9;
			
			if(x >= 927) {
				y += 9;
				x = 27;
			}
		}
	}
	
	private void AfisareTitlu(Graphics g) {
        String title = "Numarul lui Kaprekar";
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(titleFont);
        g.setColor(Color.BLACK);

        FontMetrics fm = g.getFontMetrics(titleFont);
        int titleWidth = fm.stringWidth(title);
        int titleX = (this.getWidth() - titleWidth) / 2; 
        int titleY = 60;

        g.drawString(title, titleX, titleY);
    }
	
	private Color CuloarePatrat(int index) {
        switch (index) {
            case 1: return new Color(102, 135, 237);
            case 2: return new Color(150, 185, 255);
            case 3: return new Color(198, 215, 241);
            case 4: return new Color(236, 209, 195);
            case 5: return new Color(247, 166, 136);
            case 6: return new Color(226, 107, 85);
            case 7: return new Color(181, 9, 39);
            default: return new Color(60, 79, 195);
        }
    }
}