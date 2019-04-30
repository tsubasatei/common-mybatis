package com.xt.mybatis.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Table 注解
 * 作用：建立实体类和数据库表之间的对应关系。
 * 默认规则：实体类类名首字母小写作为表名。Employee 类→employee 表。
 * 用法：在@Table 注解的name 属性中指定目标数据库表的表名
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_emp")
public class Employee implements Serializable {

    /**
     * @Id 注解
     * 通用Mapper 在执行xxxByPrimaryKey(key)方法时，有两种情况。
     *
     * 情况1：没有使用@Id 注解明确指定主键字段
     * SELECT emp_id,emp_name,emp_salary_apple,emp_age FROM tabple_emp WHERE emp_id = ?
     * AND emp_name = ? AND emp_salary_apple = ? AND emp_age = ?
     * 之所以会生成上面这样的WHERE 子句是因为通用Mapper 将实体类中的所有
     * 字段都拿来放在一起作为联合主键。
     *
     * 情况2：使用@Id 主键明确标记和数据库表中主键字段对应的实体类字段。
     */
    /**
     * @GeneratedValue 注解
     * 作用：让通用Mapper 在执行insert 操作之后将数据库自动生成的主键值回写到实体类对象中。
     * 非自增主键 添加属性 generator = "select SEQ_ID.nextval from dual"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;
    private String empName;

    /**
     * @Column 注解
     * 作用：建立实体类字段和数据库表字段之间的对应关系。
     * 默认规则：
     * 实体类字段：驼峰式命名
     * 数据库表字段：使用“_”区分各个单词
     * 用法：在@Column 注解的name 属性中指定目标字段的字段名
     */
    @Column(name = "emp_salary_apple")
    private Double empSalary;
    private Integer empAge;

    /**
     * @Transient 主键
     * 用于标记不与数据库表字段对应的实体类字段。
     */
}
