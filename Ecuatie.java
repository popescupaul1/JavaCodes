public class Ecuatie {

    double delta;
    String x1, x2;

    public Ecuatie (double a, double b, double c) {
        delta = b * b - 4 * a * c;
        if(delta >= 0) {
            x1 = (-b + Math.sqrt(delta)) / 2 * a + "";
            x2 = (-b - Math.sqrt(delta)) / 2 * a + "";
        }
        else {
            x1 = -b / (2 * a) + " + " + Math.sqrt(-delta) / (2 * a) + "i"; 
            x2 = -b / (2 * a) + " - " + Math.sqrt(-delta) / (2 * a) + "i"; 
        }
        afisareHTML(a, b, c, x1, x2);
    }

    public void afisare() {
        System.out.println("Solutie: ");
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
    }

    public void afisareHTML(double a, double b, double c, String x1, String x2) {
        System.out.println("<html>");
        System.out.println("<head>");
        System.out.println("<title>Ecuatie Gradul 2</title>");
        System.out.println("</head>");
        System.out.println("<body>");
        System.out.println("<center><h1> Titlu </h1></center>");
        System.out.println("<h2> Pentru variabilele a = " + a + ", b = " + b + ", c = " + c + ", solutia este: " + x1 + x2 +" </h2>");
        System.out.println("</body>");
        System.out.println("</html>");
    }

    public static void main(String[] args) {
        double a = 0, b = 0, c = 0;
        
        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            c = Double.parseDouble(args[2]);
        }
        catch(Exception e){}
        Ecuatie ec2 = new Ecuatie(a, b, c);
    }
}