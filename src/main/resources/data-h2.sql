insert into appservergroup(stage,name,created,lastchanged) values('t','tl53',current_timestamp,current_timestamp);
insert into appservergroup(stage,name,created,lastchanged) values('t','tl99',current_timestamp,current_timestamp);
insert into appservergroup(stage,name,created,lastchanged) values('t','al14',current_timestamp,current_timestamp);
insert into appservergroup(stage,name,created,lastchanged) values('t','pl02',current_timestamp,current_timestamp);

insert into appserver(hostname,jvmargs,location,lastchangedby,appservergroupid,created,lastchanged) values('lsrv4711','-Xms500M', 'Best','metskeh',(select id from appservergroup where name='tl53'),current_timestamp,current_timestamp);
insert into appserver(hostname,jvmargs,location,lastchangedby,appservergroupid,created,lastchanged) values('lsrv4712','-Xms500M -Djavax.net.debug=true', 'Boxtel','troijenc',(select id from appservergroup where name='tl53'),current_timestamp,current_timestamp);
insert into appserver(hostname,jvmargs,location,lastchangedby,appservergroupid,created,lastchanged) values('lsrv2313','-Xms500M -Dwhatever', 'Boxtel','troijenc',(select id from appservergroup where name='pl02'),current_timestamp,current_timestamp);
