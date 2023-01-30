package cn.violin.calendar.controller;

import cn.violin.calendar.entity.CalendarEvent;
import cn.violin.calendar.io.CalendarEventIn;
import cn.violin.calendar.service.CalendarService;
import cn.violin.calendar.vo.CalendarEventVo;
import cn.violin.common.annotation.CurrentUser;
import cn.violin.common.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    /**
     * @param tenant tenant
     * @return List<CalendarEventVo>
     */
    @GetMapping("/event")
    public ResponseEntity<List<CalendarEventVo>> getCalendarEvents(@CurrentUser Tenant tenant) {

        List<CalendarEvent> calendarEvents = calendarService.selectCalendarEvents(tenant);
        List<CalendarEventVo> vos =
            calendarEvents
                .stream().map(event -> CalendarEventVo.builder().eventId(event.getEventId())
                    .eventDate(event.getEventDate()).title(event.getTitle()).eventInfo(event.getEventInfo()).build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    /**
     * @param tenant tenant
     * @param input input
     * @return Void
     */
    @PutMapping("/event")
    public ResponseEntity<Void> putCalendarEvent(@CurrentUser Tenant tenant,
        @Valid @RequestBody CalendarEventIn input) {

        calendarService.putCalendarEvent(tenant, input);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
