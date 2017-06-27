JAVAC=jikes +E
#JAVAC=javac

PATH=$PATH:/usr/local/jdk*
CLASSES_DIR = ../WEB-INF/classes

JAR=jar
#JAR=fastjar
#JAR=/usr/local/linux-jdk1.2/bin/jar

all:	html war

html:	public_services.html logged_in_services.html

public_services.html logged_in_services.html: MkServices.class services.txt
		java MkServices
MkServices.class: src/MkServices.java
		$(JAVAC) src/MkServices.java

war:
		$(JAR) -cvmf Manifest /tmp/jabadot.war *

dump:
		pg_dump -d jabadot > dump.sql

restore:
		(
		echo 'drop table "users";
		echo 'drop table "categories";'
		echo 'drop table "submissions";'
		echo 'drop sequence "submissions_id_seq";'
		cat dump.sql
		) | psql -d jabadot
