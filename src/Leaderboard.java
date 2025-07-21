import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Leaderboard extends JFrame implements Serializable, KeyListener {
    Border eb = BorderFactory.createEmptyBorder();
    private Font font = new Font("MV BOLI",Font.PLAIN, 20);
    private List<String> scores;
    private JTextPane leaderboard;
    private JLabel label;
    private JPanel panel;
    private File leaderboardFile = new File("leaderboard.txt");




    public Leaderboard(){
        setLeaderboardPanel();

        this.setIconImage(new ImageIcon("ghost.png").getImage());
        this.setBackground(new Color(0xFFEA07));
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Leaderboard");
        this.setResizable(false);
        this.setSize(500,500);
        this.addKeyListener(this);
        loadScores();
    }
    private void setLeaderboardPanel() {
        panel = new JPanel();
        panel.setBorder(eb);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0xFFEA07));

        label = new JLabel("Leaderboard");
        label.setFont(new Font("MV BOLI",Font.PLAIN, 50));
        label.setForeground(Color.black);
        label.setBorder(eb);

        panel.add(label, BorderLayout.NORTH);
        panel.setSize(100,30);

        leaderboard = new JTextPane();
        leaderboard.setBackground(new Color(0xFFEA07));
        leaderboard.setFont(font);
        leaderboard.setEditable(false);
        leaderboard.setSize(new Dimension(400,500));

        JScrollPane scrollPane = new JScrollPane(leaderboard);

        this.add(panel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }
    public void addScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(leaderboardFile, true))) {
            writer.write(name + " scored " + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadScores();
    }

    private void loadScores() {
        scores = new ArrayList<>();
        if (leaderboardFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(leaderboardFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    scores.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scores.sort((x,y)->Integer.compare(extractScore(y),extractScore(x)));

        StringBuilder scoreText = new StringBuilder();
        for (String score : scores) {
            scoreText.append(score).append("\n");
        }
        leaderboard.setText(scoreText.toString());
    }
    private int extractScore(String score) {
        String[] s = score.split(" scored ");
        return Integer.parseInt(s[1]);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                SwingUtilities.invokeLater(() -> new MainMenu());
                this.dispose();
                //escape
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

