-- OakHybridCacheWithDBTest 初始化sql, 批量执行连接参数注意加上allowMultiQueries=TRUE

create schema `oak-hybrid-cache-01` collate utf8mb4_0900_ai_ci;
create schema `oak-hybrid-cache-02` collate utf8mb4_0900_ai_ci;

use `oak-hybrid-cache-01`;

create table u_task_detail
(
    id          bigint auto_increment comment '主键'
        primary key,
    version     int      default 0                 not null comment '版本号',
    task_code   varchar(50)                        not null comment '任务编码',
    task_name   varchar(100)                       not null comment '任务名称',
    create_by   varchar(100)                       null comment '创建人',
    update_by   varchar(100)                       null comment '更新人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime                           null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted  tinyint  default 0                 not null comment '是否删除，默认0，1:是',
    constraint idx_task_code
        unique (task_code)
)
    comment '用户成长值任务详情表' row_format = DYNAMIC;

create table u_task_progress_2024_0
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2024_1
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;


create table u_task_progress_2024_2
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_0
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_1
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_2
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;


use `oak-hybrid-cache-02`;

create table u_task_progress_2024_0
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2024_1
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;


create table u_task_progress_2024_2
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_0
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_1
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;

create table u_task_progress_2025_2
(
    id                   varchar(100)      not null comment '主键'
        primary key,
    version              int     default 0 not null comment '版本号',
    task_code            varchar(50)       not null comment '任务编码',
    user_id              bigint            not null comment '用户ID',
    task_complete_status tinyint default 1 not null comment '任务完成状态，1:完成中，2:已完成，默认0',
    create_by            varchar(100)      null comment '创建人',
    update_by            varchar(100)      null comment '更新人',
    create_time          datetime          not null comment '创建时间',
    update_time          datetime          not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted           tinyint default 0 not null comment '是否删除,默认0,1:删除'
)
    comment '用户任务进度表' row_format = DYNAMIC;