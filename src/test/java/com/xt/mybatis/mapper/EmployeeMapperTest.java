package com.xt.mybatis.mapper;

import com.xt.mybatis.bean.Employee;
import com.xt.mybatis.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xt
 * @create 2019/4/25 13:17
 * @Desc
 */
public class EmployeeMapperTest {

    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-context.xml");
    private EmployeeService employeeService = (EmployeeService) iocContainer.getBean("employeeService");

    @Test
    public void testCache () {
        testSelectAllMy();

        System.out.println("----------");

        testSelectAllMy();
    }

    @Test
    public void testUpdateBatch () {
        List<Employee> list = new ArrayList<>();
        Employee employee = new Employee(9, "emp01", 111.11, 18);
        Employee employee1 = new Employee(10, "emp02", 222.22, 19);
        Employee employee2 = new Employee(11, "emp03", 333.33, 19);

        list.add(employee);
        list.add(employee1);
        list.add(employee2);

        employeeService.updateBatch(list);

        testSelectAllMy();
    }

    @Test
    public void testSelectAllMy () {
        List<Employee> employees = employeeService.getAll();
        employees.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectOne () {

        //1.创建封装查询条件的实体类对象
        Employee empQueryCondition = new Employee(null, "bob", 5560.11, null);

        //2.执行查询
        Employee employee = employeeService.getOne(empQueryCondition);

        System.out.println(employee);
    }

    @Test
    public void testSelectByPrimaryKey () {
        Integer empId = 3;
        Employee employee = employeeService.getEmpById(empId);
        System.out.println(employee);
    }

    @Test
    public void testExistsWithPrimaryKey () {
        Integer empId = 33;
        Boolean flag = employeeService.isExists(empId);
        System.out.println(flag);
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(null, "emp01", 1000d, 23);
        employeeService.saveEmployee(employee);
        System.out.println("empId: " + employee.getEmpId());
    }

    @Test
    public void testInsertSelective() {
        Employee employee = new Employee(null, "emp01", null, 23);
        employeeService.saveEmployeeSelective(employee);
        System.out.println("empId: " + employee.getEmpId());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {

        //1.创建用于测试的实体类
        Employee employee = new Employee(7, "empNewName", null, null);

        //2.执行更新
        employeeService.updateEmployeeSelective(employee);

    }

    @Test
    public void testDelete() {

        //1.声明实体类变量作为查询条件
        Employee employee = null;

        //2.执行删除
        employeeService.removeEmployee(employee);

    }

    @Test
    public void testDeleteByPrimaryKey() {

        //1.提供主键值
        Integer empId = 8;

        //2.执行删除
        employeeService.removeEmployeeById(empId);
    }

    /**
     * QBC 查询是将查询条件通过Java 对象进行模块化封装。
     */
    @Test
    public void testSelectByExample() {
        //目标：WHERE (emp_salary>? AND emp_age<?) OR (emp_salary<? AND emp_age>?)
        //1.创建Example对象
        Example example = new Example(Employee.class);

        //i.设置排序信息
        example.orderBy("empSalary").asc().orderBy("empAge").desc();

        //ii.设置“去重”
        example.setDistinct(true);

        //iii.设置select字段
        example.selectProperties("empSalary", "empAge");


        //2.通过Example对象创建Criteria对象
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();

        //3.在两个Criteria对象中分别设置查询条件
        //property参数：实体类的属性名
        //value参数：实体类的属性值
        criteria1.andGreaterThan("empSalary", 3000)
                 .andLessThan("empAge", 25);

        criteria2.andLessThan("empSalary", 5000)
                .andGreaterThan("empAge",30);

        //4.使用OR关键词组装两个Criteria对象
        example.or(criteria2);

        List<Employee> list = employeeService.getEmpListByExample(example);
        System.out.println(list);
    }

    @Test
    public void testDataSource () {
        DataSource dataSource = (DataSource) iocContainer.getBean("dataSource");
        System.out.println(dataSource);
    }
}