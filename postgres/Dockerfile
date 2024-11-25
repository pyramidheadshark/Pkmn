FROM postgres:latest

COPY init.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_USER=pkmn_user
RUN psql -c "CREATE USER $POSTGRES_USER WITH PASSWORD '$POSTGRES_PASSWORD';"

ENV POSTGRES_DB=pkmn_db
RUN psql -c "CREATE DATABASE $POSTGRES_DB;"

COPY init.sql /docker-entrypoint-initdb.d/

CMD ["postgres"]