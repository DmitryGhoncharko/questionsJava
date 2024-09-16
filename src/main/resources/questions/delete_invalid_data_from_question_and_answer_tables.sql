select count(answer_id) from  answer where answer_body isnull or answer_body='';
select count(question_id) from question where question_body isnull or question_body='';;
delete from answer where answer_body isnull or answer_body='';
delete from question where question_body isnull or question_body='';
select count(answer_id) from answer;
select count(question_id) from question;
delete from question where not exists(
    select from answer
           where question.question_id=answer.question_id
);