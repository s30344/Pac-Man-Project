import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements Runnable, KeyListener {
    private Font font = new Font("MV BOLI",Font.PLAIN, 20);
    private static final int tileSize = 24;
    private  int columns;
    private  int rows;
    private int witdhPanel;
    private int heightPanel;
    private int[][] mazeTileMap;
    private int[][] mazeCopy;

    private final int pacmanDefaultX = 1 * tileSize;
    private final int pacmanDefaultY = 1 * tileSize;
    private final int ghostDefaultX = 6 * tileSize;
    private final int ghostDefaultY = 6 * tileSize;
    private int pacmanX = pacmanDefaultX;
    private int pacmanY = pacmanDefaultY;
    private int ghostNumber;
    private int[] ghostX = new int[ghostNumber];
    private int[] ghostY = new int[ghostNumber];
    private int[] ghostDirection = new int[ghostNumber];
    private int pacmanSpeed = 6;
    private int ghostSpeed = 6;
    private int powerUpX;
    private int powerUpY;



    private int lives = 3;
    private int score;
    private int sec;
    private int min = 0;
    private boolean isAlive = false;
    private boolean notRunning;
    private final GameFrame gFrame;



    private Thread gameThread;




    private boolean upM;
    private boolean downM;
    private boolean leftM;
    private boolean rightM;
    private boolean[] upMG;
    private boolean[] downMG;
    private boolean[] leftMG;
    private boolean[] rightMG;

    private Image up1,up2,down1,down2,left1,left2,right1,right2;
    private Image ghostleft,ghostright,ghostup,ghostdown;
    private ImageIcon heart;
    private Image pacmanImage;
    private Image ghostImage = ghostdown;


    private boolean powerUpIsRunning;
    private boolean powerUpInMaze;

    JPanel hud;
    JLabel scoreLabel;
    JLabel heartLabel;
    JLabel HUDTimer;




    // WĄTKI
    private Runnable animation = new Runnable() {
        @Override
        public void run() {
            while(isAlive) {
                if (upM) {
                    try {
                        pacmanImage = up1;
                        Thread.sleep(20);
                        pacmanImage = up2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (downM) {
                    try {
                        pacmanImage = down1;
                        Thread.sleep(20);
                        pacmanImage = down2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (leftM) {
                    try {
                        pacmanImage = left1;
                        Thread.sleep(20);
                        pacmanImage = left2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (rightM) {
                    try {
                        pacmanImage = right1;
                        Thread.sleep(20);
                        pacmanImage = right2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < ghostNumber; i++) {
                    if (upMG[i]) {
                        try {
                            ghostImage = ghostup;
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (downMG[i]) {
                        try {
                            ghostImage = ghostdown;
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (leftMG[i]) {
                        try {
                            ghostImage = ghostleft;
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (rightMG[i]) {
                        try {
                            ghostImage = ghostright;
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                repaint();
            }

        }
    };
    Runnable power = new Runnable() {
        @Override
        public void run() {
            powerUpType();
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            powerUpIsRunning = false;
            ghostSpeed = 6;
        }
    };
    private void powerUpType(){
        int type = (int)(Math.random() * 5);
        switch (type){
            case 0:
                ghostSpeed = 0;
                break;
            case 1:
                score += 100;
                break;
            case 2:
                ghostSpeed = 3;
                break;
            case 3:
                ghostSpeed = 3;
                returnToDefaultPosition();
                break;
            case 4:
                lives++;
                break;
        }
    }
    Runnable time = new Runnable() {
        @Override
        public void run() {
            while(isAlive){
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                int l = (int)(Math.random()*4);
                switch (l){
                    case 0:powerUpInMaze = true;
                        int i = (int) (Math.random() * ghostNumber);
                        powerUpX = ghostX[i];
                        powerUpY = ghostY[i];
                        break;
                    case 1:break;
                    case 2:break;
                    case 3:break;
                }

            }
        }
    };
    Runnable timer = new Runnable() {
        @Override
        public void run() {
            while (isAlive){
                try {
                    sec++;
                    Thread.sleep(1000);
                    if (sec >= 60){
                        sec = 0;
                        min++;
                    }
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };


    // INICJALIZACJA

    public Game(GameFrame gFrame,int[][] maze,int row,int col,int ghostNr) {
        this.rows = row;
        this.columns = col;
        this.mazeTileMap = maze;
        this.mazeCopy = new int[row][col];
        this.witdhPanel = col * tileSize;
        this.heightPanel = row * tileSize;
        this.ghostNumber = ghostNr;
        this.gFrame = gFrame;
        this.ghostX = new int[ghostNumber];
        this.ghostY = new int[ghostNumber];
        this.ghostDirection = new int[ghostNumber];
        this.upMG = new boolean[ghostNumber];
        this.downMG = new boolean[ghostNumber];
        this.leftMG = new boolean[ghostNumber];
        this.rightMG = new boolean[ghostNumber];

        HUD();

        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(witdhPanel, heightPanel));
        this.addKeyListener(this);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(this, BorderLayout.CENTER);
        gamePanel.add(hud,BorderLayout.NORTH);
        gFrame.add(gamePanel);
    }
    private void HUD(){
        heart = new ImageIcon("serce.png");
        hud = new JPanel();
        hud.setLayout(new FlowLayout(FlowLayout.CENTER));
        hud.setBackground(new Color(0xFFEA07));
//        hud.setOpaque(false);
        scoreLabel = new JLabel();
        scoreLabel.setFont(font);
        scoreLabel.setText("Score: "+score);
        scoreLabel.setForeground(Color.magenta);
        heartLabel = new JLabel();
        heartLabel.setIcon(heart);
        heartLabel.setText(lives+"x");
        heartLabel.setFont(font);
        heartLabel.setForeground(Color.red);
        HUDTimer = new JLabel();
        HUDTimer.setFont(font);
        HUDTimer.setText(min+":"+sec);
        hud.add(HUDTimer);
        hud.add(scoreLabel);
        hud.add(heartLabel);
    }
    private void updateHUD(){
        HUDTimer.setText(min+":"+sec);
        heartLabel.setText(lives+"x");
        scoreLabel.setText("Score: "+score);
    }
    public synchronized void gameStart() {
        setImages();
        fillMaze();
        setGhostStartLocation();
        startGhost();
        lives = 3;
        isAlive = true;
        gameThread = new Thread(this);
        new Thread(timer).start();
        new Thread(animation).start();
        new Thread(time).start();
        gameThread.start();
    }
    private void setImages(){
        up1 = new ImageIcon("up1.png").getImage();
        up2 = new ImageIcon("up2.png").getImage();
        down1 = new ImageIcon("down1.png").getImage();
        down2 = new ImageIcon("down2.png").getImage();
        left1 = new ImageIcon("left1.png").getImage();
        left2 = new ImageIcon("left2.png").getImage();
        right1 = new ImageIcon("right1.png").getImage();
        right2 = new ImageIcon("right2.png").getImage();
        ghostup = new ImageIcon("ghostup.png").getImage();
        ghostdown = new ImageIcon("ghostdown.png").getImage();
        ghostleft = new ImageIcon("ghostleft.png").getImage();
        ghostright = new ImageIcon("ghostright.png").getImage();
        heart = new ImageIcon("serce.png");
        pacmanImage = right1;
    }
    private void fillMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeCopy[i][j] = mazeTileMap[i][j];
            }
        }
    }
    private void setGhostStartLocation() {
        for (int i = 0; i < ghostNumber; i++) {
            ghostY[i] = ghostDefaultY;
            ghostX[i] = ghostDefaultX;
        }
    }
    private void startGhost(){
        for (int i = 0; i < ghostNumber; i++) {
            ghostDirection[i]=(int)(Math.random() * 4);
            upMG[i] = downMG[i] = leftMG[i] = rightMG[i] = false;
            switch(ghostDirection[i]){
                case 0:
                    upMG[i] = true;
                    break;
                case 1:
                    downMG[i] = true;
                    break;
                case 2:
                    leftMG[i] = true;
                    break;
                case 3:
                    rightMG[i] = true;
                    break;
            }
        }
    }
    private void gameOver() {
        notRunning = true;
        if (!isAlive) {
            String inputName = JOptionPane.showInputDialog("Game Over! Input your name to save score:");
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.addScore(inputName, score);
            gFrame.dispose();
            gameThread = null;
        }
    }


    //TWORZENIE LABIRYNTU I POSTACI


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        createPowerUp(g);
        createMaze(g);
        createPacman(g);
        createGhost(g);

    }
    private void createMaze(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                switch (mazeCopy[i][j]) {
                    case 3:
                        g.setColor(Color.blue);
                        g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                        break;
                    case 1:
                        g.setColor(Color.white);
                        g.fillOval(j * tileSize + tileSize / 2 , i * tileSize + tileSize / 2, 4, 4);
                        break;
                }
            }
        }
    }
    private void createPacman(Graphics g){
        g.setColor(Color.PINK);
        g.drawImage(pacmanImage,pacmanX, pacmanY, tileSize, tileSize,this);
    }

    private void createGhost(Graphics g) {
        for (int i = 0; i < ghostNumber; i++) {
            g.drawImage(ghostImage,ghostX[i], ghostY[i], tileSize, tileSize,null);
        }
    }
    private void createPowerUp(Graphics g) {
        if (powerUpInMaze) {
            g.setColor(Color.RED);
            g.fillOval(powerUpX + tileSize / 2 - 2, powerUpY + tileSize / 2 - 2, 8, 8);

        }
        if (!powerUpInMaze){
        }
    }



    //RUCH I KOLIZJA



    @Override
    public void run() {
        while (isAlive) {
            movement();
            updateHUD();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void movement() {
        wallPacmanCollision();
        movePacman();
        moveGhost();
        ghostPacmanCollision();
        foodPacmanCollision();
        pacmanPowerUpCollision();
        mazeComplete();
    }
    private void movePacman(){
        if (upM) {
            pacmanY -= pacmanSpeed;
        }
        else if (downM) {
            pacmanY += pacmanSpeed;
        }
        else if (rightM) {
            pacmanX += pacmanSpeed;
        }
        else if (leftM) {
            pacmanX -= pacmanSpeed;
        }
    }
    private void moveGhost() {
        for (int i = 0; i < ghostNumber; i++) {
            if(ghostWallCollision(i)) {
                if (upMG[i]) {
                    ghostY[i] -= ghostSpeed;
                } else if (downMG[i]) {
                    ghostY[i] += ghostSpeed;

                } else if (rightMG[i]) {
                    ghostX[i] += ghostSpeed;

                } else if (leftMG[i]) {
                    ghostX[i] -= ghostSpeed;
                }
            }
        }
    }
    private boolean ghostWallCollision(int i){
        int wallX;
        int wallY;
        for (i = 0; i < ghostNumber; i++) {
            if (upMG[i]) {
                wallX = ghostX[i] / tileSize;
                wallY = (ghostY[i] - ghostSpeed) / tileSize;
                if (mazeCopy[wallY][wallX] == 3) {
                    upMG[i] = false;
                    switch((int)(Math.random()*3)){
                        case 0:
                            downMG[i] = true;
                            break;
                        case 1:
                            leftMG[i] = true;
                            break;
                        case 2:
                            rightMG[i] = true;
                            break;
                    }
                    return false;
                }
            }
            else if (downMG[i]) {
                wallX = ghostX[i] / tileSize;
                wallY = (ghostY[i] + ghostSpeed + tileSize - 1) / tileSize;
                if (mazeCopy[wallY][wallX] == 3) {
                    downMG[i] = false;
                    switch((int)(Math.random()*3)){
                        case 0:
                            upMG[i] = true;
                            break;
                        case 1:
                            leftMG[i] = true;
                            break;
                        case 2:
                            rightMG[i] = true;
                            break;
                    }
                    return false;
                }
            }
            else if (leftMG[i]) {
                wallX = (ghostX[i] - ghostSpeed) / tileSize;
                wallY = ghostY[i] / tileSize;
                if (mazeCopy[wallY][wallX] == 3) {
                    leftMG[i] = false;
                    switch((int)(Math.random()*3)){
                        case 0:
                            upMG[i] = true;
                            break;
                        case 1:
                            downMG[i] = true;
                            break;
                        case 2:
                            rightMG[i] = true;
                            break;
                    }
                    return false;
                }
            }
            else if (rightMG[i]) {
                wallX = (ghostX[i] + ghostSpeed + tileSize - 1) / tileSize;
                wallY = ghostY[i] / tileSize;
                if (mazeCopy[wallY][wallX] == 3) {
                    rightMG[i] = false;
                    switch((int)(Math.random()*3)){
                        case 0:
                            upMG[i] = true;
                            break;
                        case 1:
                            downMG[i] = true;
                            break;
                        case 2:
                            leftMG[i] = true;
                            break;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private void wallPacmanCollision() {
        int wallX;
        int wallY;
        if (upM) {
            wallX = pacmanX / tileSize;
            wallY = (pacmanY - pacmanSpeed) / tileSize;
            if (mazeCopy[wallY][wallX] == 3) {
                upM = false;
            }
        } else if (downM) {
            wallX = pacmanX / tileSize;
            wallY = (pacmanY + pacmanSpeed + tileSize - 1) / tileSize;
            if (mazeCopy[wallY][wallX] == 3) {
                downM = false;
            }
        } else if (leftM) {
            wallX = (pacmanX - pacmanSpeed) / tileSize;
            wallY = pacmanY / tileSize;
            if (mazeCopy[wallY][wallX] == 3) {
                leftM = false;
            }
        } else if (rightM) {
            wallX = (pacmanX + pacmanSpeed + tileSize - 1) / tileSize;
            wallY = pacmanY / tileSize;
            if (mazeCopy[wallY][wallX] == 3) {
                rightM = false;
            }
        }
    }

    private void ghostPacmanCollision() {
        for (int i = 0; i < ghostNumber; i++) {
            if (lives == 0) {
                isAlive = false;
                if (!notRunning){
                gameOver();
                }
            }
            if ((pacmanX > ghostX[i] - tileSize) && (pacmanX < ghostX[i] + tileSize)
                    && (pacmanY > ghostY[i] - tileSize) && (pacmanY < ghostY[i] + tileSize))
            {
                lives--;
                returnToDefaultPosition();
            }
        }
    }

    private void foodPacmanCollision() {
        if (mazeCopy[pacmanY / tileSize][pacmanX / tileSize] == 1) {
            score++;
            mazeCopy[pacmanY / tileSize][pacmanX / tileSize] = 0;
        }
    }

    private void returnToDefaultPosition() {
        pacmanX = pacmanDefaultX;
        pacmanY = pacmanDefaultY;
        setGhostStartLocation();
    }

    private void pacmanPowerUpCollision(){
        if ((pacmanX > powerUpX - tileSize) && (pacmanX < powerUpX + tileSize) &&
                (pacmanY > powerUpY - tileSize) && (pacmanY < powerUpY + tileSize))
        {
            if (!powerUpIsRunning){
                powerUpInMaze = false;
                powerUpIsRunning = true;
                new Thread(power).start();
            }
        }
    }

    private void mazeComplete() {
        int dotsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mazeCopy[j][i] == 1) {
                    dotsCount++;
                }
            }
        }
        if (dotsCount == 0) {
            fillMaze();
            score += 100;
            returnToDefaultPosition();
        }
    }




    // ODCZYT PRZYCISKOW



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                leftM = true;
                pacmanImage = left1;
                break;
            case 38:
                upM = true;
                pacmanImage = up1;
                break;
            case 39:
                rightM = true;
                pacmanImage = right1;
                break;
            case 40:
                downM = true;
                pacmanImage = down1;
                break;
            case 27:
                SwingUtilities.invokeLater(() -> new MainMenu());
                isAlive = false;
                gFrame.dispose();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                leftM = false;
                break;
            case 38:
                upM = false;
                break;
            case 39:
                rightM = false;
                break;
            case 40:
                downM = false;
                break;
        }
    }
}