package pers.yangws.androiddevtools.helper.data;

/**
 * Created by 1yyg-安卓 on 2015/12/25.
 */
public class ArrayHelper {

    /***
     * 合并数组
     * @param originalValues
     * @param newValues
     * */
    public static String[] appendArgs(String[] originalValues, String[] newValues) {
        if (originalValues == null || originalValues.length == 0) {
            return newValues;
        }
        String[] result = new String[originalValues.length + newValues.length ];
        System.arraycopy(originalValues, 0, result, 0, originalValues.length);
        System.arraycopy(newValues, 0, result, originalValues.length, newValues.length);
        return result;
    }


}
