package com.dxj.skc.mapstruct;

import com.dxj.skc.domain.dto.UserDTO;
import com.dxj.skc.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/11/18 9:57
 * @CopyRight: 2020 skc all rights reserved.
 */

@Mapper
public interface UserMapper {

    /**
     * entity 转 dto
     * @param user
     * @return
     */
    @Mapping(source = "age", target = "age2")
    UserDTO userToUserDTO(User user);
}