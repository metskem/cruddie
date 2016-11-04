insert into appservergroup(stage,name) values('t','tl53');
insert into appservergroup(stage,name) values('t','tl99');
insert into appservergroup(stage,name) values('t','al14');
insert into appservergroup(stage,name) values('t','pl02');

insert into appserver(hostname,jvmargs,location,lastchangedby,lastchanged,created,appservergroupid) values('lsrv4711','-Xms500M', 'Best','metskeh',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),(select appservergroupid from appservergroup where name='tl53'));
insert into appserver(hostname,jvmargs,location,lastchangedby,lastchanged,created,appservergroupid) values('lsrv4712','-Xms500M -Djavax.net.debug=true', 'Boxtel','troijenc',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),(select appservergroupid from appservergroup where name='tl53'));
insert into appserver(hostname,jvmargs,location,lastchangedby,lastchanged,created,appservergroupid) values('lsrv2313','-Xms500M -Dwhatever', 'Boxtel','troijenc',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),(select appservergroupid from appservergroup where name='pl02'));
