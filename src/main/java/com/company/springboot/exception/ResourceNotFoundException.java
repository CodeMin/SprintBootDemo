package com.company.springboot.exception;

import lombok.Data;

/**
 * @author Min Xu
 * @description Record not found exception
 */

@Data
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3137516402200994728L;

  private Long id;

  public ResourceNotFoundException(Long id) {
    super("Resource ID '" + id + "' does not exist." );
    this.id = id;
  }

}
