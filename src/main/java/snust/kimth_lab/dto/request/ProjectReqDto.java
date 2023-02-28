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
  private String ctrClass;
  private String detailCtrClass;
  private String floorPlan;
  private String thumbnail;

  @Builder
  public ProjectReqDto(
    String name,
    Long companyId,
    Long managerId,
    String startDate,
    String endDate,
    String ctrClass,
    String detailCtrClass,
    String floorPlan,
    String thumbnail
  ) {
    this.name = name;
    this.companyId = companyId;
    this.managerId = managerId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.ctrClass = ctrClass;
    this.detailCtrClass = detailCtrClass;
    this.floorPlan = floorPlan;
    this.thumbnail = thumbnail;
  }
}
