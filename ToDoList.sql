CREATE TABLE ToDo_List(
	TaskID int not null auto_increment,
    Date date not null,
    Name varchar(100) not null,
    Description varchar(200) not null,
    Priority varchar(25) not null,
    Status varchar(15),
    PRIMARY KEY (TaskID)
);

INSERT INTO ToDo_List (Date, Name, Description, Priority, Status) VALUES ('2022-07-09', 'Java', 'To code a webshop', 'High', 'Pending');

SELECT * FROM ToDo_List;

UPDATE ToDo_List SET Status = '' WHERE TaskID = 1;

SELECT Description FROM ToDo_List WHERE Status = 'Pending';

DELETE FROM ToDo_List WHERE TaskID = 2;

-- DROP TABLE IF EXISTS ToDo_List;
