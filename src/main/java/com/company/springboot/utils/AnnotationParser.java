package com.company.springboot.utils;

import com.company.springboot.entity.UserEntity;
import com.company.springboot.security.PermissionInfo;

import java.lang.reflect.Method;

public class AnnotationParser {
  /*
   * targetClass: the class format of target class
   * methodName: the method called by the client
   */
  public static String parse(Class targetClass, String methodName) throws Exception {
    String permissionName = "";
    Method method = null;
    if (methodName.equals("getUserById")) {
      method = targetClass.getDeclaredMethod(methodName, Long.class);
    } else if (methodName.equals("saveUser")) {
      method = targetClass.getDeclaredMethod(methodName, UserEntity.class);
    }

    if (method != null && method.isAnnotationPresent(PermissionInfo.class)) {
      PermissionInfo permissionInfo = method.getAnnotation(PermissionInfo.class);
      permissionName = permissionInfo.name();
    }
    return permissionName;
  }
}
