# Introduction

The Work Order Storage service is responsible for persisting into PostgreSQL, entities that reside within the Work Order domain (i.e. work orders, work order templates, and work order dependencies). The Work Order Storage service is also responsible for servicing requests for data from the Work Order domain (e.g. from API services).

## Install a PostgreSQL docker container. 

- Creates a database named `postgres` with username `postgres` and password `docker`. 
- Data will be persisted in `$HOME/docker/volumes/postgres`

```bash
docker pull postgres
mkdir -p $HOME/docker/volumes/postgres
docker run --rm --name pg -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
```

- Run the migration

```bash
sbt flywayMigrate
```
