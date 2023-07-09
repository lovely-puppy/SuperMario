package Program;

import Program.obj.Daoju;
import Program.obj.Enemy;
import Program.obj.Mario;
import Program.obj.Obstacle;
import Program.util.BackGround;
import Program.util.Music;
import Program.util.StaticValue;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, Runnable {
    //存储所有的背景
    private List<BackGround> allBg = new ArrayList<>();
    //存储当前背景
    private BackGround nowBg = new BackGround();
    //用于双缓存
    private Image offScreenImage = null;
    //定义马里奥对象
    private Mario mario = new Mario();
    //实现马里奥的移动
    private Thread thread = new Thread(this);
    public MyFrame() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setTitle("超级马里奥");

        //初始化图片
        StaticValue.init();
        //初始化马里奥
        mario = new Mario(10, 355, this.getHeight(), this.getWidth());
        //创建全部场景
        for (int i = 1; i <= 3; i ++ ) {
            allBg.add(new BackGround(i, i==3?true:false));
        }
        //将第一个场景设置为当前场景
        nowBg = allBg.get(0);
        mario.setBackGround(nowBg);
        //绘制图像
        repaint();
        thread.start();

        try {
            new Music();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);
        //绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);
        //绘制道具
        for (Daoju daoju : nowBg.getDaojuList()) {
            graphics.drawImage(daoju.getShow(), daoju.getX(), daoju.getY(), this);
        }
        //绘制敌人
        for (Enemy e : nowBg.getEnemyList()) {
            graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
        }
        //绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(),ob.getX(), ob.getY(), this);
        }

        //绘制旗杆城堡
        graphics.drawImage(nowBg.getGan(), 500, 220, this);
        graphics.drawImage(nowBg.getTower(), 620, 270, this);
        //绘制马里奥
        if (!mario.isEatMg()) {
            graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), 25, 25, this);
        } else {
            graphics.drawImage(mario.getShow(), mario.getX(), mario.getY()-15, 28, 40, this);
        }

        //添加积分
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体", Font.BOLD, 25));
        graphics.drawString("SCORE: " + mario.getScore(), 300, 100);
        graphics.setColor(c);
        //将背景添加到窗口中
        g.drawImage(offScreenImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        //实现移动
        if (e.getKeyCode() == 39) {
            mario.rightMove();
        }
        if (e.getKeyCode() == 37) {
            mario.leftMove();
        }
        if (e.getKeyCode() == 38) {
            mario.jump();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //停止
        if (e.getKeyCode() == 37) {
            mario.leftStop();
        }
        if (e.getKeyCode() == 39) {
            mario.rightStop();
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);
                //是否到达下一关
                if (mario.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(395);
                }
                //判断马里奥是否死亡
                if (mario.isDeath()) {
                    JOptionPane.showMessageDialog(this, "MARIO DIED!");
                    System.exit(0);
                }

                //判断游戏是否结束
                if (mario.isOK()) {
                    JOptionPane.showMessageDialog(this, "CONGRATULATIONS! YOU PASS THE GAME!");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
