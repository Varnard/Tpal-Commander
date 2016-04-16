package swingthreads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * <B>THIS CODE OS FOR DEMONSTRATION ONLY! IT IS INCORRECT!</B>
 * 
 * Try to run it and obscure the window with another one -- it will not refresh
 * properly.
 *
 * @author Dawid Weiss
 */
public class BadExample {
	public void start() {
		JFrame frame = new JFrame("Swing Freeze Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button = new JButton("Freeze me !");
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("Sleeping...");
				try {
					for (int i=0;i<20;i++) {
						Thread.sleep(1000);
						button.setText("Count: " + i);
					}
				} catch (InterruptedException e1) {
				}
				button.setText("Freeze me !");
			}
		});

		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BadExample().start();
	}
}