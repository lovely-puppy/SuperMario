package Program.util;

import Program.obj.Daoju;
import Program.obj.Enemy;
import Program.obj.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //当前场景要显示的图片
    private BufferedImage bgImage = null;
    //记录当前是第几个场景
    private int sort;
    //判断是否到达最后一个场景
    private boolean flag;
    //用于存放所有障碍物
    private List<Obstacle> obstacleList = new ArrayList<>();
    //用于存放所有敌人
    private List<Enemy> enemyList = new ArrayList<>();
    //旗杆
    private BufferedImage gan = null;
    //城堡
    private BufferedImage tower = null;
    //马里奥是否到达旗杆位置
    private boolean isReach = false;
    //旗子是否落地
    private boolean isBase = false;
    //道具对象
    private List<Daoju> daojuList = new ArrayList<>();

    public BackGround() {}
    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }

        //是否是第一关
        if (sort == 1) {
            for (int i = 0; i <= 27; i ++ ) {
                obstacleList.add(new Obstacle(i*30, 420, 2, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i ++ ) {
                    obstacleList.add(new Obstacle(i*30, 570-j, 1, this));
                }
            }
            //砖块
            for (int i = 120; i <= 150; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 3, this));
            }
            //道具方块
            obstacleList.add(new Obstacle(180, 300, 9, this));
            obstacleList.add(new Obstacle(210, 300, 3, this));
            for (int i = 300; i <= 570; i += 30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 3, this));
                } else {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }
            for (int i = 420; i <= 450; i += 30) {
                obstacleList.add(new Obstacle(i, 240, 3, this));
            }
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 7, this));
                    obstacleList.add(new Obstacle(645, i, 8, this));
                }
            }
            //绘制第一关蘑菇敌人
            enemyList.add(new Enemy(580, 385, 1, true, this));
            //食人花
            enemyList.add(new Enemy(635, 420, 2, true, this, 328, 428));
            //乌龟敌人
            enemyList.add(new Enemy(588, 385, 3, true, this));
        }
        //如果是第二关
        else if (sort == 2) {
            //地面
            for (int i = 0; i <= 27; i ++ ) {
                obstacleList.add(new Obstacle(i*30, 420, 2, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i ++ ) {
                    obstacleList.add(new Obstacle(i*30, 570-j, 1, this));
                }
            }
            //道具方块
            obstacleList.add(new Obstacle(210, 300, 9, this));
            //第一根水管
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60, i, 5, this));
                    obstacleList.add(new Obstacle(85, i, 6, this));
                } else {
                    obstacleList.add(new Obstacle(60, i, 7, this));
                    obstacleList.add(new Obstacle(85, i, 8, this));
                }
            }
            //第二根水管
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 7, this));
                    obstacleList.add(new Obstacle(645, i, 8, this));
                }
            }
            //砖块
            obstacleList.add(new Obstacle(300, 330, 0, this));
            for (int i = 270; i <= 330; i += 30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i, 360, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 360, 3, this));
                }
            }
            for (int i = 240; i <= 360; i += 30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i, 390, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 390, 3, this));
                }
            }
            obstacleList.add(new Obstacle(240, 300, 0, this));
            for (int i = 360; i <= 540; i += 60) {
                obstacleList.add(new Obstacle(i, 270, 3, this));
            }
            //第二关敌人
            enemyList.add(new Enemy(75, 420, 2, true, this, 328, 428));
            enemyList.add(new Enemy(635, 420, 2, true, this, 328, 428));
            enemyList.add(new Enemy(200, 385, 1, true, this));
            enemyList.add(new Enemy(500, 385, 1, true, this));
            enemyList.add(new Enemy(220, 385, 3, true, this));
        }
        //第三关
        else {
            //地面
            for (int i = 0; i <= 27; i ++ ) {
                obstacleList.add(new Obstacle(i*30, 420, 2, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i ++ ) {
                    obstacleList.add(new Obstacle(i*30, 570-j, 1, this));
                }
            }
            //砖块
            int tmp = 290;
            for (int i = 390; i >= 270; i -= 30) {
                for (int j = tmp; j <= 410; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 3, this));
                }
                tmp += 30;
            }
            tmp = 60;
            for (int i = 390; i >= 360; i -= 30) {
                for (int j = tmp; j <= 90; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 3, this));
                }
                tmp += 30;
            }
            //旗杆
            gan = StaticValue.gan;
            obstacleList.add(new Obstacle(515, 240, 4, this));
            //城堡
            tower = StaticValue.tower;
        }
        enemyList.add(new Enemy(150, 385, 1, true, this));
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public List<Daoju> getDaojuList() {
        return daojuList;
    }

    public void setDaojuList(List<Daoju> daojuList) {
        this.daojuList = daojuList;
    }
}
