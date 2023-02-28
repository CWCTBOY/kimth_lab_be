package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProjectResDto {
  private Long id;
  private String name;
  private int processRate;
  private String startDate;
  private String endDate;
  private String ctrClass;
  private String detailCtrClass;
  private String floorPlanUrl;
  private String thumbnailUrl;
  private boolean isParticipants;
  private List<ParticipantsResDto> participants;

  @Builder
  public ProjectResDto(
    Long id,
    String name,
    int processRate,
    String startDate,
    String endDate,
    String ctrClass,
    String detailCtrClass,
    String floorPlanUrl,
    String thumbnailUrl,
    boolean isParticipants,
    List<ParticipantsResDto> participants
  ) {
    this.id = id;
    this.name = name;
    this.processRate = processRate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.ctrClass = ctrClass;
    this.detailCtrClass = detailCtrClass;
    this.floorPlanUrl = floorPlanUrl;
    this.thumbnailUrl = thumbnailUrl;
    this.isParticipants = isParticipants;
    this.participants = participants;
  }
}
