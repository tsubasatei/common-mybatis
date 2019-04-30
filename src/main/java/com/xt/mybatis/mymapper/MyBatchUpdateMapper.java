package com.xt.mybatis.mymapper;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author xt
 * @create 2019/4/26 8:44
 * @Desc
 */
public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type = MyBatchProvider.class, method = "dynamicSQL")
    void batchUpdateMapper(List<T> list);
}
