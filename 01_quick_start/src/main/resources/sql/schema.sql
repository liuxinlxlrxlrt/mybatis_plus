DROP TABLE IF EXISTS tbl_employee;
create table tbl_employee(
                             id int(11) primary key not null unique auto_increment,
                             last_name varchar(50),
                             email varchar(50),
                             gender char(1),
                             age varchar(50)
)ENGINE=innodb DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;