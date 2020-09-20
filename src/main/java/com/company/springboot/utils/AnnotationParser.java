package com.company.springboot.utils;

import com.company.springboot.annotation.PermissionInfo;

import java.lang.reflect.Method;

public class AnnotationParser {
  /*
   * targetClass: the class format of target class
   * methodName: the method called by the client
   */
  public static String parse(Class targetClass, String methodName) throws Exception {
    String permissionName = "";
    Method method = targetClass.getMethod(methodName);
    if (method.isAnnotationPresent(PermissionInfo.class)) {
      PermissionInfo permissionInfo = method.getAnnotation(PermissionInfo.class);
      permissionName = permissionInfo.name();
    }
    return permissionName;
  }
}
