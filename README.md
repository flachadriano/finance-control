# Finance Control

Application developed to Android and Web

The idea is to use rails as server side of this application.

Developed with Ruby on Rails 4.2.4

And develop the interface with some javascript frameworks

GWT, AngularJS, EmberJS...

# Screens (Prototype - Web)

developed with http://lumzy.com/app/

!https://raw.github.com/flachadriano/finance-control/master/web/prototypes/portuguese/cadastros-adicionar.jpg!
!https://raw.github.com/flachadriano/finance-control/master/web/prototypes/portuguese/cadastros-listar.jpg!
!https://raw.github.com/flachadriano/finance-control/master/web/prototypes/portuguese/lancamentos.jpg!
!https://raw.github.com/flachadriano/finance-control/master/web/prototypes/portuguese/relatorios.jpg!

# How to work through Cloud9

## Create user on database

Based on http://www.tynecastle.nl/blog/2014/11/05/setup-postgresql-on-cloud9

Start the Postgresql
$ sudo service postgresql start

Access the postgresql terminal
$ sudo sudo -u postgres psql

Create the user in the database
$ # create user "username" with password 'password';

Grant the user
$ # alter user "username" superuser;

Create the databases
$ # CREATE DATABASE project_test WITH OWNER "username";
$ # CREATE DATABASE project_development WITH OWNER "username";

## When restart the workspace

Start the Postgresql
$ sudo service postgresql start

Run the project
$ rails s -b $IP -p $PORT