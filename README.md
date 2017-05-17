# never-forget
Task Manager for the forgetful folk

Setup database

Enter the mysql console with

<code>
mysql -u springuser -p
</code>

Create the database as named in <path>application.properties</path>:

<code>
create database db_example; -- Create the new database
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
</code>





##LocalDateTime/LocalDate/LocalTime

Thanks to the following dependency, all LocalDateTime, LocalDate and LocalTime can be stored al non-blobs in the database. Hibernate required! No need for additional AttributeConverter like eg. http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/

<code>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-java8</artifactId>
    <version>5.0.0.Final</version>
</dependency>
</code>