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

  @ManyToOne
  @JoinColumn(name = "id")
  @Column(name = "user_id")
  private Long userId;
  
  @ManyToOne
  @JoinColumn(name = "company")
  @Column(name = "company")
  private String company;
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
  public Project(int processRate, Date startDate, Date endDate, String constructionClass, String detailConstructionClass, String managerName, String managerEmail, String floorPlan, String thumbnailUrl) {
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
