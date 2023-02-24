package snust.kimth_lab.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectReqDto {
  private String name;
  private Long companyId;
  private Long managerId;
  private String startDate;
  private String endDate;
  private String constructionClass;
  private String detailConstructionClass;
  private String floorPlan;
  private String thumbnail;

  @Builder
  public ProjectReqDto(
    String name,
    Long companyId,
    Long managerId,
    String startDate,
    String endDate,
    String constructionClass,
    String detailConstructionClass,
    String floorPlan,
    String thumbnail
  ) {
    this.name = name;
    this.companyId = companyId;
    this.managerId = managerId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.constructionClass = constructionClass;
    this.detailConstructionClass = detailConstructionClass;
    this.floorPlan = floorPlan;
    this.thumbnail = thumbnail;
  }
}
