package pers.yangws.androiddevtools.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 文件名称:
 * 包路径:
 * 描述:
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
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    /**
     * 表名称
     * */
    String tablename();
    
}
