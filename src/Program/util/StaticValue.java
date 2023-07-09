package Program.util;

import Program.obj.Enemy;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    //四种状态
    public static BufferedImage jump_L = null;
    public static BufferedImage jump_R = null;
    public static BufferedImage stand_L = null;
    public static BufferedImage stand_R = null;
    //城堡
    public static BufferedImage tower = null;
    //旗杆
    public static BufferedImage gan = null;
    //蘑菇道具
    public static List<BufferedImage> mg = new ArrayList<>();
    //小花道具
    public static BufferedImage xh = null;
    //金币道具
    public static List<BufferedImage> jinbi = new ArrayList<>();
    //跑
    public static List<BufferedImage> run_L = new ArrayList<>();
    public static List<BufferedImage> run_R = new ArrayList<>();
    //障碍物
    public static List<BufferedImage> obstacle = new ArrayList<>();
    //蘑菇敌人
    public static List<BufferedImage> mogu = new ArrayList<>();
    //食人花
    public static List<BufferedImage> flower = new ArrayList<>();
    //路径前缀
    public static String path = System.getProperty("user.dir") + "\\src\\images\\";
    //添加乌龟敌人
    public static List<BufferedImage> toise_L = new ArrayList<>();
    public static List<BufferedImage> toise_R = new ArrayList<>();
    //龟壳
    public static List<BufferedImage> shell = new ArrayList<>();


    public static void init() {
        try {
            //加载背景图片
            bg = ImageIO.read(new File(path+"bg.png"));
            bg2 = ImageIO.read(new File(path + "bg2.png"));
            //马里奥四个动作
            stand_L = ImageIO.read(new File(path + "s_mario_stand_L.png"));
            stand_R = ImageIO.read(new File(path + "s_mario_stand_R.png"));
            jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
            jump_R = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
            //城堡和旗杆
            tower = ImageIO.read(new File(path + "tower.png"));
            gan = ImageIO.read(new File(path + "gan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //跑
        for (int i = 1; i <= 2; i ++ ) {
            try {
                run_L.add(ImageIO.read(new File(path + "s_mario_run" + i + "_L.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= 2; i ++ ) {
            try {
                run_R.add(ImageIO.read(new File(path + "s_mario_run" + i + "_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //障碍物
        try {
            obstacle.add(ImageIO.read(new File(path + "brick.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
            obstacle.add(ImageIO.read(new File(path + "brick2.png")));
            obstacle.add(ImageIO.read(new File(path + "flag.png")));
            for (int i = 1; i <= 3; i ++ ) {
                mogu.add(ImageIO.read(new File(path + "fungus" + i + ".png")));
            }
            for (int i = 1; i <= 2; i ++ ) {
                flower.add(ImageIO.read(new File(path + "flower1." + i + ".png")));
            }
            for (int i = 1; i <= 4; i ++ ) {
                obstacle.add(ImageIO.read(new File(path + "pipe" + i + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //加载道具
        for (int i = 1; i <= 3; i ++ ) {
            try {
                mg.add(ImageIO.read(new File(path + "mushroom" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            xh = ImageIO.read(new File(path + "xiaohua.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= 3; i ++ ) {
            try {
                jinbi.add(ImageIO.read(new File(path + "jinbi" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //加载乌龟敌人
        for (int i = 1; i <= 2; i ++ ) {
            try {
                toise_L.add(ImageIO.read(new File(path + "Ltortoise"+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 1; i <= 2; i ++ ) {
            try {
                toise_R.add(ImageIO.read(new File(path + "Rtortoise"+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //龟壳
        for (int i = 1; i <= 4; i ++ ) {
            try {
                shell.add(ImageIO.read(new File(path + "shell" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //加载道具
        for (int i = 1; i <= 4; i ++ ) {
            try {
                obstacle.add(ImageIO.read(new File(path + "box1."+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
