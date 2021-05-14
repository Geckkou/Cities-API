# Cities API

## Requisitos

* Linux
* Git
* Java 8
* Docker
* IntelliJ Community
* Heroku CLI
* Travis CLI

## DataBase

### Postgres
##### Rode esse container.
```shell script
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### Inserindo dados no Postgres.

* [data](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

```shell script
cd ~/workspace/sql-paises-estados-cidades/PostgreSQL

docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash

psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql
psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql
psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql

psql -h localhost -U postgres_user_city cities

CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

### Acesso

```shell script
docker exec -it cities-db /bin/bash

psql -U postgres_user_city cities
```

### Query Calculo de distância

Point
```roomsql
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

Cube
```roomsql
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;
```


## Heroku

* [DevCenter](https://devcenter.heroku.com/articles/getting-started-with-gradle-on-heroku)

```shell script
heroku create dio-cities-api --addons=heroku-postgresql
```

#### extra
+ [Heroku Deployment](https://docs.travis-ci.com/user/deployment/heroku/)
+ [Minha Api no Heroku/cidades](https://lit-springs-36460.herokuapp.com/cities)
+  [Minha Api no Heroku/estados](https://lit-springs-36460.herokuapp.com/states)
+  [Minha Api no Heroku/paises](https://lit-springs-36460.herokuapp.com/countries)
+ [Calculo entre duas cidades / Na url em "from" e "to" é so trocar os ids dos lugares](https://lit-springs-36460.herokuapp.com/distances/by-points?from=4929&to=5254)