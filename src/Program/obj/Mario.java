package Program.obj;

import Program.util.BackGround;
import Program.util.StaticValue;

import java.awt.image.BufferedImage;

public class Mario implements Runnable {
    private int x;
    private int y;
    private int height = 25;
    private int width = 25;
    //状态
    private String status;
    //状态对应的图像
    private BufferedImage show = null;
    //定义一个BackGr对象，用来获取障碍物信息
    private BackGround backGround = new BackGround();
    //实现马里奥动作
    private Thread thread = null;
    //移动速度
    private int xSpeed;
    private int ySpeed;
    //索引
    private int index;
    //朝向
    private boolean face_to = true;
    //上升时间
    private int upTime = 0;
    //判断马里奥是否走到城堡口
    private boolean isOK;
    //记录马里奥是否死亡
    private boolean isDeath = false;
    //积分
    private int score = 0;
    //是否吃掉蘑菇道具
    private boolean eatMg = false;
    //是否吃掉小花道具
    private boolean eatXh = false;


    public Mario() {}
    public Mario(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        show = StaticValue.stand_R;
        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    //移动
    public void leftMove() {
        //改变速度
        xSpeed = -5;
        //马里奥是否碰到旗子
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }
    }
    public void rightMove() {
        //改变速度
        xSpeed = 5;
        //马里奥是否碰到旗子
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }
    }
    //停止
    public void leftStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }
    }
    public void rightStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }
    }
    public void jump() {
        if (status.indexOf("jump") == -1) {
            if (status.indexOf("left") != -1) {
                status = "jump--left";
            } else {
                status = "jump--right";
            }
            if (!this.eatXh){
                ySpeed = -10;
                upTime = 7;
            } else {
                ySpeed = -20;
                upTime = 5;
            }

        }
        if (backGround.isReach()) {
            ySpeed = 0;
        }
    }
    public void fall() {
        if (status.indexOf("left") != -1) {
            status = "jump--left";
        } else {
            status = "jump--right";
        }
        ySpeed = 10;
    }
    public void death() {
        isDeath = true;
    }

    @Override
    public void run() {
        while (true) {
            //是否处于障碍物上
            boolean onObstacle = false;
            //可不可以向左向右走
            boolean canRight = true;
            boolean canLeft = true;
            //马里奥是否到达旗子位置
            if (backGround.isFlag() && this.x >= 500) {
                this.backGround.setReach(true);

                //旗子是否下落
                if (this.backGround.isBase()) {
                    status = "move--right";
                    if (x < 690) {
                        x += 5;
                    } else {
                        isOK = true;
                    }
                } else {
                    if (y < 395) {
                        xSpeed = 0;
                        this.y += 5;
                        status = "jump--right";
                    }

                    if (y > 395) {
                        this.y = 395;
                        status = "stop--right";
                    }
                }
            }
            //判断是否碰到敌人
            for (int i = 0; i < backGround.getEnemyList().size(); i ++ ) {
                Enemy e = backGround.getEnemyList().get(i);
                if (e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)) {
                    if (e.getType() == 1) {
                        e.death();
                        upTime = 3;
                        ySpeed = -10;
                        score += 2;
                    } else if (e.getType() == 2) {
                        death();
                    } else if (e.getType() == 3) {
                        score += 3;
                        upTime = 3;
                        ySpeed = -10;
                        e.death();
                        e.setY(e.getY() + 10);
                    } else if (e.getType() == 4) {
                        score += 2;
                        upTime = 3;
                        ySpeed = -10;
                        e.death();
                    }
                }
                if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x) && (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) {
                    //马里奥死亡
                    death();
                }
            }

            //遍历所有障碍物
            for (int i = 0; i < backGround.getObstacleList().size(); i ++ ) {
                Obstacle ob = backGround.getObstacleList().get(i);
                //是否在障碍物上
                if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                    onObstacle = true;
                }
                //判断是否顶到砖块
                if ((ob.getY() >= this.y - 30  && ob.getY() <= this.y-20) && (ob.getX() > this.x - 30 && ob.getX()<this.x+25)) {
                    if (ob.getType() == 0) {
                        backGround.getObstacleList().remove(ob);
                        score += 1;
                    }
                    if (ob.getType() == 9) {
                        if (this.eatMg) {
                            backGround.getDaojuList().add(new Daoju(ob.getX(), this.y - 55, 1, backGround));
                        } else {
                            backGround.getDaojuList().add(new Daoju(ob.getX(), this.y - 55, 0, backGround));
                        }
                    }
                    upTime = 0;
                }
                //是否可以向左向右走
                if (ob.getX()==this.x+25 && (ob.getY()>this.y-30 && ob.getY() < this.y + 25)) {
                    canRight = false;
                }
                if (ob.getX()==this.x-30 && (ob.getY() > this.y-30 && ob.getY() < this.y + 25)) {
                    canLeft = false;
                }
            }
            //是否吃掉道具
            for (int i = 0; i < backGround.getDaojuList().size(); i ++ ) {
                Daoju d = backGround.getDaojuList().get(i);

                if ((d.getX() + 35 > this.x && d.getX()-25 < this.x) && (d.getY()+ 35 > this.y && d.getY() - 20 < this.y)) {
                    if (d.getType() == 0) {
                        d.eat();
                        this.setEatMg(true);
                    }
                    if (d.getType() == 1) {
                        d.eat();
                        this.setEatXh(true);
                    }
                    if (d.getType() == 3) {
                        d.eat();
                        score += 5;
                    }
                }
            }
            //跳跃操作
            if (onObstacle && upTime == 0) {
                if (status.indexOf("left") != -1) {
                    if (xSpeed != 0) {
                        status = "move--left";
                    } else {
                        status = "stop--left";
                    }
                } else {
                    if (xSpeed != 0) {
                        status = "move--right";
                    } else {
                        status = "stop--right";
                    }
                }
            }else {
                if (upTime != 0) {
                    upTime -- ;
                } else {
                    fall();
                }
                y += ySpeed;
            }

            if ((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
                x += xSpeed;
//                是否到达最左边
                if (x < 0) {
                    x = 0;
                }
            }
            //是否在移动
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            //向左移动
            if ("move--left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }
            //右移动
            if ("move--right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }
            //停止
            if ("stop--left".equals(status)) {
                show = StaticValue.stand_L;
            }
            if ("stop--right".equals(status)) {
                show = StaticValue.stand_R;
            }
            //是否跳跃
            if ("jump--left".equals(status)) {
                show = StaticValue.jump_L;
            }
            if ("jump--right".equals(status)) {
                show = StaticValue.jump_R;
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

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }

    public BufferedImage getShow() {
        return show;
    }

    public BackGround getBackGround() {
        return backGround;
    }

    public Thread getThread() {
        return thread;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getIndex() {
        return index;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public int getUpTime() {
        return upTime;
    }

    public void setUpTime(int upTime) {
        this.upTime = upTime;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
    }

    public boolean isFace_to() {
        return face_to;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public void setDeath(boolean death) {
        isDeath = death;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isEatMg() {
        return eatMg;
    }

    public void setEatMg(boolean eatMg) {
        this.eatMg = eatMg;
    }

    public boolean isEatXh() {
        return eatXh;
    }

    public void setEatXh(boolean eatXh) {
        this.eatXh = eatXh;
    }
}
