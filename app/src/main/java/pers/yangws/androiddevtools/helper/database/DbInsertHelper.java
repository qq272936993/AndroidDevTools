package pers.yangws.androiddevtools.helper.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.List;
import pers.yangws.androiddevtools.model.annotation.Table;

/**
 * <pre>
 * 文件名称:
 * 包路径:
 * 描述:
 *      数据库插入帮助类
 * 内容摘要
 *    作者: 杨文松
 *    版本: 1.0
 *    时间:
 *    邮箱: 272936993@qq.com
 * 修改历史:
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ----------------------------------------------
 *
 * </pre>
 */
public class DbInsertHelper {
        
    
    /**
     * 批量插入标注类的对象
     * 
     * @param pojoCol 数据库映射对象集合
     * @return Boolean 是否执行成功.出现异常返回false,否则成功.
     * */
    public boolean butchAnnotationInsert(SQLiteDatabase sqLiteDatabase,List<?> pojoCol){
        if(pojoCol == null || pojoCol.isEmpty()) return true;
        else{
            Class<?> classes = pojoCol.get(0).getClass();
            //1.判断是否为标注类型
            if (classes.isAnnotationPresent(Table.class)){
                //如果是表类型,则获取类中的表对象
                String tablename = classes.getAnnotation(Table.class).tablename();   //表名

                String sql = "";
                Object[] args = null;
                //SQLiteStatement statement = new SQLiteStatement(sqLiteDatabase , sql, args );
                
            }
            
        }
        
        return true;
    }
    
    
}
