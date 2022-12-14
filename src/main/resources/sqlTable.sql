CREATE SCHEMA `webproject` ;

create table webproject.user(
                                id varchar(20) primary key not null,
                                password varchar(20) not null,
                                phone varchar(13) not null,
                                name varchar(10) not null,
                                nickname varchar(10) not null
);

create table webproject.userProfileImage(
                                            profileImageNumber int primary key not null auto_increment,
                                            userId varchar(20),
                                            serverFilePath varchar(500),
                                            userUploadFileName varchar(100),
                                            foreign key(userId) references user(id) on delete cascade
);

create table webproject.video(
                                 videoNumber int primary key not null auto_increment,
                                 videoName varchar(30) not null,
                                 uploadUser varchar(20) not null,
                                 videoText varchar(1000) default '',
                                 uploadTime datetime default now(),
                                 genere varchar(20) not null,

                                 foreign key(uploadUser) references user(id) on delete cascade
);

create table webproject.videoFile(
                                     fileNumber int primary key not null auto_increment,
                                     videoNumber int not null,
                                     serverFilePath varchar(500) not null,
                                     userUploadFileName varchar(100) default '',

                                     foreign key(videoNumber) references video(videoNumber) on delete cascade
);

create table webproject.music(
                                 musicNumber int not null primary key auto_increment,
                                 musicName varchar(30) not null,
                                 uploadUser varchar(20) not null,
                                 musicText varchar(1000) default '',
                                 uploadTime datetime default now(),
                                 genere varchar(20),
                                 lyrics varchar(2000) default '',
                                 songwriter varchar(10) default '',
                                 lyricwriter varchar(10) default '',
                                 musicArranger varchar(10) default '',
                                 singer varchar(10) not null,
                                 releaseDate datetime not null,

                                 foreign key(uploadUser) references user(id) on delete cascade
);

create table webproject.musicFile(
                                     fileNumber int primary key not null auto_increment,
                                     musicNumber int not null,
                                     serverFilePath varchar(500) not null,
                                     userUploadFileName varchar(100) default '',

                                     foreign key(musicNumber) references music(musicNumber) on delete cascade
);

create table webproject.musicImage(
                                      imageNumber int not null primary key auto_increment,
                                      musicNumber int not null,
                                      serverFilePath varchar(500) not null,
                                      foreign key(musicNumber) references music(musicNumber) on delete cascade
);

create table webproject.videoImage(
                                      imageNumber int not null primary key auto_increment,
                                      videoNumber int not null,
                                      serverFilePath varchar(500) not null,
                                      foreign key(videoNumber) references video(videoNumber) on delete cascade
);

create table webproject.board(
                                 boardNumber int not null primary key auto_increment,
                                 writer varchar(20) not null,
                                 title varchar(100) not null,
                                 content varchar(1000) not null,
                                 category varchar(10) default "free",
                                 foreign key (writer) references user(id)
);

create table webproject.boardImage(
                                      imageNumber int not null primary key auto_increment,
                                      boardNumber int not null,
                                      serverFilePath varchar(500) not null,
                                      foreign key (boardNumber) references board(boardNumber)
);

create table webproject.boardComment(
                             boardCommentNumber int primary key not null auto_increment,
                             writer varchar(20) not null,
                             text varchar(1000) not null,
                             writeDate datetime default now(),
                             boardNumber int not null,
                             foreign key (writer) references user(id) on delete cascade,
                             foreign key (boardNumber) references board(boardNumber) on delete cascade
);

create table webproject.videoComment(
                             videoCommentNumber int primary key not null auto_increment,
                             writer varchar(20) not null,
                             text varchar(1000) not null,
                             writeDate datetime default now(),
                             videoNumber int not null,
                             foreign key (writer) references user(id) on delete cascade,
                             foreign key (videoNumber) references video(videoNumber) on delete cascade
);

create table webproject.musicComment(
                             musicCommentNumber int primary key not null auto_increment,
                             writer varchar(20) not null,
                             text varchar(1000) not null,
                             writeDate datetime default now(),
                             musicNumber int not null,
                             foreign key (writer) references user(id) on delete cascade,
                             foreign key (musicNumber) references music(musicNumber) on delete cascade
);