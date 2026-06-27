package cn.violin.calendar.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建 / 更新日历事件请求。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventRequest {

    @NotBlank
    private String eventDate;

    @NotBlank
    @Size(max = 255)
    private String title;

    private String eventInfo;
}