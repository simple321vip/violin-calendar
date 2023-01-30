package cn.violin.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CALENDAR_EVENT")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private int eventId;

    @Column(name = "EVENT_DATE")
    private String eventDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EVENT_INFO")
    private String eventInfo;

    @Column(name = "TENANT_ID")
    private String tenantId;

}