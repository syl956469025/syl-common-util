package syl.study.utils;

/**
 * @author 史彦磊
 * @create 2017-09-23 22:09
 */
public class HashUtil {


    public static void main(String[] args) {
        String key = "C1650000000095902772";
        int value = 8;
        int hashCode = key.hashCode();
        int shard = hashCode % value;
        System.out.println(Math.abs(shard)+1);
    }


}
