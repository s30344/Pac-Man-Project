import javax.swing.*;


public class GameFrame extends JFrame{
    Game game;


    public GameFrame(int[][] maze,int row,int col,int ghostNr){
        game = new Game(this,maze,row,col,ghostNr);
        this.setIconImage(new ImageIcon("ghost.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pac-man");
        this.setResizable(false);
        this.pack();
        game.gameStart();
    }
}
