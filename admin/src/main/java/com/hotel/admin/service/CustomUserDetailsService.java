package com.hotel.admin.service;

import com.hotel.admin.vo.UserPrincipal;
import com.hotel.model.*;
import com.hotel.model.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @package: com.xkcoding.rbac.security.service
 * @description: 自定义UserDetails查询
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 10:29
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SecUserMapper secUserMapper;

    @Autowired
    private SecRoleMapper secRoleMapper;

    @Autowired
    private SecUserRoleMapper secUserRoleMapper;

    @Autowired
    private SecPermissionMapper secPermissionMapper;

    @Autowired
    private SecRolePermissionMapper rolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        SecUserExample secUserExample = new SecUserExample();
        SecUserExample.Criteria criteria = secUserExample.createCriteria();
        criteria.andPhoneEqualTo(usernameOrEmailOrPhone);
        SecUserExample.Criteria criteria5 = secUserExample.createCriteria();
        criteria5.andUsernameEqualTo(usernameOrEmailOrPhone);
        secUserExample.or(criteria5);


        List<SecUser> secUsers = secUserMapper.selectByExample(secUserExample);
        if (secUsers.size() <= 0) {
                throw new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone);
        }
        SecUser user = secUsers.get(0);
        SecUserRoleExample secUserRoleExample = new SecUserRoleExample();
        SecUserRoleExample.Criteria criteria1 = secUserRoleExample.createCriteria();
        criteria1.andUserIdEqualTo(user.getId());

        List<SecUserRole> secUserRoles = secUserRoleMapper.selectByExample(secUserRoleExample);
        if (secUserRoles.size() > 0) {
            List<Long> roleIds = secUserRoles.stream().map(SecUserRole::getRoleId).collect(Collectors.toList());

            SecRolePermissionExample rolePermissionExample = new SecRolePermissionExample();
            SecRolePermissionExample.Criteria criteria2 = rolePermissionExample.createCriteria();
            criteria2.andRoleIdIn(roleIds);
            List<SecRolePermission> secRolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
            List<Long> permissionIds = secRolePermissions.stream().map(SecRolePermission::getPermissionId).collect(Collectors.toList());
            SecPermissionExample secPermissionExample = new SecPermissionExample();
            SecPermissionExample.Criteria criteria3 = secPermissionExample.createCriteria();
            criteria3.andIdIn(permissionIds);
            List<SecPermission> permissions = secPermissionMapper.selectByExample(secPermissionExample);

            SecRoleExample secRoleExample = new SecRoleExample();
            SecRoleExample.Criteria criteria4 = secRoleExample.createCriteria();
            criteria4.andIdIn(roleIds);
            List<SecRole> roles = secRoleMapper.selectByExample(secRoleExample);
            return UserPrincipal.create(user, roles, permissions);
        }
        return UserPrincipal.create(user, null, null);
    }
}
