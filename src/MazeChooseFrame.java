import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MazeChooseFrame extends JFrame implements KeyListener {
    Border eb = BorderFactory.createEmptyBorder();
    private Font font = new Font("MV BOLI",Font.PLAIN, 50);
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public int[][] maze1 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 0, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 1, 1, 3, 3, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 3, 3, 1, 3, 1, 3, 0, 3, 1, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 1, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 3, 3, 1, 3, 1, 1, 1, 1, 1, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 0, 3, 0, 3, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 0, 0, 0, 3, 3, 3, 1, 3, 1, 3, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
    };
    public int[][] maze2 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 0, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 1, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 1, 3, 3, 1, 3, 1, 3},
            {3, 1, 1, 1, 3, 3, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
    };
    public int[][] maze3 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3},
            {3, 1, 3, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 1, 1, 3, 1, 3, 1, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 1, 3, 1, 3, 1, 3, 3, 0, 3, 1, 3},
            {3, 1, 3, 1, 1, 1, 1, 1, 1, 3, 0, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 1, 3, 3, 1, 3, 1, 3, 3},
            {3, 1, 3, 3, 1, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3},
            {3, 1, 1, 1, 1, 3, 3, 1, 3, 3, 0, 3, 1, 1, 3},
            {3, 3, 3, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3},
            {3, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
    };
    public int[][] maze4 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 0, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 3, 1, 3, 3, 3, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3},
            {3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 1, 3, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3},
            {3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3},
            {3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
    };
    public int[][] maze5 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 0, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 3, 3, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 1, 3, 0, 0, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 0, 0, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 3, 3, 3, 1, 3, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3},
            {3, 1, 1, 1, 3, 1, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3},
            {3, 3, 3, 1, 3, 1, 3, 1, 3, 1, 3, 3, 0, 3, 0, 3, 1, 3, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 3, 3, 3, 3, 3, 1, 3, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 3, 1, 1, 1, 1, 1, 3, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3},
            {3, 1, 3, 1, 3, 1, 3, 3, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 3, 1, 1, 1, 3, 3, 3, 1, 3, 3, 1, 3, 0, 0, 0, 3, 1, 3},
            {3, 1, 3, 1, 3, 3, 3, 3, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
    };

    private int col1;
    private int row1;
    private int col2;
    private int row2;
    private int col3;
    private int row3;
    private int col4;
    private int row4;
    private int col5;
    private int row5;
    private int ghostNr;


    public MazeChooseFrame(){
        buttonHandler();
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.setIconImage(new ImageIcon("ghost.png").getImage());
        this.setLayout(new GridLayout(0,1));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pac-man");
        this.setResizable(false);
        this.setFocusable(true);
        this.pack();
        this.addKeyListener(this);
    }
    public void buttonHandler() {
        button1 = new JButton("Map #1");
        button2 = new JButton("Map #2");
        button3 = new JButton("Map #3");
        button4 = new JButton("Map #4");
        button5 = new JButton("Map #5");

        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        button5.setFont(font);

        button1.setBackground(new Color(0xFFEA07));
        button2.setBackground(new Color(0xFFEA07));
        button3.setBackground(new Color(0xFFEA07));
        button4.setBackground(new Color(0xFFEA07));
        button5.setBackground(new Color(0xFFEA07));

        button1.setBorder(eb);
        button2.setBorder(eb);
        button3.setBorder(eb);
        button4.setBorder(eb);
        button5.setBorder(eb);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col1 = 20;
                row1 = 20;
                ghostNr = 4;
                SwingUtilities.invokeLater(() -> new GameFrame(maze1, row1, col1,ghostNr));
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col2 = 10;
                row2 = 10;
                ghostNr = 2;
                SwingUtilities.invokeLater(() -> new GameFrame(maze2, row2, col2,ghostNr));
                dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col3 = 15;
                row3 = 15;
                ghostNr = 3;
                SwingUtilities.invokeLater(() -> new GameFrame(maze3, row3, col3,ghostNr));
                dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col4 = 25;
                row4 = 25;
                ghostNr = 5;
                SwingUtilities.invokeLater(() -> new GameFrame(maze4, row4, col4,ghostNr));
                dispose();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col5 = 20;
                row5 = 20;
                ghostNr = 4;
                SwingUtilities.invokeLater(() -> new GameFrame(maze5, row5, col5,ghostNr));
                dispose();
            }
        });
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
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


