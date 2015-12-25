package pers.yangws.androiddevtools.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {

    /***
     * 是否主键
     * */
    public boolean isPrimary() default false;

    /**
     * 映射的名称
     * */
    public String mappname();


    /**
     * 长度
     * */
    public int length();

}
