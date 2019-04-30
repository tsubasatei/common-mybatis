package com.xt.mybatis.mapper;

import com.xt.mybatis.bean.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * 具体操作数据库的Mapper 接口， 需要继承通用Mapper 提供的核心接口：Mapper<Employee>
 * 泛型类型就是实体类的类型
 */
public interface EmployeeMapper extends Mapper<Employee> {
}
