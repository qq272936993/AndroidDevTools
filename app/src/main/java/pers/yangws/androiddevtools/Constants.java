package pers.yangws.androiddevtools;


/**
 * 常量记录
 */
public class Constants {

    /**
     * 特殊字符
     */
    public static class SpecialChar {
        /**
         * 隐藏符号,可以用于代替分割数据的分隔符
         */
        public final static String CHAT_TEXT_LEFT_BRACKETS_TAG = new String(
                new byte[]{0x1e});
        /**
         * 隐藏符号
         */
        public final static String CHAT_TEXT_RIGHT_BRACKETS_TAG = new String(
                new byte[]{0x1f});
    }


    /**
     * SharePreference中使用的常量
     */
    public static class Sp {

        public final static String SP_NAME = "APP_SP_FILE";

    }


    /**
     * 容量单位常量
     */
    public static class Capacity {

        /**
         * 1M
         */
        public final static long ONE_M = 1024 * 1024;    //byte			1M

        /**
         * 1G
         */
        public final static long ONE_G = 1024 * 1024 * 1024;    //		1G

    }


    /**
     * 时间单位
     */
    public static class Time {

        /**
         * 一分钟对应的毫秒数
         */
        public final static long ONE_MINUTE = 60 * 1000;

        /**
         * 一小时对相应的毫秒数
         */
        public final static long ONE_HOUR = 60 * 60 * 1000;

    }


    /**
     * 数据库操作常量
     * */
    public static class DB{

        public final static String DEFAULT_DB ="default.db";

    }



}
