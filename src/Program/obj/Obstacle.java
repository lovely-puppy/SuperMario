package Program.obj;

import Program.util.BackGround;
import Program.util.StaticValue;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable {
    //用于表示坐标
    private int x;
    private int y;
    //用于记录障碍物类型
    private int type;
    //用于显示图像
    private BufferedImage show = null;
    //定义当前背景对象
    private BackGround bg = null;
    //定义一个线程
    Thread thread = new Thread(this);

    public Obstacle() {}
    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacle.get(type);
        if (type == 4) {
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            //判断是否达到旗子位置
            if (bg.isReach()) {
                if (y < 374) {
                    this.y += 5;
                } else {
                    bg.setBase(true);
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BackGround getBg() {
        return bg;
    }

    public BufferedImage getShow() {
        return show;
    }


}
