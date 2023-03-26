/*
 * Realizzare un’applicazione che tramite un’opportuna GUI permetta di visualizzare in una JLabel il passaggio 
 * (sulla reale precisione ne discuteremo successivamente) dei secondi dopo avere premuto un JButton. 
 * Il JButton avvia un Thread che conta da 1 a quando viene fermato (a discrezione da un altro JButton o dallo stesso JButton che ha avviato il conteggio).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JFrame implements ActionListener{
	static JFrame frame;
	static JLabel label;
	static JButton start, stop, restart;
	static JPanel pnlNord, pnlCenter, pnlSud;
	static boolean bb=false;
	Count thread = null;

	public static void main(String[] args) { 
		Timer t = new Timer();

		frame = new JFrame("Timer");

		pnlNord = new JPanel();
		pnlNord.setLayout(new FlowLayout());
		start = new JButton("Start");
		pnlNord.add(start);
		start.addActionListener(t);

		pnlCenter = new JPanel();
		pnlCenter.setLayout(new FlowLayout());
		label = new JLabel("Secondi: 0");
		pnlCenter.add(label);

		pnlSud = new JPanel();
		pnlSud.setLayout(new GridLayout(1,2));
		restart = new JButton("Restart");
		stop = new JButton("Stop");
		pnlSud.add(restart);
		pnlSud.add(stop);
		stop.addActionListener(t);
		restart.addActionListener(t);

		frame.add(pnlNord,BorderLayout.NORTH);
		frame.add(pnlCenter,BorderLayout.CENTER);
		frame.add(pnlSud,BorderLayout.SOUTH);

		frame.setSize(400,200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b =(JButton)e.getSource();
		
		if(b == start && bb == false){
			thread = new Count(this);
			bb=true;
			thread.start();
		}else if(b == start && bb == true){
			thread.riprendi();
		}else if(b == stop){
			thread.attendi();
		}else if(b == restart){
			thread.termina();
			bb=false;
			thread = new Count(this);
			thread.start();
		}
	}
}