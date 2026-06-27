package cn.violin.calendar.service;

import cn.violin.calendar.dto.request.CalendarEventRequest;
import cn.violin.calendar.entity.CalendarEventEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CalendarService {
    @Transactional(readOnly = true)
    List<CalendarEventEntity> list();

    @Transactional(readOnly = true)
    CalendarEventEntity getById(Long id);

    @Transactional
    CalendarEventEntity create(CalendarEventRequest request);

    @Transactional
    CalendarEventEntity update(Long id, CalendarEventRequest request);

    @Transactional
    void delete(Long id);
}
