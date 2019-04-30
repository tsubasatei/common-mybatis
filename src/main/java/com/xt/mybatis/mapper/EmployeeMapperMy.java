package com.xt.mybatis.mapper;


import com.xt.mybatis.bean.Employee;
import com.xt.mybatis.mymapper.MyMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author xt
 * @create 2019/4/26 8:04
 * @Desc
 */
@CacheNamespace
public interface EmployeeMapperMy extends MyMapper<Employee> {
}
