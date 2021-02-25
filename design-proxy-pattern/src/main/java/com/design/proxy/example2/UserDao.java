package com.design.proxy.example2;

import org.springframework.stereotype.Repository;

/**
 * Dao层接口
 */
@Repository
public interface UserDao {
    @Select("select * from User where uid = #{uid}")
    String selectOne(String uid);
}
