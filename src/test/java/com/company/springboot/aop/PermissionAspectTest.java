package com.company.springboot.aop;

import com.company.springboot.entity.UserEntity;
import com.company.springboot.service.UserService;
import com.company.springboot.utils.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PermissionAspectTest {

  @Test
  public void testPermissionAspect() {
    ApplicationContext context = new FileSystemXmlApplicationContext("aop.xml");

    // Set user's permissions
    PermissionAspect permissionAspect = (PermissionAspect)context.getBean("permissionAspect");

    Permission permission1 = new Permission();
    permission1.setName("test");

    //Permission permission2 = new Permission();
    //permission2.setName("saveUser");

    permissionAspect.getPermissions().add(permission1);
    //permissionAspect.getPermissions().add(permission2);

    // Call user's method
    UserService userService = (UserService)context.getBean("userService");
    UserEntity userEntity = new UserEntity();
    userEntity.setId(Long.MAX_VALUE);
    userEntity.setName("Test");
    userEntity.setEmail("test@example.com");
    userEntity.setPhone("12345");
    userService.saveUser(userEntity);

    userService.getUserById(Long.MAX_VALUE);
  }
}
