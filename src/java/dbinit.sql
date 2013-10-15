CREATE TABLE eventmessage ( msgId bigint NOT NULL, event varchar(20)  NOT NULL, eventKey varchar(20) ) ;
CREATE TABLE imagemessage ( msgId bigint NOT NULL, picUrl varchar(500)  NOT NULL );
CREATE TABLE linkmessage ( msgId bigint NOT NULL, title varchar(200)  NOT NULL, description varchar(500)  NOT NULL, url varchar(300)  NOT NULL) ;
CREATE TABLE locationmessage ( msgId bigint NOT NULL, Location_X float NOT NULL, Location_Y float NOT NULL, Scale int NOT NULL, Label varchar(1024)  NOT NULL) ;
CREATE TABLE message ( toUserName varchar(50)  NOT NULL, fromUserName varchar(50)  NOT NULL, createTime bigint NOT NULL, msgType varchar(10)  NOT NULL, msgId bigint NOT NULL, postSign bit, funcFlag int, PRIMARY KEY (msgId) );
CREATE TABLE textmessage ( msgId bigint NOT NULL, content varchar(2048) );
CREATE TABLE voicemessage ( msgId bigint NOT NULL, mediaId varchar(200)  NOT NULL, format varchar(50)  NOT NULL );
