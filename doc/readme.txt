yum install lvm2
yum install docker
rpm -ivh mysql57-community-release-el7-7.noarch.rpm
yum install mysql-community-server

service mysqld start
service mysqld status

≤Èø¥≥ı ºroot√‹¬Î
grep 'temporary password' /var/log/mysqld.log

mysql -uroot -p 

ALTER USER 'root'@'localhost' IDENTIFIED BY 'Qy123456<@>';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'  IDENTIFIED BY 'Qy123456<@>'  WITH GRANT OPTION;


http://112.74.109.243:8080/codegen4-0.0.1/