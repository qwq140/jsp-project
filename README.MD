# JSP 프로젝트 항공권 예매 사이트

## 환경

- window10
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- lombok
- gson
- git
- 인코딩 utf-8

## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'flightuser'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'flightuser'@'%';
create database flightdb;
```

## MySQL 테이블 생성

```sql
CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    name varchar(20) not null,
    email varchar(100) not null,
    phone varchar(100),
    role varchar(20),
    createDate timestamp
)engine=InnoDB default charset=utf8;

CREATE TABLE book(
	id int primary key auto_increment,
    userId int not null,
    personnel int not null,
    depAirportNm varchar(20) not null,
    arrAirportNm varchar(20) not null,
    depPlandTime varchar(20) not null,
    arrPlandTime varchar(20) not null,
    vihicleId varchar(20) not null,
    grade varchar(20) not null,
    charge int not null,
    FOREIGN KEY (userId) REFERENCES user (id) on update cascade on delete cascade
)engine=InnoDB default charset=utf8;
```