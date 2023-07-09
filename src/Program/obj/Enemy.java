package Program.obj;

import Program.util.BackGround;
import Program.util.StaticValue;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
    private int x;
    private int y;
    private int type;
    private boolean face_to = true;
    private BufferedImage show = null;
    private BackGround bg;
    //食人花上下移动最大值
    private int max_up = 0;
    private int max_down = 0;
    //定义一个线程对象
    private Thread thread = new Thread(this);
    //表示当前图片状态
    private int image_type = 0;

    public Enemy() {
    }
    //蘑菇
    public Enemy(int x, int y, int type, boolean face_to, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        show = StaticValue.mogu.get(0);
        thread.start();
    }
    //食人花
    public Enemy(int x, int y, int type, boolean face_to, BackGround bg, int max_up, int max_down) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        this.max_up = max_up;
        this.max_down = max_down;
        show = StaticValue.flower.get(0);
        thread.start();
    }

    //敌人的死亡方法
    public void death() {
        if (this.getType() == 3) {
            type = 4;
        } else {
            this.bg.getEnemyList().remove(this);
        }

    }

    @Override
    public void run() {
        while(true) {
            //判断敌人类型
            if (type==1) {
                if (face_to == true) {
                    this.x -= 2;
                } else {
                    this.x += 2;
                }
                image_type = image_type == 0 ? 1 : 0;
                show = StaticValue.mogu.get(image_type);
            } else if (type == 3) {
                if (face_to) {
                    this.x -= 2;
                    show = StaticValue.toise_L.get(image_type);
                } else {
                    this.x += 2;
                    show = StaticValue.toise_R.get(image_type);
                }
                image_type = image_type == 1 ? 0 : 1;
            } else if (type == 4) {
                if (face_to) {
                    this.x -= 5;
                } else {
                    this.x += 5;
                }
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.shell.get(image_type);
            }
            //定义两个布尔值
            boolean canLeft = true;
            boolean canRight = true;

            for (int i = 0; i < bg.getObstacleList().size(); i ++ ) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                if (ob1.getX() == this.x - 36  && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canLeft = false;
                }
                if (ob1.getX() == this.x + 36  && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canRight = false;
                }
                if (face_to && !canLeft || this.x == 0) {
                    face_to = false;
                } else if ((!face_to) && (!canRight) || this.x == 764) {
                    face_to = true;
                }

            }
            //是否为食人花敌人
            if (type == 2) {
                if (face_to) {
                    this.y -= 2;
                } else {
                    this.y += 2;
                }

                image_type = image_type == 1 ? 0 : 1;
                //判断食人花是否到达极限位置
                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }
                if ((!face_to) && (this.y == max_down)) {
                    face_to = true;
                }
                show = StaticValue.flower.get(image_type);
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

    public boolean isFace_to() {
        return face_to;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
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

    public int getMax_up() {
        return max_up;
    }

    public void setMax_up(int max_up) {
        this.max_up = max_up;
    }

    public int getMax_down() {
        return max_down;
    }

    public void setMax_down(int max_down) {
        this.max_down = max_down;
    }

    public int getImage_type() {
        return image_type;
    }

    public void setImage_type(int image_type) {
        this.image_type = image_type;
    }
}
