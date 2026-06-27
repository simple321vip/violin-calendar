package cn.violin.calendar.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 日历事件实体（MyBatis Plus）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("calendar_events")
public class CalendarEventEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("customer_id")
    private String customerId;

    @TableField("event_date")
    private String eventDate;

    @TableField("title")
    private String title;

    @TableField("event_info")
    private String eventInfo;

    @TableField(value = "created_by_user_id", fill = FieldFill.INSERT)
    private String createdByUserId;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private OffsetDateTime createdAt;

    @TableField(value = "updated_by_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updatedByUserId;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime updatedAt;

    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("deleted_at")
    private OffsetDateTime deletedAt;
}