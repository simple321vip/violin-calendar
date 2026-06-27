package cn.violin.calendar.service.impl;

import cn.violin.calendar.dto.request.CalendarEventRequest;
import cn.violin.calendar.entity.CalendarEventEntity;
import cn.violin.calendar.mapper.CalendarEventMapper;
import cn.violin.calendar.service.CalendarService;
import cn.violin.common.context.RequestContext;
import cn.violin.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日历事件业务实现。
 */
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarEventMapper calendarEventMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CalendarEventEntity> list() {
        return calendarEventMapper.selectByCustomerId(currentCustomerId());
    }

    @Transactional(readOnly = true)
    @Override
    public CalendarEventEntity getById(Long id) {
        CalendarEventEntity event = calendarEventMapper.selectById(id);
        if (event == null) {
            throw new ResourceNotFoundException("Event not found: " + id);
        }
        return event;
    }

    @Transactional
    @Override
    public CalendarEventEntity create(CalendarEventRequest request) {
        CalendarEventEntity event = new CalendarEventEntity();
        event.setCustomerId(currentCustomerId());
        event.setCreatedByUserId(currentUserId());
        event.setEventDate(request.getEventDate());
        event.setTitle(request.getTitle());
        event.setEventInfo(request.getEventInfo());
        calendarEventMapper.insert(event);
        return event;
    }

    @Transactional
    @Override
    public CalendarEventEntity update(Long id, CalendarEventRequest request) {
        CalendarEventEntity existing = getById(id);
        existing.setEventDate(request.getEventDate());
        existing.setTitle(request.getTitle());
        existing.setEventInfo(request.getEventInfo());
        existing.setUpdatedByUserId(currentUserId());
        calendarEventMapper.updateById(existing);
        return existing;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getById(id);
        calendarEventMapper.deleteById(id);
    }

    private String currentCustomerId() {
        String id = RequestContext.getCustomerId();
        if (id == null) throw new IllegalStateException("RequestContext.customerId 未设置");
        return id;
    }

    private String currentUserId() {
        String u = RequestContext.getUserId();
        return u == null ? "system" : u;
    }
}