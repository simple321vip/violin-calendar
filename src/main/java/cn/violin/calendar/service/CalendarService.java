package cn.violin.calendar.service;

import cn.violin.calendar.dao.CalendarEventRepo;
import cn.violin.calendar.entity.CalendarEvent;
import cn.violin.calendar.io.CalendarEventIn;
import cn.violin.common.entity.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    @Autowired
    private CalendarEventRepo calendarEventRepo;

    public List<CalendarEvent> selectCalendarEvents(Tenant tenant) {

        return calendarEventRepo.findAllByTenantId(tenant.getTenantId());
    }

    public void putCalendarEvent(Tenant tenant, CalendarEventIn input) {
        CalendarEvent event = new CalendarEvent();
        event.setEventDate(input.getDate());
        event.setEventInfo(input.getEventInfo());
        event.setTitle(input.getTitle());
        event.setTenantId(tenant.getTenantId());
        calendarEventRepo.save(event);
    }

}
