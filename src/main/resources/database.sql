
alter table if exists employees drop constraint employees_department_id_fkey;
drop table if exists messages;
drop table if exists attachments;
drop table if exists reimbursements;
drop table if exists events;
drop table if exists grading_formats;
drop table if exists event_types;
drop table if exists departments;
drop table if exists employees;

create table employees (
	id						serial primary key,
	first_name				varchar(30),
	last_name				varchar(30),
	email					varchar(50) unique,
	password				varchar(30),
	supervisor_emp_id		integer null references employees(id) on delete set null,
	department_id			integer null,
	ben_co_emp_id			integer null references employees(id) on delete set null,
	termination_time		bigint null
);

create table departments (
	id					serial primary key,
	name				varchar(50),
	description			varchar(200) null,
	dep_head_emp_id 	integer null references employees(id) on delete set null
);

alter table employees add constraint employees_department_id_fkey
	foreign key (department_id) references departments(id) on delete set null;

create table event_types (
	id					serial primary key,
	type_name			varchar(50),
	percent_coverage	numeric(3)
);

create table grading_formats (
	id						serial primary key,
	format_name				varchar(30),
	description				varchar(200) null,
	passing_grade_cutoff	varchar(30) null
);

create table events (
	id					serial primary key,
	event_name			varchar(50),
	start_time			bigint,
	location			varchar(200),
	tuition				numeric(7,2),
	event_type_id		integer references event_types(id) on delete restrict,
	grading_format_id	integer references grading_formats(id) on delete restrict,
	end_time			bigint null
);

create table reimbursements (
	id					serial primary key,
	employee_id			integer references employees(id) on delete restrict,
	event_id			integer references events(id) on delete restrict,
	description			varchar(200),
	submission_time		bigint,
	hours_missed		numeric(6,2) null,
	final_grade			varchar(50) null,
	actual_claim		numeric(6,2) null
);

create table attachments (
	id					serial primary key,
	reimbursement_id	integer references reimbursements(id) on delete cascade,
	attachment_url		varchar(100),
	description			varchar(100)
);

create table messages (
	id					serial primary key,
	reimbursement_id	integer references reimbursements(id) on delete cascade,
	approver_type		varchar(10),
	message_type		varchar(10),
	time_sent			bigint,
	message				varchar(200)
);

insert into event_types (type_name, percent_coverage) values 
	('university course', 80),
	('seminar', 60),
	('certification preparation class', 75),
	('certification', 100),
	('technical training', 90),
	('other', 30)
;
