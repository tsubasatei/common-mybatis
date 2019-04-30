package com.xt.mybatis.mymapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @author xt
 * @create 2019/4/26 8:47
 * @Desc
 */
public class MyBatchProvider extends MapperTemplate {
    public MyBatchProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * <foreach collection="list" item="record" separator=";" >
     * UPDATE tabple_emp
     * <set>
         * emp_name=#{record.empName},
         * emp_age=#{record.empAge},
         * emp_salary=#{record.empSalary}
     * </set>
     * where emp_id=#{record.empId}
     * </foreach>
     *
     */
    public String batchUpdateMapper(MappedStatement mappedStatement) {

        //1.创建StringBuilder用于拼接SQL语句的各个组成部分
        StringBuilder stringBuilder = new StringBuilder();

        //2.拼接foreach标签
        stringBuilder.append("<foreach collection=\"list\" item=\"record\" separator=\";\" >");

        //3.获取实体类对应的Class对象
        Class<?> entityClass = super.getEntityClass(mappedStatement);
        //4.获取实体类在数据库中对应的表名
        String tableName = super.tableName(entityClass);

        //5.生成update子句
        String updateClause = SqlHelper.updateTable(entityClass, tableName);
        stringBuilder.append(updateClause);

        stringBuilder.append("<set>");

        //6.获取所有字段信息
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);

        String idColumn = null;
        String idHolder = null;

        for (EntityColumn entityColumn : columns) {

            boolean isPrimaryKey = entityColumn.isId();

            //7.判断当前字段是否为主键
            if (isPrimaryKey) {
                //8.缓存主键的字段名和字段值
                idColumn = entityColumn.getColumn();
                //※返回格式如:#{record.age,jdbcType=NUMERIC,typeHandler=MyTypeHandler}
                idHolder = entityColumn.getColumnHolder("record");
            } else {
                //9.使用非主键字段拼接SET子句
                String column = entityColumn.getColumn();
                String columnHolder = entityColumn.getColumnHolder("record");
                stringBuilder.append(column).append("=").append(columnHolder).append(",");
            }
        }

        stringBuilder.append("</set>");

        //10.使用前面缓存的主键名、主键值拼接where子句
        stringBuilder.append("where ").append(idColumn).append("=").append(idHolder);

        stringBuilder.append("</foreach>");

        //11.将拼接好的字符串返回
        return stringBuilder.toString();
    }
}
