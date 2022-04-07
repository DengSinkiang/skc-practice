package com.dxj.skc.mapstruct;

import com.dxj.skc.domain.dto.UserDTO;
import com.dxj.skc.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author: sinkiang
 * @date: 2022/4/7 10:32
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