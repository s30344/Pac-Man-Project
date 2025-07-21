import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu(){

        createMenuPanel();
        this.setIconImage(new ImageIcon("ghost.png").getImage());
        this.setLayout(new GridLayout(0,1));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pac-man");
        this.setResizable(false);
        this.pack();

    }

    public void createMenuPanel(){

        Border eb = BorderFactory.createEmptyBorder();

        JButton playButton = new JButton();
        JButton leaderboardButton = new JButton();
        JButton exitButton = new JButton();

        playButton.setBorder(eb);
        leaderboardButton.setBorder(eb);
        exitButton.setBorder(eb);

        playButton.setFocusable(false);
        leaderboardButton.setFocusable(false);
        exitButton.setFocusable(false);

        playButton.setBackground(new Color(0xFFEA07));
        exitButton.setBackground(new Color(0xFFEA07));
        leaderboardButton.setBackground(new Color(0xFFEA07));

        playButton.setIcon(new ImageIcon("newgame.png"));
        leaderboardButton.setIcon(new ImageIcon("highscores.png"));
        exitButton.setIcon(new ImageIcon("exit.png"));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new Leaderboard());
                dispose();
            }

        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new MazeChooseFrame());
                dispose();
            }
        });

        JLabel title = new JLabel();

        title.setIcon(new ImageIcon("logo.png"));
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBackground(new Color(0xFFEA07));
        title.setOpaque(true);

        this.add(title);

        JPanel b1 = new JPanel();
        b1.add(playButton);
        b1.setBackground(new Color(0xFFEA07));

        this.add(b1);

        JPanel b2 = new JPanel();
        b2.add(leaderboardButton);
        b2.setBackground(new Color(0xFFEA07));

        this.add(b2);

        JPanel b3 = new JPanel();
        b3.setBackground(new Color(0xFFEA07));
        b3.add(exitButton);

        this.add(b3);
    }


}
