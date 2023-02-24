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
  private String constructionClass;
  private String detailConstructionClass;
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
    String constructionClass,
    String detailConstructionClass,
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
    this.constructionClass = constructionClass;
    this.detailConstructionClass = detailConstructionClass;
    this.floorPlanUrl = floorPlanUrl;
    this.thumbnailUrl = thumbnailUrl;
    this.isParticipants = isParticipants;
    this.participants = participants;
  }
}
