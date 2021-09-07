
drop table if exists messages cascade;
drop table if exists attachments cascade;
drop table if exists grading_formats cascade;
drop table if exists event_types cascade;
drop table if exists events cascade;
drop table if exists reimbursements cascade;
drop table if exists employees cascade;

create table employees (
    id                      serial primary key,
    first_name              varchar(30),
    last_name               varchar(30),
    email                   varchar(50) unique,
    password                varchar(30),
    supervisor_emp_id       integer null references employees(id) on delete set null,
    dep_head_emp_id         integer null references employees(id) on delete set null,
    ben_co_emp_id           integer null references employees(id) on delete set null,
    termination_time        bigint null
);

--create table departments (
--  id                  serial primary key,
--  name                varchar(50),
--  description         varchar(200) null,
--  dep_head_emp_id     integer null references employees(id) on delete set null
--);

--alter table employees add constraint employees_department_id_fkey
--  foreign key (department_id) references departments(id) on delete set null;

create table event_types (
    id                  serial primary key,
    type_name           varchar(255),
    percent_coverage    numeric(3)
);

create table grading_formats (
    id                      serial primary key,
    format_name             varchar(255),
    description             varchar(255) null,
    passing_grade_cutoff    varchar(255) null
);

create table events (
    id                  serial primary key,
    event_name          varchar(255),
    start_time          bigint,
    location            varchar(255),
    tuition             numeric(8,2),
    event_type_id       integer null references event_types(id) on delete set null,
    grading_format_id   integer null references grading_formats(id) on delete set null,
    end_time            bigint null
);

create table reimbursements (
    id                  serial primary key,
    employee_id         integer references employees(id) on delete set null,
    event_id            integer references events(id) on delete set null,
    description         varchar(255),
    submission_time     bigint,
    hours_missed        numeric(8,2) null,
    approval_step       int default 0, -- -1 Denied, 0 Submitted, 1 Supervisor Appr, 2 Dep_Head Appr, 3 Ben_Co Appr, 4 Grade Submitted, 5 Reimbursed
    final_grade         varchar(255) null,
    actual_claim        numeric(6,2) null
);

create table attachments (
    id                  serial primary key,
    reimbursement_id    integer references reimbursements(id) on delete set null,
    attachment_url      varchar(255),
    description         varchar(255)
);

create table messages (
    id                  serial primary key,
    reimbursement_id    integer references reimbursements(id) on delete set null,
    approver_type       varchar(255),
    message_type        varchar(255),
    time_sent           bigint,
    message             varchar(255)
);

insert into event_types values (default, 'University Course', 80);                --1
insert into event_types values (default, 'Seminar', 60);                          --2
insert into event_types values (default, 'Certification Preparation Class', 75);  --3
insert into event_types values (default, 'Certification', 100);                   --4
insert into event_types values (default, 'Technical Training', 90);               --5
insert into event_types values (default, 'Other', 30);                            --6

--insert into departments values (1, 'Services', 'Janitorial and clerk staff', null);
--insert into departments values (2, 'Engineering', 'Bulk of this IT company', null);
--insert into departments values (3, 'Human Resources', 'Includes benefits coordinators', null);  -- 1=>5 2=>3 3=>1
                                                                                                --supervisor_emp_id, department_head_id, ben_co_emp_id, termination
insert into employees values (default, 'Melvyn', 'Tippetts', 'mtippetts0@gmpg.org', '6GEXmZSP',                 null, 1, null, null);   --1 ben co head
insert into employees values (default, 'Pippa', 'Tasseler', 'ptasseler1@technorati.com', 'nIPt3pNB',            1, 1, 1, null);         --2 second ben co
insert into employees values (default, 'Rozalin', 'Golson', 'rgolson2@dropbox.com', 'Ke4GS5qop7WH',             null, 3, 1, null);      --3 IT head
insert into employees values (default, 'Hoyt', 'Donovan', 'hdonovan3@businessinsider.com', 'rWUM01OQX',         3, 3, 1, null);         --4 IT supervisor
insert into employees values (default, 'Meara', 'Quenell', 'mquenell4@simplemachines.org', 'SY7hSLJb',          null, 5, 1, null);      --5 services head
insert into employees values (default, 'Benny', 'Barnewell', 'bbarnewell5@yandex.ru', 'aA8vR7c',                3, 3, 1, 1443888000000);--6 terminated IT
insert into employees values (default, 'Grethel', 'Valois', 'gvalois6@technorati.com', 'YVkWvB51',              3, 3, 1, null);         --7 IT supervisor
insert into employees values (default, 'Devi', 'Eglise', 'deglise7@ebay.co.uk', 'fbSH2AC',                      5, 5, 1, null);         --8
insert into employees values (default, 'Tracee', 'Baumler', 'tbaumler8@amazon.co.uk', '7Fw892E5lED',            4, 3, 2, null);         --9
insert into employees values (default, 'Wallache', 'Marousek', 'wmarousek9@springer.com', 'ZWcVaZOXi',          4, 3, 2, null);         --10
insert into employees values (default, 'Donavon', 'Aireton', 'dairetona@msu.edu', 'rmzugDS9s02',                1, 1, 2, null);         --11
insert into employees values (default, 'Joleen', 'Rope', 'jropeb@nba.com', 'zhS2BRgKK',                         7, 3, 1, null);         --12
insert into employees values (default, 'Julita', 'Turle', 'jturlec@nydailynews.com', '1O1MAihozv',              5, 5, 2, null);         --13
insert into employees values (default, 'Marni', 'Chesterman', 'mchestermand@phoca.cz', 'VAqvZNUMB',             3, 3, 1, null);         --14
insert into employees values (default, 'Donnell', 'Aristide', 'daristidee@is.gd', 'VNXIx0qgIjd5',               4, 3, 2, 1473001260000);--15
insert into employees values (default, 'Dorise', 'Baskeyfied', 'dbaskeyfiedf@cargocollective.com', '3zhvDCkI',  4, 3, 1, null);         --16
insert into employees values (default, 'Mar', 'Sawdy', 'msawdyg@epa.gov', 'wYisOoyeFO',                         7, 3, 2, null);         --17
insert into employees values (default, 'Rubie', 'Beacham', 'rbeachamh@blogs.com', '21A3avc',                    3, 3, 1, null);         --18
insert into employees values (default, 'Roarke', 'Thickett', 'rthicketti@apple.com', 'JyD79Z',                  1, 1, 2, null);         --19
insert into employees values (default, 'Ruggiero', 'Scatchard', 'rscatchardj@theglobeandmail.com', 'ShkhJQL',   4, 3, 1, null);         --20
insert into employees values (default, 'Maggi', 'McCadden', 'mmccaddenk@studiopress.com', 'cLvit3',             7, 3, 2, null);         --21
insert into employees values (default, 'Traci', 'Swigg', 'tswiggl@time.com', 'ACFu6KOAxM',                      3, 3, 1, null);         --22
insert into employees values (default, 'Filip', 'Sumpton', 'fsumptonm@state.tx.us', 'm6w6f4suss9',              4, 3, 2, null);         --23
insert into employees values (default, 'Cyndi', 'Hanmer', 'chanmern@mac.com', 'bSfPzk11',                       7, 3, 1, null);         --24
insert into employees values (default, 'Dory', 'Gossan', 'dgossano@cdbaby.com', 'C6cm3i',                       3, 3, 1, null);         --25
insert into employees values (default, 'Templeton', 'Aslin', 'taslinp@arizona.edu', 'hHGPKaMll5Ab',             4, 3, 2, null);         --26
insert into employees values (default, 'Dolli', 'Jelley', 'djelleyq@typepad.com', 'hAQcZt0o',                   5, 5, 1, null);         --27
insert into employees values (default, 'Carmita', 'Joutapavicius', 'cjoutapaviciusr@microsoft.com', 'qKilbOgK', 7, 3, 2, null);         --28
insert into employees values (default, 'Tiffi', 'Hambleton', 'thambletons@dot.gov', 'k6HlzCEK0',                3, 3, 2, 1555076237000);--29
insert into employees values (default, 'Granville', 'Sharland', 'gsharlandt@nationalgeographic.com', '5nXC26',  4, 3, 2, null);        --30

update employees set ben_co_emp_id = 2 where id = 1;

insert into grading_formats values (default, 'Typical Letter Grade', 'Grades are A, B, C, D, and F. F is fail, D is lowest passing, A is highest passing', 'D');--1
insert into grading_formats values (default, 'Typical Numeric Grade', 'Grades are from 0 to 100. Less than 60 is fail.', '60');                                 --2
insert into grading_formats values (default, 'Pass or Fail', 'Grades are either Pass or Fail', 'Pass');                                                         --3

insert into events values (default, 'Be a Benefits Coordinator', 1443892000000, '1234 Under a Rock Somewhere Dr. Phoenix, AZ 85282', 1100.55, 4, 1, 1446520000000);--1
insert into events values (default, 'All About Supervising', 1443892000000, '4321 Be Cool Guys Rd. Honolulu, HI 96795', 999.11, 5, 2, null);--2
insert into events values (default, 'Public Speaking', 1456673400000, '1337 Road Street Ln. Litte Rock, AR 72002', 200, 1, 3, null);        --3
insert into events values (default, 'How to Eat Pie', 1555076237000, '911 Real Street Name Blv. Tokyo, JP', 9999.99, 2, 2, null);           --4
insert into events values (default, 'Prepare for getting that certification', 1632063600000, '5134 Certification Place Atlanta, GA 12345', 200, 3, 3, 1632085200000);--5

insert into reimbursements values (default, 2, 1, 'I would like reimbursement for my benefits coordinator certification.', 1442682000000, null, 5, 'B', 1000);--1
insert into reimbursements values (default, 4, 2, 'I am getting some supervisoral training.', 1442682000000, 40, 5, '91', 899.20);                            --2
insert into reimbursements values (default, 10, 3, 'My public speaking needs improvement. I hope I can get reimbursed.', 1454859400000, null, 5, 'Pass', 160);--3
insert into reimbursements values (default, 7, 2, 'I heard I need to take this class to be a supervisor.', 1443892100000, null, -1, null, null);              --4 submitted late so rejected
insert into reimbursements values (default, 29, 4, 'This is seminar is very important for my hard work here.', 1558193400000, 200, -1, null, null);           --5 submitted by terminated
insert into reimbursements values (default, 2, 5, 'There is a certification that I want to take preparation classes for.', 1627550700000, 40, 0, null, null); --6
insert into reimbursements values (default, 12, 5, 'I want to try this.', 1627550700000, 40, 2, null, null);                                                   --7

insert into messages values (default, 1, 'Supervisor', 'Approval', 1442768400000, 'Sure thing Pippa, have fun. -Melvyn');                 --1
insert into messages values (default, 1, 'Benefits Coordinator', 'Approval', 1442768420000, 'Approving again as a ben co. -Melvyn');      --2
insert into messages values (default, 2, 'Supervisor', 'Approval', 1442768400000, 'Break a leg. -Rozalin');                               --3
insert into messages values (default, 2, 'Benefits Coordinator', 'Approval', 1442854800000, 'Everything looks good. -Melvyn');            --4
insert into messages values (default, 3, 'Supervisor', 'Request', 1454945800000, 'Looks like you forgot to fill out something. -Hoyt');   --5
insert into messages values (default, 3, 'Supervisor', 'Approval', 1455032200000, 'Approved! Thank you. -Hoyt');                          --6
insert into messages values (default, 3, 'Department Head', 'Approval', 1455118600000, 'Good -Rozalin');                                  --7
insert into messages values (default, 3, 'Benefits Coordinator', 'Approval', 1455205000000, 'Hope you do well! -Pippa');                  --8
insert into messages values (default, 4, 'Supervisor', 'Approval', 1443978500000, 'Good -Rozalin');                                       --9
insert into messages values (default, 4, 'Benefits Coordinator', 'Denial', 1444064900000, 'Rozalin must have missed that the class ended already! -Melvyn');--10
insert into messages values (default, 5, 'Supervisor', 'Denial', 1558279800000, 'Did you forget you were fired? Go away. -Rozalin');     --11
insert into messages values (default, 7, 'Supervisor', 'Approval', 1628308800000, 'You got this!');                                      --12
insert into messages values (default, 3, 'Department Head', 'Approval', 1628395200000, 'Yes!');                                          --13

insert into attachments values (default, 1, 'fakedatabucket.com/ai3214ijojdv2', 'Here is my grade.');         --1
insert into attachments values (default, 2, 'fakedatabucket.com/fatij124314ij', 'Here is my presentation');   --2
insert into attachments values (default, 3, 'fakedatabucket.com/3u4i508sdj204', 'This grade is okay right?'); --3

