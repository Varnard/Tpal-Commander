package layouts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An example of a {@link CardLayout}.
 * 
 * @author Dawid Weiss
 */
public final class CardLayoutExample {
    private final static String [] CARDS = new String [] {
        "card1", "card2", "card3"  
    };

    /**
     * Currently shown card index in {@link #CARDS}.
     */
    private int currentCard = 0;

    /**
     * No instances.
     */
    private CardLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final CardLayout cardsLayout = new CardLayout();
        final JComponent cardPanel = createCards(cardsLayout);
        panel.add(cardPanel, BorderLayout.CENTER);

        final JButton nextButton = new JButton("Next");
        panel.add(nextButton, BorderLayout.EAST);
        nextButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentCard = (currentCard + 1) % CARDS.length;
                    cardsLayout.show(cardPanel, CARDS[currentCard]);
                }
            }
        );

        cardsLayout.show(cardPanel, CARDS[currentCard]);
        return panel;
    }

    /**
     * 
     */
    private JComponent createCards(CardLayout cardsLayout) {
        final JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(cardsLayout);

        // Card 1
        final JPanel card1 = new JPanel();
        card1.add(new JButton("Card 1/a"));
        card1.add(new JButton("Card 1/b"));
        cardsPanel.add(card1, CARDS[0]);

        // Card 2
        cardsPanel.add(new JTextField("Card 2"), CARDS[1]);
        
        // Card 3
        cardsPanel.add(new JLabel("Card 3"), CARDS[2]);

        return cardsPanel;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final CardLayoutExample instance = new CardLayoutExample();

        final JFrame frame = new JFrame(CardLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
