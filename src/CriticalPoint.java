import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.Graphics;

public class CriticalPoint extends JFrame {

	public static double P, r;
	public static int t;
	
	public CriticalPoint() {
		super("Diagrami");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Diagrami());
		setSize(700, 400);
		llogaritKestin();
		setVisible(true);
	}
	
	public void llogaritKestin() {
		P = Double.parseDouble(JOptionPane.showInputDialog("Shuma e mare hua "));
		t = Integer.parseInt(JOptionPane.showInputDialog("Per kete periudh kohore "));
		r = Double.parseDouble(JOptionPane.showInputDialog("Perqinda e interesit per te cilen i eshte dhene shuma"));
		DecimalFormat a = new DecimalFormat("0.00");
		
		double m = ((P * r)/12)/(1 - Math.pow((1/(1 + r / 12)), 12*t));
		JOptionPane.showMessageDialog(null, "Kesti qe duhet te paguani ne muaj eshte: "+ a.format(m));
	}

	public static void main(String [] args){
		new CriticalPoint();
	}
}

class Diagrami extends JPanel {
	public Diagrami() {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.white);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		GeneralPath p = new GeneralPath();
		p.moveTo(70,50);
		p.lineTo(70,300);
		p.lineTo(520,300);
		g2.drawString("Kesti", 40, 40);
		int j = 5;
		for(int i = 70; i <= 270; i+= 50)
			g2.drawString(j-- + "00", 40, i);
		j = 12;
		for(int i = 140; i <= 484; i+= 86) {
			g2.drawString(j + "", i, 320);
			j += 12;
		}
		/*
		double[] M = new double[4];
		j = 12;
		for(int i = 0; i <= M.length; i++) {
			M[i] = ((P * r)/12)/(1 - Math.pow((1/(1 + r/12)), 12*(i + 1)));
			JOptionPane.showMessageDialog(null, "Kesti qe duhet te paguani ne " + j + " muaj eshte: "+ a.format(m));
			j += 12;
		}*/
		g2.drawString("Muajt", 520, 320);
		double[] M = new double[4];
		for(int i = 0; i < 4; i++)
			M[i] = ((CriticalPoint.P * CriticalPoint.r)/12)/(1 - Math.pow((1/(1 + CriticalPoint.r/12)), 12*(i + 1)));
		p.moveTo(140, M[0]);
		p.lineTo(140, M[1]);
		p.lineTo(M[1], M[2]);
		p.lineTo(M[2], M[3]);
		g2.draw(p);
	}
}





