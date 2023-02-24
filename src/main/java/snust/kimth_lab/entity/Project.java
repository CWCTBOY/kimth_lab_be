package snust.kimth_lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "project")
public class Project {
  @JsonIgnore
  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
  List<Participants> participants;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @NotNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  private Company company;
  @NotNull
  @Column(name = "process_rate", columnDefinition = "INTEGER default '0'")
  private int processRate;
  @NotNull
  @Column(name = "start_date")
  private String startDate;
  @NotNull
  @Column(name = "end_date")
  private String endDate;
  @NotNull
  @Column(name = "construction_class")
  private String constructionClass;
  @NotNull
  @Column(name = "detail_construction_class")
  private String detailConstructionClass;
  @Column(name = "floor_plan_url")
  private String floorPlanUrl;
  @Column(name = "thumbnail_url")
  private String thumbnailUrl;


  @Builder
  public Project(
    String name,
    Company company,
    int processRate,
    String startDate,
    String endDate,
    String constructionClass,
    String detailConstructionClass,
    String floorPlanUrl,
    String thumbnailUrl
  ) {
    this.name = name;
    this.company = company;
    this.processRate = processRate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.constructionClass = constructionClass;
    this.detailConstructionClass = detailConstructionClass;
    this.floorPlanUrl = floorPlanUrl;
    this.thumbnailUrl = thumbnailUrl;
  }
}
