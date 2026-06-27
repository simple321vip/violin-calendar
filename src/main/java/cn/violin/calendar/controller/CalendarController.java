package cn.violin.calendar.controller;

import cn.violin.calendar.dto.request.CalendarEventRequest;
import cn.violin.calendar.dto.response.CalendarEventResponse;
import cn.violin.calendar.entity.CalendarEventEntity;
import cn.violin.calendar.service.CalendarService;
import cn.violin.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ============================
 * 业务模块：events（日历事件）
 * ============================
 *
 * <p>URL：{@code /api/v1/events}（复数 + kebab-case）。</p>
 *
 * <p>多租户隔离：customer_id 由 MyBatis Plus interceptor 自动从
 * {@link cn.violin.common.context.RequestContext} 读取，SQL 自动加
 * {@code WHERE customer_id = ?}。</p>
 */
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public ApiResponse<List<CalendarEventResponse>> list() {
        List<CalendarEventEntity> events = calendarService.list();
        return ApiResponse.ok(events.stream().map(CalendarEventResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ApiResponse<CalendarEventResponse> get(@PathVariable("id") Long id) {
        return ApiResponse.ok(CalendarEventResponse.from(calendarService.getById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CalendarEventResponse> create(@Valid @RequestBody CalendarEventRequest request) {
        return ApiResponse.ok(CalendarEventResponse.from(calendarService.create(request)));
    }

    @PutMapping("/{id}")
    public ApiResponse<CalendarEventResponse> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody CalendarEventRequest request) {
        return ApiResponse.ok(CalendarEventResponse.from(calendarService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        calendarService.delete(id);
    }
}