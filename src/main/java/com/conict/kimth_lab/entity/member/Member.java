package com.conict.kimth_lab.entity.member;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "member")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_email")
  private String email;

  @Column(name = "company")
  private String company;

  @Column(name = "company_address")
  private String companyAddress;

  @Column(name = "user_class")
  private String userClassification;
}
