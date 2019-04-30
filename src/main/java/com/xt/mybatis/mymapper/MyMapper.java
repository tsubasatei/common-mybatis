package com.xt.mybatis.mymapper;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * @author xt
 * @create 2019/4/26 8:01
 * @Desc
 */
public interface MyMapper<T> extends SelectAllMapper<T>, SelectByExampleMapper<T>, MyBatchUpdateMapper<T> {
}
