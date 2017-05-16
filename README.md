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
