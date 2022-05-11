insert into speaker(id, name)
values (1, 'Gleb Akkuratnov');

insert into speaker(id, name)
values (2, 'Cool Designer');

insert into speaker(id, name)
values (3, 'Nice Tester');

insert into talk(id, name, speaker_id)
values (1, 'java-junior-starter: Spring Data', 1);

insert into talk(id, name, speaker_id)
values (2, 'java-junior-starter: Hibernate', 1);

insert into talk(id, name, speaker_id)
values (3, 'java-junior-starter: Testing', 1);

insert into talk(id, name, speaker_id)
values (4, 'How to test something', 3);

insert into talk(id, name, speaker_id)
values (5, 'Design advices', 2);

insert into talk(id, name, speaker_id)
values (6, 'Make a bad looks good', 2);

insert into guest (id, name)
values (1, 'Sanyok');

insert into guest (id, name)
values (2, 'Mizhgan');

insert into guest (id, name)
values (3, 'Mashulya');

insert into guest (id, name)
values (4, 'Vovan');

insert into guest (id, name)
values (5, 'Lisa');

insert into guest_talk(talk_id, guest_id) values (1, 1);
insert into guest_talk(talk_id, guest_id) values (1, 3);
insert into guest_talk(talk_id, guest_id) values (1, 5);
insert into guest_talk(talk_id, guest_id) values (3, 4);
insert into guest_talk(talk_id, guest_id) values (4, 5);
insert into guest_talk(talk_id, guest_id) values (4, 2);
insert into guest_talk(talk_id, guest_id) values (5, 5);
insert into guest_talk(talk_id, guest_id) values (5, 4);
insert into guest_talk(talk_id, guest_id) values (5, 3);
