# JFerence
This project is focused on recreating the [Coreference](https://github.com/markovd18/Coreference) web-app with Java backend using Spring Boot and React frontend.

## DB connection
This project is designed to run with connected PostgreSQL database. To run your local PostgreSQL database, 
you need to install Docker first. Then run 
`docker run --name jference-db -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=jference-db -d postgres
` from your terminal. This command runs new container with name `jference-db` and exposes 
its internal port `5432` to the same port in the host system. It sets the default super-user 
password with the environment variable `POSTGRES_PASSWORD` and ensures that database with the name `jference-db` is created 
with `-e POSTGRES_DB=jference-db`. If this database is already present in your container, it is not overwritten.


