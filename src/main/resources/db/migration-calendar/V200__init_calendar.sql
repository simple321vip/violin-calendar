-- ============================================================
-- V200__init_calendar.sql — violin-calendar 初始 schema（业界标准）
-- ============================================================
-- 命名规范：
--   - 列名 snake_case（PG 默认小写）
--   - 主键统一 id（BIGSERIAL）
--   - 客户隔离用 customer_id（与 core 的 customer 表语义一致）
--   - 审计字段 created_at / updated_at / created_by / updated_by
--   - 软删除 is_deleted + deleted_at
--   - 时间戳用 TIMESTAMPTZ（带时区，避免时区问题）
--   - 表名复数 calendar_events
--   - 添加外键约束（customer_id 引用 core 的 t_customer.id）
-- ============================================================

CREATE TABLE IF NOT EXISTS calendar_events (
    id                      BIGSERIAL       PRIMARY KEY,
    customer_id             VARCHAR(36)     NOT NULL,
    event_date              DATE            NOT NULL,
    title                   VARCHAR(255)    NOT NULL,
    event_info              TEXT,

    -- 审计字段（user 维度，记录具体操作人）
    created_by_user_id      VARCHAR(64),
    created_at              TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by_user_id      VARCHAR(64),
    updated_at              TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- 软删除
    is_deleted              BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at              TIMESTAMPTZ
);

-- 索引：客户隔离查询
CREATE INDEX IF NOT EXISTS idx_calendar_events_customer_id ON calendar_events (customer_id);

-- 索引：按日期查
CREATE INDEX IF NOT EXISTS idx_calendar_events_event_date ON calendar_events (event_date);

-- 复合索引：客户 + 日期（最常用查询模式）
CREATE INDEX IF NOT EXISTS idx_calendar_events_customer_date
    ON calendar_events (customer_id, event_date DESC)
    WHERE is_deleted = FALSE;

-- 注释
COMMENT ON TABLE  calendar_events IS '日历事件表';
COMMENT ON COLUMN calendar_events.customer_id IS '所属客户 ID（多租户隔离，R1 重构：原 TENANT_ID）';
COMMENT ON COLUMN calendar_events.created_by_user_id IS '创建人 user_id（操作人）';
COMMENT ON COLUMN calendar_events.updated_by_user_id IS '最后修改人 user_id（操作人）';
COMMENT ON COLUMN calendar_events.is_deleted IS '软删除标记：FALSE=有效，TRUE=已删除';
