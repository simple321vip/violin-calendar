package cn.violin.calendar.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventIn {

    @JsonProperty("event_date")
    private String date;

    @JsonProperty("title")
    private String title;

    @JsonProperty("event_info")
    private String eventInfo;

}
