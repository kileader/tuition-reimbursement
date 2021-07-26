
alter table if exists employees drop constraint employees_department_id_fkey;
alter table if exists events drop constraint events_grading_format_id_fkey;
alter table if exists events drop constraint events_event_type_id_fkey;
alter table if exists reimbursements drop constraint reimbursements_event_id_fkey;
drop table if exists events_reimbursements;
drop table if exists messages;
drop table if exists attachments;
drop table if exists grading_formats;
drop table if exists event_types;
drop table if exists events;
drop table if exists reimbursements;
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
	tuition				numeric(8,2),
	event_type_id		integer null references event_types(id) on delete set null,
	grading_format_id	integer null references grading_formats(id) on delete set null,
	end_time			bigint null
);

create table reimbursements (
	id					serial primary key,
	employee_id			integer references employees(id) on delete set null,
	event_id			integer references events(id) on delete set null,
	description			varchar(200),
	submission_time		bigint,
	hours_missed		numeric(8,2) null,
	final_grade			varchar(50) null,
	actual_claim		numeric(6,2) null
);

create table attachments (
	id					serial primary key,
	reimbursement_id	integer references reimbursements(id) on delete set null,
	attachment_url		varchar(100),
	description			varchar(100)
);

create table messages (
	id					serial primary key,
	reimbursement_id	integer references reimbursements(id) on delete set null,
	approver_type		varchar(10),
	message_type		varchar(10),
	time_sent			bigint,
	message				varchar(200)
);

insert into event_types values (1, 'university course', 80);
insert into event_types values (2, 'seminar', 60);
insert into event_types values (3, 'certification preparation class', 75);
insert into event_types values (4, 'certification', 100);
insert into event_types values (5, 'technical training', 90);
insert into event_types values (6, 'other', 30);

insert into departments values (1, 'Services', 'Janitorial and clerk staff', null);
insert into departments values (2, 'Engineering', 'Bulk of this IT company', null);
insert into departments values (3, 'Human Resources', 'Includes benefits coordinators', null);
																								--supervisor_emp_id, department_id, ben_co_emp_id
insert into employees values (1, 'Melvyn', 'Tippetts', 'mtippetts0@gmpg.org', '6GEXmZSP', 					null, 3, null, null); -- 	1 ben co head
insert into employees values (2, 'Pippa', 'Tasseler', 'ptasseler1@technorati.com', 'nIPt3pNB', 				1, 3, 1, null);	--		2 second ben co
insert into employees values (3, 'Rozalin', 'Golson', 'rgolson2@dropbox.com', 'Ke4GS5qop7WH', 				null, 2, 1, null); -- 	3 IT head
insert into employees values (4, 'Hoyt', 'Donovan', 'hdonovan3@businessinsider.com', 'rWUM01OQX', 			3, 2, 1, null); -- 		4 IT supervisor
insert into employees values (5, 'Meara', 'Quenell', 'mquenell4@simplemachines.org', 'SY7hSLJb', 			null, 1, 1, null); --	5 services head
insert into employees values (6, 'Benny', 'Barnewell', 'bbarnewell5@yandex.ru', 'aA8vR7c', 					3, 2, 1, 1443888000000); --6 terminated IT
insert into employees values (7, 'Grethel', 'Valois', 'gvalois6@technorati.com', 'YVkWvB51', 				3, 2, 1, null); --		7 IT supervisor
insert into employees values (8, 'Devi', 'Eglise', 'deglise7@ebay.co.uk', 'fbSH2AC', 						5, 1, 1, null);
insert into employees values (9, 'Tracee', 'Baumler', 'tbaumler8@amazon.co.uk', '7Fw892E5lED', 				4, 2, 2, null);
insert into employees values (10, 'Wallache', 'Marousek', 'wmarousek9@springer.com', 'ZWcVaZOXi', 			4, 2, 2, null);
insert into employees values (11, 'Donavon', 'Aireton', 'dairetona@msu.edu', 'rmzugDS9s02', 				1, 3, 2, null);
insert into employees values (12, 'Joleen', 'Rope', 'jropeb@nba.com', 'zhS2BRgKK', 							7, 2, 1, null);
insert into employees values (13, 'Julita', 'Turle', 'jturlec@nydailynews.com', '1O1MAihozv', 				5, 1, 2, null);
insert into employees values (14, 'Marni', 'Chesterman', 'mchestermand@phoca.cz', 'VAqvZNUMB', 				3, 2, 1, null);
insert into employees values (15, 'Donnell', 'Aristide', 'daristidee@is.gd', 'VNXIx0qgIjd5', 				4, 2, 2, 1473001260000);
insert into employees values (16, 'Dorise', 'Baskeyfied', 'dbaskeyfiedf@cargocollective.com', '3zhvDCkI', 	4, 2, 1, null);
insert into employees values (17, 'Mar', 'Sawdy', 'msawdyg@epa.gov', 'wYisOoyeFO', 							7, 2, 2, null);
insert into employees values (18, 'Rubie', 'Beacham', 'rbeachamh@blogs.com', '21A3avc', 					3, 2, 1, null);
insert into employees values (19, 'Roarke', 'Thickett', 'rthicketti@apple.com', 'JyD79Z', 					1, 3, 2, null);
insert into employees values (20, 'Ruggiero', 'Scatchard', 'rscatchardj@theglobeandmail.com', 'ShkhJQL', 	4, 2, 1, null);
insert into employees values (21, 'Maggi', 'McCadden', 'mmccaddenk@studiopress.com', 'cLvit3', 				7, 2, 2, null);
insert into employees values (22, 'Traci', 'Swigg', 'tswiggl@time.com', 'ACFu6KOAxM', 						3, 2, 1, null);
insert into employees values (23, 'Filip', 'Sumpton', 'fsumptonm@state.tx.us', 'm6w6f4suss9', 				4, 2, 2, null);
insert into employees values (24, 'Cyndi', 'Hanmer', 'chanmern@mac.com', 'bSfPzk11', 						7, 2, 1, null);
insert into employees values (25, 'Dory', 'Gossan', 'dgossano@cdbaby.com', 'C6cm3i', 						3, 2, 1, null);
insert into employees values (26, 'Templeton', 'Aslin', 'taslinp@arizona.edu', 'hHGPKaMll5Ab', 				4, 2, 2, null);
insert into employees values (27, 'Dolli', 'Jelley', 'djelleyq@typepad.com', 'hAQcZt0o', 					5, 1, 1, null);
insert into employees values (28, 'Carmita', 'Joutapavicius', 'cjoutapaviciusr@microsoft.com', 'qKilbOgK', 	7, 2, 2, null);
insert into employees values (29, 'Tiffi', 'Hambleton', 'thambletons@dot.gov', 'k6HlzCEK0', 				3, 2, 2, 1555076237000);
insert into employees values (30, 'Granville', 'Sharland', 'gsharlandt@nationalgeographic.com', '5nXC26', 	4, 2, 2, null);

update employees set ben_co_emp_id = 2 where id = 1;

update departments set dep_head_emp_id = 5 where id = 1;
update departments set dep_head_emp_id = 3 where id = 2;
update departments set dep_head_emp_id = 1 where id = 3;

insert into grading_formats values (1, 'Typical Letter Grade', 'Grades are A, B, C, D, and F. F is fail, D is lowest passing, A is highest passing', 'D');
insert into grading_formats values (2, 'Typical Numeric Grade', 'Grades are from 0 to 100. Less than 60 is fail.', '60');
insert into grading_formats values (3, 'Pass or Fail', 'Grades are either Pass or Fail', 'Pass');

insert into events values (1, 'Be a Benefits Coordinator', 1443892000000, '1234 Under a Rock Somewhere Dr. Phoenix, AZ 85282', 1100.55, 4, 1, 1446520000000);
insert into events values (2, 'All About Supervising', 1443892000000, '4321 Be Cool Guys Rd. Honolulu, HI 96795', 999.11, 5, 2, null);
insert into events values (3, 'Public Speaking', 1456673400000, '1337 Road Street Ln. Litte Rock, AR 72002', 200, 1, 3, null);
insert into events values (4, 'How to Eat Pie', 1555076237000, '911 Real Street Name Blv. Tokyo, JP', 9999.99, 2, 2, null);
insert into events values (5, 'Prepare for getting that certification', 1632063600000, '5134 Certification Place Atlanta, GA 12345', 200, 3, 3, 1632085200000);

insert into reimbursements values (1, 2, 1, 'I would like reimbursement for my benefits coordinator certification.', 1442682000000, null, 'B', 1000);
insert into reimbursements values (2, 4, 2, 'I am getting some supervisoral training.', 1442682000000, 40, '91', 899.20);
insert into reimbursements values (3, 10, 3, 'My public speaking needs improvement. I hope I can get reimbursed.', 1454859400000, null, 'pass', 160);
insert into reimbursements values (4, 7, 2, 'I heard I need to take this class to be a supervisor.', 1443892100000, null, null, null); -- submitted late so rejected
insert into reimbursements values (5, 29, 4, 'This is seminar is very important for my hard work here.', 1558193400000, 200, null, null); -- submitted by terminated

insert into messages values (1, 1, 'supervisor', 'approval', 1442768400000, 'Sure thing Pippa, have fun. -Melvyn');
insert into messages values (2, 1, 'ben co', 'approval', 1442768420000, 'Approving again as a ben co. -Melvyn');
insert into messages values (3, 2, 'supervisor', 'approval', 1442768400000, 'Break a leg. -Rozalin');
insert into messages values (4, 2, 'ben co', 'approval', 1442854800000, 'Everything looks good. -Melvyn');
insert into messages values (5, 3, 'supervisor', 'request', 1454945800000, 'Looks like you forgot to fill out something. -Hoyt');
insert into messages values (6, 3, 'supervisor', 'approval', 1455032200000, 'Approved! Thank you. -Hoyt');
insert into messages values (7, 3, 'dep head', 'approval', 1455118600000, 'Good -Rozalin');
insert into messages values (8, 3, 'ben co', 'approval', 1455205000000, 'Hope you do well! -Pippa');
insert into messages values (9, 4, 'supervisor', 'approval', 1443978500000, 'Good -Rozalin');
insert into messages values (10, 4, 'ben co', 'denial', 1444064900000, 'Rozalin must have missed that the class ended already! -Melvyn');
insert into messages values (11, 5, 'supervisor', 'denial', 1558279800000, 'Did you forget you were fired? Go away. -Rozalin');

insert into attachments values (1, 1, 'fakedatabucket.com/ai3214ijojdv2', 'Here is my grade.');
insert into attachments values (2, 2, 'fakedatabucket.com/fatij124314ij', 'Here is my presentation');
insert into attachments values (3, 3, 'fakedatabucket.com/3u4i508sdj204', 'This grade is okay right?');

alter sequence grading_formats_id_seq restart with 4;
alter sequence event_types_id_seq restart with 7;
alter sequence events_id_seq restart with 6;
alter sequence reimbursements_id_seq restart with 6;
alter sequence employees_id_seq restart with 31;
alter sequence departments_id_seq restart with 4;
alter sequence messages_id_seq restart with 12;
alter sequence attachments_id_seq restart with 4;



