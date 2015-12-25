package pers.yangws.androiddevtools.helper.data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1yyg-安卓 on 2015/12/25.
 */
public class ReflectHelper {



    /**
     * 获取泛型中的类型
     * 	 例如: List<String>   获取出的类型为 [String.class]
     * 说明:
     * 		需要获取泛型的,必须通过继承等方式重新构建一个新类,传递泛型的类型
     *
     * 可查考
     * @throws ClassNotFoundException
     * */
    @SuppressWarnings("rawtypes")
    public static List<Class> getGenericsClass(Class thisClass) throws ClassNotFoundException{
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = thisClass.getGenericSuperclass();
        List<Class> list= new ArrayList<Class>();
        if (!(genType instanceof ParameterizedType)) {
            list.add(Object.class);
            return list;
        }

        //返回表示此类型实际类型参数的Type对象的数组
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        for(Type type : params){
            list.add((Class)type);
        }
        return list;
    }

}
