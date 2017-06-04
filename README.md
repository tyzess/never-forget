# never-forget
Task Manager for the forgetful folk

## Open tasks

* Don't use hardcoded db-username, db-password and db-name
* Automate docker deployment
* Add testing
* Implement better authentication
* ......

## How to run

### Setup with docker

First start the MySQL container and set the correct username, password, and database-name as stated in springs application.properties. E.g:
~~~
docker run --name never-forget-mysql -e MYSQL_ROOT_PASSWORD=ThePassword -e MYSQL_DATABASE=db_example -e MYSQL_USER=springuser -e MYSQL_PASSWORD=ThePassword -d mysql:5.6
~~~

Once the MySQL container is up and running (may take up to 1 minute), start the spring application with the following command:
~~~
docker run -p 8080:8080 --name never-forget --link never-forget-mysql:mysql -d uzysset/never-forget:latest
~~~

### Setup without docker

1. Install mysql server
2. Create a user called 'springuser' with the password 'ThePassword'
3. Login with
~~~
mysql -u springuser -p
~~~

4. Create the database as named in <path>application.properties</path> with
~~~
create database db_example; -- Create the new database
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
~~~
5. Run the NeverForgetApplication.java

## Misc/Notes

### LocalDateTime / LocalDate / LocalTime

Thanks to the following dependency, all LocalDateTime, LocalDate and LocalTime can be stored al non-blobs in the database. Hibernate required! No need for additional AttributeConverter like eg. http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/

<code>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-java8</artifactId>
    <version>5.0.0.Final</version>
</dependency>
</code>

