-- create sequence
drop sequence seq_gallery;

create sequence seq_gallery
start with 1
increment by 1
maxvalue 9999999999;

insert into gallery values(seq_gallery.nextval, '업로드 테스트', 'www');

select no, comments, image_url as imageURL from gallery;