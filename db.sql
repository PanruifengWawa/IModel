create table user(
id serial primary key,
user_name varchar(30),
password varchar(30),
token varchar(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table models(
id serial primary key,
model_name varchar(30),
algorithm varchar(30),
state int,
file_dir varchar(300),
file_name varchar(300),
created_time datetime,
published int,
model_size int,
model_input text,
model_target text,
app_key text,
user_id bigint(20) unsigned not null,
foreign key(user_id) references user(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table records(
id serial primary key,
app_key text,
date date,
count int default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table model_records(
id serial primary key,
app_key text,
date date,
ip varchar(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop trigger if exists goodsTrigger;
delimiter || 
create trigger model_records_trigger after insert on model_reocrds
for each row 
begin 
	declare one_record_id bigint(20) unsigned not null;
	select id from records where app_key = new.app_key and date=CURRENT_DATE into one_record_id;
	if one_record_id is null then 
		inset into records(app_key,date,count) values(new.app_key,CURRENT_DATE,1);
	else
		update records set count=count+1 where id = one_record_id;
	end if;
	
end ||
delimiter ;