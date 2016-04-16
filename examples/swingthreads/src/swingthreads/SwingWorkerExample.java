package swingthreads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * A correct solution for the freezing component using
 * {@link javax.swing.SwingWorker}. 
 * 
 * @author Dawid Weiss
 */
public class SwingWorkerExample {

	public void start() {

		JFrame frame = new JFrame("Swing Freeze Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button = new JButton("Freeze me !");
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("Sleeping...");
				button.setEnabled(false);
				
                new SwingWorker<Object, Void>() {
                    public Object doInBackground() {
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
                                }
                            );
                        }
                        
                        return null;
                    }

                    // this method is called on the main event dispatching thread
                    public void done() {
                        button.setText("Freeze me!");
                        button.setEnabled(true);
                    }
                }.execute();
			}
		});

		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new SwingWorkerExample().start();
	}
}
