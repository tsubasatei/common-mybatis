package com.xt.mybatis.service;

import com.xt.mybatis.bean.Employee;
import com.xt.mybatis.mapper.EmployeeMapper;
import com.xt.mybatis.mapper.EmployeeMapperMy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 员工Service
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeMapperMy employeeMapperMy;

    public List<Employee> getAll() {
        return employeeMapperMy.selectAll();
    }

    /**
     * 4.1selectOne 方法
     *  通用Mapper 替我们自动生成的SQL 语句情况
     *  实体类封装查询条件生成WHERE 子句的规则
     *  使用非空的值生成WHERE 子句
     *  在条件表达式中使用“=”进行比较
     *  要求必须返回一个实体类结果，如果有多个，则会抛出异常
     */
    public Employee getOne(Employee employee) {
        return employeeMapper.selectOne(employee);
    }

    /**
     * xxxByPrimaryKey 方法
     * 需要使用@Id 主键明确标记和数据库表主键字段对应的实体类字段，
     * 否则通用 Mapper 会将所有实体类字段作为联合主键。
     */
    public Employee getEmpById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public Boolean isExists(Integer empId) {
        return employeeMapper.existsWithPrimaryKey(empId);
    }

    public void saveEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }

    /**
     * xxxSelective 方法
     * 非主键字段如果为null 值，则不加入到SQL 语句中。
     */
    public void saveEmployeeSelective(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public void updateEmployeeSelective(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    // 慎用：会清空表
    public void removeEmployee(Employee employee) {
        employeeMapper.delete(employee);
    }

    public void removeEmployeeById(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    public List<Employee> getEmpListByExample(Example example) {
        return employeeMapper.selectByExample(example);
    }

    public void updateBatch(List<Employee> list) {
        employeeMapperMy.batchUpdateMapper(list);
    }
}
