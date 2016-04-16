package swingthreads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A correct solution using
 * {@link javax.swing.SwingUtilities#invokeLater(java.lang.Runnable)}. 
 * 
 * @author Dawid Weiss
 */
public class InvokeLaterExample {
	public void start() {
		final JFrame frame = new JFrame("Swing Freeze Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button = new JButton("Freeze me !");
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("Sleeping...");
				button.setEnabled(false);

				new Thread() {
					public void run() {
						for (int i=0; i < 5; i++) {
							final int j = i;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
							}
							
							SwingUtilities.invokeLater(
								new Runnable() {
									public void run() {
										button.setText("Count: " + j);
									}
								});
						}
						
						SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									button.setEnabled(true);
									button.setText("Freeze me!");
								}
							});
					}
				}.start();
			}
		});

		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new InvokeLaterExample().start();
	}
}
