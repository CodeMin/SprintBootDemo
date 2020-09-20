package com.company.springboot.aop;

import com.company.springboot.utils.AnnotationParser;
import com.company.springboot.utils.Permission;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.ArrayList;
import java.util.List;

@Data
public class PermissionAspect {
  /**
   * User's access permissions
   */
  private List<Permission> permissions = new ArrayList<>();

  public void permissionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
    Class targetClass = joinPoint.getTarget().getClass();
    String methodName = joinPoint.getSignature().getName();
    // Get the permission name of the target method
    String permissionName = AnnotationParser.parse(targetClass, methodName);
    boolean hasPermission = false;
    // Check if the permission on user's permission list
    for (Permission permission : permissions) {
      if (permissionName.equals(permission.getName())) {
        hasPermission = true;
        break;
      }
    }

    if (hasPermission) {
      joinPoint.proceed(); // execute target method
    } else {
      System.out.println("Sorry, you have no access permission to " + methodName);
    }
  }
}
