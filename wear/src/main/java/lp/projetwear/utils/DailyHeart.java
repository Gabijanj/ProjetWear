package lp.projetwear.utils;

public class DailyHeart {


    public static  int heart;



    public static void updateHearts(){
        heart = (int)(60+Math.random()*70);
    }

    public static int getHeart() {
        return heart;
    }
}