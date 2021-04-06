package com.lishuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author lishuai
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
