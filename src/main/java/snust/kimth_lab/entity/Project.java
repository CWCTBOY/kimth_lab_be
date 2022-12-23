package snust.kimth_lab.entity;

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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(name = "user_id")
  private Long user_id;
  @Column(name = "company")
  private String company;
  @Column(name = "process_rate", columnDefinition = "INTEGER default '0'")
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
  @Column(name = "floor_plan  ")
  private String floorPlan;
  @Column(name = "thumbnail_url")
  private String thumbnailUrl;

  @Builder
  public Project(Long user_id, String company, int processRate, Date startDate, Date endDate, String constructionClass, String detailConstructionClass, String managerName, String managerEmail, String floorPlan, String thumbnailUrl) {
    this.user_id = user_id;
    this.company = company;
    this.processRate = processRate;
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
