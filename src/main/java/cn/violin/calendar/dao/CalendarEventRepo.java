package cn.violin.calendar.dao;

import cn.violin.calendar.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarEventRepo extends JpaRepository<CalendarEvent, Integer> {

    public List<CalendarEvent> findAllByTenantId(String tenantId);
}
