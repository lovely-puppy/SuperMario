package Program.obj;

import Program.util.BackGround;
import Program.util.StaticValue;

import java.awt.image.BufferedImage;

public class Daoju implements Runnable {
    private int x;
    private int y;
    private int type;
    private BufferedImage show;
    private BackGround bg;
    private Thread thread = new Thread(this);
    private int image_type;

    public Daoju() {
    }

    public Daoju(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.mg.get(0);
        thread.start();
    }

    public void eat() {
        this.bg.getDaojuList().remove(this);
    }

    @Override
    public void run() {
        while (true) {
            //判断道具类型
            //蘑菇
            if (type == 0) {
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.mg.get(image_type);
            }
            //小花
            if (type == 1) {
                show = StaticValue.xh;
            }
            //金币
            if (type == 2) {
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.jinbi.get(image_type);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public BackGround getBg() {
        return bg;
    }

    public void setBg(BackGround bg) {
        this.bg = bg;
    }

    public int getImage_type() {
        return image_type;
    }

    public void setImage_type(int image_type) {
        this.image_type = image_type;
    }
}
