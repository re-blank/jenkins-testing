FROM postgres
ENV POSTGRES_USER httpdb
ENV POSTGRES_PASSWORD httpdb
ADD *.sql /docker-entrypoint-initdb.d/
EXPOSE 5432