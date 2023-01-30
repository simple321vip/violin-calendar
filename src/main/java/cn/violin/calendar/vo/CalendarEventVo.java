package cn.violin.calendar.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventVo {

    @JsonProperty("event_id")
    private int eventId;

    @JsonProperty("event_date")
    private String eventDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("event_info")
    private String eventInfo;

}
