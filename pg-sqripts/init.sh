#!/bin/bash

set -e

set -u
echo "*********Creating user and databases"
psql -v ON_ERROR_STOP=1 --username "root" <<-EOSQL

create user root with login nosuperuser nocreatedb nocreaterole inherit noreplication password '1243';

		  CREATE DATABASE spring-security;

      \c auth
      alter default privileges for role "root" in schema public grant select, insert, update, delete on tables to "spring-security";
      alter default privileges for role "root" in schema public grant select, usage on sequences to "spring-security";


EOSQL
echo "*********Completed user and databases"