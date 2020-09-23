package com.company.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Min Xu
 * @description User entity
 */

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Username cannot be empty.")
  @Size(min = 5, max = 20, message = "The length of username must be between 5 and 20.")
  private String name;

  @Email(message = "Invalid email format.")
  private String email;

  private String phone;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "update_time")
  private Date updateTime;

  @PrePersist
  protected void onCreate() {
    createTime = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updateTime = new Date();
  }

}
