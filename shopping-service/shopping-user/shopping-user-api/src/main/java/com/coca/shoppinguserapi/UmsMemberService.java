package com.coca.shoppinguserapi;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.UserDto;

public interface UmsMemberService {
    UserDto loadUserByUsername(String username);
    UmsMember getByUsername(String username);
}
