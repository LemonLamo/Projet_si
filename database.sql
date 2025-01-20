insert into editor values (0,'johnsi@gmail.com','john','si',1000,'physics');

insert into conference values (0,'2025-12-10','2025-12-15','OPEN_FOR_SUBMISSIONS','physics','play2die',0);

insert into author values(0,'author1@gmail.com','joe','goldberg',2000,'litterature');
insert into author values(1,'author2@gmail.com','peach','salinger',2001,'fashion');
insert into author values(2,'author3@gmail.com','natalie','engler',2002,'finance');
insert into author values(3,'author4@gmail.com','rhys','montrose',2003,'medecine');
insert into author values(4,'author5@gmail.com','ellie','alves',2004,'politics');

insert into submission values(0,'how to do stuff','path','smth smth',0);
insert into submission values(1,'guide to be si','path','smth smth',0);
insert into submission values(2,'si the question to existance','path','smth smth',0);
insert into submission values(3,'why does gl exist','path','smth smth',0);
insert into submission values(4,'miv and why not','path','smth smth',0);

insert into submission_author values(0,0);
insert into submission_author values(1,4);
insert into submission_author values(2,1);
insert into submission_author values(3,2);
insert into submission_author values(4,3);

insert into evaluator values(0,'eval0@gmail.com','george','washington',2000,'politics');
insert into evaluator values(1,'eval1@gmail.com','sabrina ','carpenter',2000,'music');
insert into evaluator values(2,'eval2@gmail.com','john ','nolan',2000,'LAPD');
insert into evaluator values(3,'eval3@gmail.com','tim ','bradford',2000,'LAPD');
insert into evaluator values(4,'eval4@gmail.com','lopez','best',2000,'Fashion');


insert into submission_evaluator values(0,0);
insert into submission_evaluator values(0,4);
insert into submission_evaluator values(1,2);
insert into submission_evaluator values(2,1);
insert into submission_evaluator values(3,2);
insert into submission_evaluator values(4,4);

insert into evaluation values(0,'need more omf',5 ,'IN_REVISION',0,0,0);
insert into evaluation values(1,'smart comment',10,'ACCEPTED',0,4,0);
insert into evaluation values(2,'thats not how physics work',1,'REJECTED',0,2,1);
insert into evaluation values(3,'the retrospective on the meme economie is well written',7,'ACCEPTED',0,1,2);
insert into evaluation values(4,'how can a person write this type of garbage',0,'REJECTED',0,2,3);
insert into evaluation values(5,'im a big fan',3,'REJECTED',0,4,4);
