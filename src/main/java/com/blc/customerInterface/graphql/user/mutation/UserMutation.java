package com.blc.customerInterface.graphql.user.mutation;

import com.blc.customerInterface.configuration.PermissionConfig;
import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.mutation.input.UserCreateInput;
import com.blc.customerInterface.graphql.user.mutation.input.UserUpdateInput;
import com.blc.customerInterface.graphql.user.mutation.mapper.UserMapper;
import com.blc.customerInterface.graphql.user.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserMutation(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //@PreAuthorize("hasAuthority('"+ PermissionName.USER_CREATE +"')")
    public User createUser(UserCreateInput input){
        return userService.save(userMapper.toEntity(input));
    }

    @PreAuthorize("hasAuthority('"+PermissionName.USER_UPDATE +"')")
    public User updateUser(UUID id, UserUpdateInput input){
        return userService.findById(id).map(user -> userService.update(userMapper.updateEntity(user,input))).orElseThrow(RuntimeException::new);
    }

    @PreAuthorize("hasAuthority('"+PermissionName.USER_DELETE +"')")
    public UUID deleteUser(UUID id){
        return userService.findById(id).map(userService::delete).orElseThrow(RuntimeException::new);
    }

    @PreAuthorize("hasAuthority('"+PermissionName.USER_UNDELETE +"')")
    public User undeleteUser(UUID id) {
        return userService.findById(id).map(userService::undelete).orElseThrow(RuntimeException::new);
    }
}
