package pers.yangws.androiddevtools.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Collection;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by 1yyg-安卓 on 2015/12/29.
 * 子类只需要继承该类,并传入泛型对象,如果有拓展方法,则需要自己书写.
 */
public abstract class BaseGreenDao<T> {

    protected Context mContext;
    protected AbstractDao<T,Long> dao;

    public abstract AbstractDao<T,Long> getDao();

    public BaseGreenDao(Context context){
        this.mContext = context;
        dao = getDao();
    }

    /**
     * 获取总数
     * */
    public long count(){
        return dao.count();
    }

    /**
     * 通过实体删除一个对象
     * */
    public void delete(T entity){
        dao.delete(entity);
    }

    /**
     * 删除表中所有数据
     * */
    public void deleteAll(){
        dao.deleteAll();
    }

    /**
     * 删除记录通过key
     * */
    public void deleteByKey(Long key){
        dao.deleteByKey(key);
    }

    /**
     * 迭代删除
     * */
    public void deleteByKeyInTx(Iterable<Long> keys){
        dao.deleteByKeyInTx(keys);
    }

    /**
     * 迭代删除
     * */
    public void deleteByKeyInTx(Long... keys){
        dao.deleteByKeyInTx(keys);
    }

    /**
     * 执行事务的删除操作
     * */
    public void deleteInTx(Iterable<T> entities){
        dao.deleteInTx(entities);
    }

    /**
     * 通过key值,执行事务的迭代删除
     * */
    public void deleteByKeyInTx(T...entities){
        dao.deleteInTx(entities);
    }

    /**
     *
     * */
    public boolean detach(T entity){
        return dao.detach(entity);
    }

    /**
     * 获取所有字段信息
     * */
    public String[] getAllColumns(){
        return dao.getAllColumns();
    }

    /**
     * 获取数据库对象
     * */
    public SQLiteDatabase getDatabase(){
        return dao.getDatabase();
    }

    /**
     * 获取除主键以外的字段
     * */
    public String[] getNonPkColumns(){
        return dao.getNonPkColumns();
    }

    /**
     * 获取主键字段信息
     * */
    public String[] getPkColumns(){
        return dao.getPkColumns();
    }

    /**
     * 获取主键Property字段
     * */
    public Property getPkProperty(){
        return dao.getPkProperty();
    }

    /**
     * 获取所有的Property信息
     * */
    public Property[] getProperties(){
        return dao.getProperties();
    }

    /**
     * 获取Session
     * */
    public AbstractDaoSession getSession(){
        return dao.getSession();
    }

    /**
     * 获取表名
     * */
    public String getTablename(){
        return dao.getTablename();
    }


    /**
     * 插入一行对象记录
     * */
    public long insert(T entity){
        return dao.insert(entity);
    }

    /**
     * 开启事务插入多条对象记录
     * */
    public void insertInTx(Iterable<T> entities){
        dao.insertInTx(entities);
    }

    /**
     * 开启事务插入多条对象记录
     * */
    public void insertInTx(T... entities){
        dao.insertInTx(entities);
    }

    /**
     * 开启事务插入多条对象记录
     * */
    public void insertInTx(Iterable<T> entities, boolean setPrimaryKey){
        dao.insertInTx(entities, setPrimaryKey);
    }

    public long insertOrReplace(T entity){
        return dao.insertOrReplace(entity);
    }

    public void insertOrReplaceInTx(Iterable<T> entities){
        dao.insertOrReplaceInTx(entities);
    }

    public void insertOrReplaceInTx(T... entities){
        dao.insertOrReplaceInTx(entities);
    }

    public void insertOrReplaceInTx(Iterable<T> entities, boolean setPrimaryKey){
        dao.insertOrReplaceInTx(entities, setPrimaryKey);
    }

    public void insertWithoutSettingPk(T entity){
        dao.insertWithoutSettingPk(entity);
    }

    public T load(Long key){
        return dao.load(key);
    }

    public List<T> loadAll(){
        return dao.loadAll();
    }

    public T loadByRowId(long rowId){
        return dao.loadByRowId(rowId);
    }

    public QueryBuilder<T> queryBuilder(){
        return dao.queryBuilder();
    }

    public List<T> queryRaw(String where,String...selectionArg){
        return dao.queryRaw(where, selectionArg);
    }

    public Query<T> queryRawCreate(String where , Object...selectionArg){
        return dao.queryRawCreate(where, selectionArg);
    }

    public Query<T> queryRawCreateListArgs(String where ,Collection<Object> selectionArg){
        return dao.queryRawCreateListArgs(where, selectionArg);
    }
    public void refresh(T entity){
        dao.refresh(entity);
    }

    public void update(T entity){
        dao.update(entity);
    }

    public void updateInTx(Iterable<T> entities){
        dao.updateInTx(entities);
    }

    public void updateInTx(T...entities){
        dao.updateInTx(entities);
    }

}
