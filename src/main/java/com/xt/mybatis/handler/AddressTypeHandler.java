package com.xt.mybatis.handler;

import com.xt.mybatis.bean.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xt
 * @create 2019/4/26 11:15
 * @Desc
 */
public class AddressTypeHandler extends BaseTypeHandler<Address> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Address address, JdbcType jdbcType) throws SQLException {
        //1.对address对象进行验证
        if (address == null) {
            return;
        }

        //2.从address对象中取出具体数据
        String province = address.getProvince();
        String city = address.getCity();
        String street = address.getStreet();

        //3.拼装成一个字符串 规则：各个值之间使用“,”分开
        StringBuilder sb = new StringBuilder();
        sb.append(province).append(",").append(city).append(",").append(street);
        String columnValue = sb.toString();

        //4.设置参数
        preparedStatement.setString(i, columnValue);
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(columnName);

        //2.验证columnValue是否有效
        if (columnValue == null || columnName.length() == 0 || !columnValue.contains(",")) {
            return null;
        }

        //3.根据“,”对columnValue进行拆分
        String[] split = columnValue.split(",");
        String province = split[0];
        String city = split[1];
        String street = split[2];

        //5.根据具体对象组装一个Address对象
        Address address = new Address(province, city, street);
        return address;
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(i);

        //2.验证columnValue是否有效
        if(columnValue == null || columnValue.length() == 0 || !columnValue.contains(",")) {
            return null;
        }

        //3.根据“,”对columnValue进行拆分
        String[] split = columnValue.split(",");

        //4.从拆分结果数组中获取Address需要的具体数据
        String province = split[0];
        String city = split[1];
        String street = split[2];

        //5.根据具体对象组装一个Address对象
        Address address = new Address(province, city, street);

        return address;
    }

    @Override
    public Address getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = callableStatement.getString(i);

        //2.验证columnValue是否有效
        if(columnValue == null || columnValue.length() == 0 || !columnValue.contains(",")) {
            return null;
        }

        //3.根据“,”对columnValue进行拆分
        String[] split = columnValue.split(",");

        //4.从拆分结果数组中获取Address需要的具体数据
        String province = split[0];
        String city = split[1];
        String street = split[2];

        //5.根据具体对象组装一个Address对象
        Address address = new Address(province, city, street);

        return address;
    }
}
