# Introduction

The Work Order Storage service is responsible for persisting into PostgreSQL, entities that reside within the Work Order domain (i.e. work orders, work order templates, and work order dependencies). The Work Order Storage service is also responsible for servicing requests for data from the Work Order domain (e.g. from API services).

## Install a PostgreSQL docker container. 

See detailed instructions [here](https://hub.docker.com/_/postgres).

- Creates a database named `postgres` with username `postgres` and password `docker`. 
- Data will be persisted in `$HOME/docker/volumes/postgres`

```bash
mkdir -p $HOME/docker/volumes/postgres
docker run --rm --name pg -e POSTGRES_PASSWORD=docker -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
```

- Run the migration

```bash
sbt flywayMigrate
```

## References

- [HikariCP](https://github.com/brettwooldridge/HikariCP)
- [CRUD Repositories for Slick based persistence Scala projects](https://github.com/gonmarques/slick-repo)
- [Slick Extensions for PostgreSQL](https://github.com/tminglei/slick-pg)
- [Handling Postgres json datatype in slick, scala](https://stackoverflow.com/a/56917169/6015856)
- [Flyway](https://flywaydb.org/)
- [Flyway Migration Javadoc](https://flywaydb.org/documentation/api/javadoc/org/flywaydb/core/Flyway)
- [Simple Slick example](https://github.com/adekunleba/sample-slick)
- [Postgres.app](https://postgresapp.com/)
- [Creating user, database and adding access on PostgreSQL](https://medium.com/coding-blocks/creating-user-database-and-adding-access-on-postgresql-8bfcd2f4a91e)
- [How to Change a User to Superuser in PostgreSQL](https://chartio.com/resources/tutorials/how-to-change-a-user-to-superuser-in-postgresql/)
- [PostGis for Postgres on MacOS](https://stackoverflow.com/a/20974997/6015856)
- [Some time utilities](https://github.com/skygoo/breaker/blob/47075860731cbee28a0953cab4a8eb44b5b681a6/backend/src/main/scala/com/neo/sk/utils/TimeUtil.scala)
