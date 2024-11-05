package src;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener {
    Timer gameLoop;
    int score=0;
    boolean gameOver=false;
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image img;
        boolean isAlive = true; // for aliens
        boolean used = false;   // for bullets

        Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    int tilesSize = 32;
    int rows = 16;
    int cols = 16;
    int bordWidth = tilesSize * cols;
    int bordHeight = tilesSize * rows;

    Image shipImg;
    Image alienImg;
    Image alienCyanImg;
    Image alienMagentaImg;
    Image alienYellowImg;
    ArrayList<Image> alienImgArray = new ArrayList<>();

    int shipWidth = tilesSize * 2;
    int shipHeight = tilesSize;
    int shipX = tilesSize * cols / 2 - tilesSize;
    int shipY = bordHeight - tilesSize * 2;
    int shipVelocityx = tilesSize;
    Block ship;
    ArrayList<Block> alienArray;
    int alienWidth=tilesSize*2;
    int alienHeight = tilesSize;
    int alienX=tilesSize;
    int alienY=tilesSize;
    int alienRows=2;
    int alienCols=3;
    int alienCount=0;
    int alienVelocityx=1;
    ArrayList<Block> bulletArray;
    int bulletWidth=tilesSize/8;
    int bulletHeight=tilesSize/2;
    int bulletVelocityY=-10;
    SpaceInvaders() {
        setPreferredSize(new Dimension(bordWidth, bordHeight));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);

        // Load images
        shipImg = new ImageIcon(getClass().getResource("ship.png")).getImage();
        alienImg = new ImageIcon(getClass().getResource("alien.png")).getImage();
        alienCyanImg = new ImageIcon(getClass().getResource("alien-cyan.png")).getImage();
        alienMagentaImg = new ImageIcon(getClass().getResource("alien-magenta.png")).getImage();
        alienYellowImg = new ImageIcon(getClass().getResource("alien-yellow.png")).getImage();

        alienImgArray.add(alienImg);
        alienImgArray.add(alienCyanImg);
        alienImgArray.add(alienMagentaImg);
        alienImgArray.add(alienYellowImg);

        // Initialize the ship
        ship = new Block(shipX, shipY, shipWidth, shipHeight, shipImg);
        alienArray=new ArrayList<Block>();
        bulletArray=new ArrayList<Block>();

        // Initialize game timer
        gameLoop = new Timer(16, this);
      createAliens();
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw the ship
        g.drawImage(ship.img, ship.x, ship.y, ship.width, ship.height, null);
        for(int i=0;i<alienArray.size();i++){
            Block alien=alienArray.get(i);
            if(alien.isAlive){
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }
        g.setColor(Color.white);
        for(int i=0;i<bulletArray.size();i++){
            Block bullet=bulletArray.get(i);
            if(!bullet.used){
                g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,32));
        if(gameOver) {
            g.drawString("Game Over: "+String.valueOf(score),10,35);
        }
        else{
            g.drawString(String.valueOf(score),10,35);
        }

    }
    public void move(){
        for(int i=0;i<alienArray.size();i++){
        Block alien=alienArray.get(i);
        if(alien.isAlive){
            alien.x+=alienVelocityx;
        }
        if(alien.x+alien.width>=bordWidth||alien.x<=0){
            alienVelocityx*=-1;
         alien.x+=alienVelocityx*2;

         for(int j=0;j<alienArray.size();j++){
            alienArray.get(j).y+=alienHeight;
         }
        }
        if(alien.y>=ship.y){
            gameOver=true;
        }
        }
        for(int i=0;i<bulletArray.size();i++){
            Block bullet=bulletArray.get(i);
            bullet.y+=bulletVelocityY;
        
        for(int j=0;j<alienArray.size();j++){
            Block alien=alienArray.get(j);
            if(alien.isAlive&&bullet.x+bullet.width>=alien.x&&bullet.x<=alien.x+alien.width&&bullet.y+bullet.height>=alien.y&&bullet.y<=alien.y+alien.height){
                bullet.used=true;
                alien.isAlive=false;
                alienCount--;
                score+=100;
            }
        }
    }
    while(bulletArray.size()>0 && (bulletArray.get(0).used||bulletArray.get(0).y<0)){
        bulletArray.remove(0);

    }
    if(alienCount==0){
        score+=alienCols*alienRows*100;
        alienCols=Math.min(alienCols+1,cols/2-2);
        alienRows=Math.min(alienRows+1,rows-6);
        alienArray.clear();
        bulletArray.clear();
        alienVelocityx=1;
        createAliens();
    }
    }
    public void createAliens(){
        Random random=new Random();
        for(int r=0;r<alienRows;r++){
            for(int c=0;c<alienCols;c++){
                int randomImgIndex=random.nextInt(alienImgArray.size());
                Block alien=new Block(
                    alienX+c*alienWidth,
                    alienY+r*alienHeight,
                    alienWidth,
                    alienHeight,
                    alienImgArray.get(randomImgIndex)
                );
                alienArray.add(alien);
            }
        }
        alienCount=alienArray.size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(gameOver){
            ship.x=shipX;
            alienArray.clear();
            bulletArray.clear();
            score=0;
            alienVelocityx=1;
            alienCols=3;
            alienRows=2;
            gameOver=false;
            createAliens();
            gameLoop.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && ship.x-shipVelocityx>=0) {
            ship.x -= shipVelocityx;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && ship.x+ ship.width+shipVelocityx<=bordWidth) {
            ship.x += shipVelocityx;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            Block bullet=new Block(
                ship.x+shipWidth*15/32,
                ship.y,
                bulletHeight,
                bulletWidth,
                null
            );
            bulletArray.add(bullet);
        }
    }
}
