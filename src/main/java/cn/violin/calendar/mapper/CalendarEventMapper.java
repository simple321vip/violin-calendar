package cn.violin.calendar.mapper;

import cn.violin.calendar.entity.CalendarEventEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CalendarEventMapper extends BaseMapper<CalendarEventEntity> {

    @Select("SELECT * FROM calendar_events WHERE customer_id = #{customerId} AND is_deleted = FALSE ORDER BY event_date")
    List<CalendarEventEntity> selectByCustomerId(String customerId);
}