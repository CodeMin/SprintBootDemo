package com.company.springboot.security;

import com.company.springboot.constant.APIPermission;
import com.company.springboot.exception.NoPermissionException;
import com.company.springboot.utils.AnnotationParser;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Aspect
@Component
public class PermissionCheckAspect {
  /**
   * User's access permissions
   */
  private final List<Permission> permissions = new ArrayList<>(Arrays.asList(
          new Permission(APIPermission.Get),
          new Permission(APIPermission.Save)
  ));

  /**
   * Define a point cut
   */
  @Pointcut(value = "execution(* com.company.springboot.service.*.*(..))")
  public void aop() {}

  /**
   * Define a Before advice
   */
  @Before("aop()")
  public void beforePermissionCheck() {
    System.out.println("Before advice: beforePermissionCheck...");
  }

  /**
   * Define an After advice
   */
  @After("aop()")
  public void afterPermissionCheck() {
    System.out.println("After advice: afterPermissionCheck...");
  }

  /**
   * Define an AfterThrowing advice
   * @param e
   */
  @AfterThrowing(pointcut = "aop()", throwing = "e")
  public void exception(Exception e) {
    System.out.println("Exception advice: exception..." + e);
  }

  /**
   *
   * @param joinPoint
   * @throws Throwable
   */
  @Around("aop()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
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
      return joinPoint.proceed(); // execute target method
    } else {
      throw new NoPermissionException(methodName);
    }
  }
}
