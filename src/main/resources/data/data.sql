create table student
(
    id   int primary key auto_increment,
    name varchar(50),
    score double
);

insert into student (name, score) VALUE ('Lương', 3);
insert into student (name, score) VALUE ('Thiên', 3.5);
insert into student (name, score) VALUE ('Trường', 2.5);

select id, name, score from student;

select id, name, score from student where id = 1;

insert into student (name, score) VALUE (?, ?);


SELECT * FROM student WHERE (:name IS NULL OR name LIKE concat('%', :name, '%'))
                 AND (:fromScore IS NULL OR score >= :fromScore)
                      AND (:toScore IS NULL OR score <= :toScore )