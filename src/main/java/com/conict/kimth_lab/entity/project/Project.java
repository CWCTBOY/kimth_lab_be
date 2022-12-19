package com.conict.kimth_lab.entity.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "project")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "process_rate")
  private int processRate;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "construction_class")
  private String constructionClass;

  @Column(name = "detail_construction_class")
  private String detailConstructionClass;

  @Column(name = "manager_name")
  private String managerName;

  @Column(name = "manager_email")
  private String managerEmail;

  @Column(name = "floor_plan")
  private String floorPlan;

  @Column(name = "thumbnail_url")
  private String thumbnailUrl;

  @Builder
  public Project(Date startDate, Date endDate, String constructionClass, String detailConstructionClass, String managerName, String managerEmail, String floorPlan, String thumbnailUrl) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.constructionClass = constructionClass;
    this.detailConstructionClass = detailConstructionClass;
    this.managerName = managerName;
    this.managerEmail = managerEmail;
    this.floorPlan = floorPlan;
    this.thumbnailUrl = thumbnailUrl;
  }
}
