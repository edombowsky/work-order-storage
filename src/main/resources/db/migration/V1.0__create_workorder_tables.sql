---------------------------------------------------------------------------------------------
--Create Work Orders Table
---------------------------------------------------------------------------------------------
create table datafabric_workorder.work_orders (
    work_order_uuid varchar(36) collate pg_catalog."default" not null,
    parent_work_order varchar(36) collate pg_catalog."default" null,
    description varchar(100) collate pg_catalog."default" not null,
    status varchar(32) collate pg_catalog."default" not null,
    priority int4 null,
    work_type varchar(64) collate pg_catalog."default" null,
    asset varchar(36) collate pg_catalog."default" null,
    assigned_to varchar(36) collate pg_catalog."default" null,
    required_by_date_time timestamp null,
    extended_description text collate pg_catalog."default" null,
    last_modified_timestamp timestamp null,
    last_modified_by varchar(20) collate pg_catalog."default" null,
    created_timestamp timestamp null,
    created_by varchar(20) collate pg_catalog."default" null,
    work_order_id varchar(32) collate pg_catalog."default" null,
    work_order_template varchar(36) collate pg_catalog."default" null,
    user_status varchar(32) collate pg_catalog."default" null,
    asset_work bool null,
    raised_date_time timestamp null,
    raised_by varchar(36) collate pg_catalog."default" null,
    planned_start_date_time timestamp null,
    planned_finish_date_time timestamp null,
    actual_start_date_time timestamp null,
    actual_finish_date_time timestamp null,
    closed_date_time timestamp null,
    planned_duration int8 null,
    actual_duration int8 null,
    completed_by varchar(36) collate pg_catalog."default" null,
    completion_comments text collate pg_catalog."default" null,
    "location" varchar(36) collate pg_catalog."default" null,
    asset_position varchar(36) collate pg_catalog."default" null,
    solution_attributes jsonb null,
    custom_attributes jsonb null,
    responsible_org varchar(36) collate pg_catalog."default" null,
    account varchar(36) collate pg_catalog."default" null,
    attachment jsonb null,
    product_service_requirement jsonb null,
    activity jsonb null,
    constraint work_order_pkey primary key (work_order_uuid)
);

---------------------------------------------------------------------------------------------
--Create Work Order Templates Table
---------------------------------------------------------------------------------------------
create table datafabric_workorder.work_order_templates (
    work_order_template_uuid varchar(36) collate pg_catalog."default" not null,
    work_order_template_id varchar(32) collate pg_catalog."default" null,
    parent_work_order_template varchar(36) collate pg_catalog."default" null,
    description varchar(100) collate pg_catalog."default" null,
    extended_description text collate pg_catalog."default" null,
    extendeddescription text collate pg_catalog."default" null,
    status varchar(32) collate pg_catalog."default" null,
    asset_work bool null,
    work_type varchar(20) collate pg_catalog."default" null,
    default_priority int4 null,
    duration int8 null,
    owned_by varchar(36) collate pg_catalog."default" null,
    active_date_time timestamp null,
    activated_by varchar(36) collate pg_catalog."default" null,
    asset varchar(36) collate pg_catalog."default" null,
    asset_position varchar(36) collate pg_catalog."default" null,
    assigned_to varchar(36) collate pg_catalog."default" null,
    solution_attributes jsonb null,
    custom_attributes jsonb null,
    last_modified_timestamp timestamp null,
    last_modified_by varchar(20) collate pg_catalog."default" null,
    created_timestamp timestamp null,
    created_by varchar(20) collate pg_catalog."default" null,
    responsible_org varchar(36) collate pg_catalog."default" null,
    attachment jsonb null,
    product_service_requirement jsonb null,
    activity_template jsonb null,
    constraint work_order_template_pkey primary key (work_order_template_uuid)
);

---------------------------------------------------------------------------------------------
--Create Work Order External Ids Table
---------------------------------------------------------------------------------------------
create table datafabric_workorder.work_order_external_ids (
    work_order_id varchar(36)  collate pg_catalog."default"not null,
    creator_id varchar(50) collate pg_catalog."default" null,
    master_of_record_id varchar(50) collate pg_catalog."default" null,
    last_modified_timestamp timestamp null,
    last_modified_by varchar(20) collate pg_catalog."default" null,
    created_timestamp timestamp null,
    created_by varchar(20) collate pg_catalog."default" null,
    constraint creator_id_unique unique (creator_id),
    constraint master_of_record_id_unique unique (master_of_record_id),
    constraint work_order_external_ids_pkey primary key (work_order_id)
);

create unique index work_order_external_ids_idx on datafabric_workorder.work_order_external_ids using btree (work_order_id, creator_id, master_of_record_id);

---------------------------------------------------------------------------------------------
--Create Work Order Location Gis Table
---------------------------------------------------------------------------------------------
create table datafabric_workorder.work_order_location_gis (
    work_order_id varchar(36) collate pg_catalog."default" not null,
    asset_reference varchar(20) collate pg_catalog."default" null,
    "type" varchar(20) collate pg_catalog."default" null,
    reference_type varchar(20) collate pg_catalog."default" null,
    route_connection varchar(20) collate pg_catalog."default" null,
    route_feature_class varchar(20) collate pg_catalog."default" null,
    route_feature_id varchar(36) collate pg_catalog."default" null,
    start_point point null,
    end_point point null,
    start_distance numeric null,
    end_distance numeric null,
    units varchar(20) null,
    last_modified_timestamp timestamp null,
    last_modified_by varchar(20) collate pg_catalog."default" null,
    created_timestamp timestamp null,
    created_by varchar(20) collate pg_catalog."default" null,
    constraint work_order_location_gis_pkey primary key (work_order_id)
);

create index work_order_location_gis_points_idx on datafabric_workorder.work_order_location_gis using gist (start_point, end_point);

---------------------------------------------------------------------------------------------
--Create Work Order Template Dependencies Table
---------------------------------------------------------------------------------------------
create table datafabric_workorder.work_order_template_dependencies (
    work_order_template_dependency_uuid varchar(36) collate pg_catalog."default" not null,
    source_work_order_template varchar(36) collate pg_catalog."default" null,
    target_work_order_template varchar(36) collate pg_catalog."default" null,
    dependency_type varchar(20) collate pg_catalog."default" null,
    solution_attributes jsonb null,
    custom_attributes jsonb null,
    last_modified_timestamp timestamp null,
    last_modified_by varchar(20) collate pg_catalog."default" null,
    created_timestamp timestamp null,
    created_by varchar(20) collate pg_catalog."default" null,
    constraint work_order_template_dependency_pkey primary key (work_order_template_dependency_uuid)
);
