import java.awt.*;
import javax.swing.*;

class GUI{
	public static void main(String[] args) {
		JButton jButton1 = new JButton("Student");
		JButton jButton2 = new JButton("College");
		JButton jButton3 = new JButton("Central Authority");
		JFrame b = new JFrame("Welcome Page");
		System.out.println(new JLabel("Saami saranam"));
		jButton1.setBounds(130,100,95,30);
		jButton2.setBounds(130,150,95,30);
		jButton3.setBounds(105,200,150,30);
		b.add(jButton1);
		b.add(jButton2);
		b.add(jButton3);
		b.setSize(400,400);
		b.setLayout(null);
		b.setVisible(true); 
	}
}