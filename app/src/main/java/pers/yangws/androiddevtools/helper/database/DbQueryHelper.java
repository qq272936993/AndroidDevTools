package pers.yangws.androiddevtools.helper.database;

import android.database.DatabaseUtils;
import android.text.TextUtils;

/**
 * Created by 1yyg-安卓 on 2015/12/24.
 * 数据库查询帮助类
 * */
public class DbQueryHelper {

    //隐藏构造函数
    private DbQueryHelper(){}

    public static String getEqualityClause(String field, String value) {
        StringBuilder clause = new StringBuilder();
        clause.append(field);
        clause.append(" = ");
        DatabaseUtils.appendEscapedSQLString(clause, value);
        return clause.toString();
    }


    public static String concatenateClausesWithAnd(String... clauses) {
        return concatenateClausesWithOperation("AND", clauses);
    }

    public static String concatenateClausesWithOr(String... clauses){
        return concatenateClausesWithOperation("OR", clauses);
    }

    public static String concatenateClausesWithOperation(String operation, String... clauses){
        if(clauses.length == 1){
            return clauses[0];
        }

        StringBuilder builder = new StringBuilder();

        for(String clause : clauses){
            if(!TextUtils.isEmpty(clause)){
                if(builder.length() > 0){
                    builder.append(" ").append(operation).append(" ");
                }
                builder.append("(");
                builder.append(clause);
                builder.append(")");
            }
        }

        return builder.toString();
    }





}
