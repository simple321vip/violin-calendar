package cn.violin.calendar.dto.response;

import cn.violin.calendar.entity.CalendarEventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 日历事件响应（对外暴露）。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventResponse {

    private Long id;
    private String customerId;
    private String eventDate;
    private String title;
    private String eventInfo;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public static CalendarEventResponse from(CalendarEventEntity event) {
        if (event == null) return null;
        return CalendarEventResponse.builder()
            .id(event.getId())
            .customerId(event.getCustomerId())
            .eventDate(event.getEventDate())
            .title(event.getTitle())
            .eventInfo(event.getEventInfo())
            .createdAt(event.getCreatedAt())
            .updatedAt(event.getUpdatedAt())
            .build();
    }
}