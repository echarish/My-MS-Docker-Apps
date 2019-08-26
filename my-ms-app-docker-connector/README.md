### Creating a Network of Containers


Easiest way is to use --link, however the newer versions of docker are moving away from that and in fact that switch will be removed soon.

The link below offers a nice how too, on connecting two containers. You can skip the attach portion, since that is just a useful how to on adding items to images.

https://deis.com/blog/2016/connecting-docker-containers-1/

The part you are interested in is the communication between two containers. The easiest way, is to refer to the DB container by name from the webserver container.

Example:

you named the db container DB1 and the webserver container WEB0. The containers should both be on the bridge network, which means the web container should be able to connect to the DB container by referring to it's name.

So if you have a web config file for your app, then for DB host you will use the name DB1.

if you are using an older version of docker, then you should use --link.

Example:

Step 1: docker run --name db1 oracle/database:12.1.0.2-ee

then when you start the web app. use:

Step 2: docker run --name web0 --link db1 webapp/webapp:3.0

and the web app will be linked to the DB. However, as I said the --link switch will be removed soon.

I'd use docker compose instead, which will build a network for you. However; you will need to download docker compose for your system. https://docs.docker.com/compose/install/#prerequisites

an example setup is like this:

file name is base.yml

```
version: "2"
services:
  webserver:
    image: "moodlehq/moodle-php-apache:7.1
    depends_on:
      - db
    volumes:
      - "/var/www/html:/var/www/html"
      - "/home/some_user/web/apache2_faildumps.conf:/etc/apache2/conf-enabled/apache2_faildumps.conf"
    environment:
      MOODLE_DOCKER_DBTYPE: pgsql
      MOODLE_DOCKER_DBNAME: moodle
      MOODLE_DOCKER_DBUSER: moodle
      MOODLE_DOCKER_DBPASS: "m@0dl3ing"
      HTTP_PROXY: "${HTTP_PROXY}"
      HTTPS_PROXY: "${HTTPS_PROXY}"
      NO_PROXY: "${NO_PROXY}"
  db:
    image: postgres:9
    environment:
      POSTGRES_USER: moodle
      POSTGRES_PASSWORD: "m@0dl3ing"
      POSTGRES_DB: moodle
      HTTP_PROXY: "${HTTP_PROXY}"
      HTTPS_PROXY: "${HTTPS_PROXY}"
      NO_PROXY: "${NO_PROXY}"
```

this will name the network a generic name, I can't remember off the top of my head what that name is, unless you use the --name switch.

IE docker-compose --name setup1 up base.yml

NOTE: if you use the --name switch, you will need to use it when ever calling docker compose, so docker-compose --name setup1 down this is so you can have more then one instance of webserver and db, and in this case, so docker compose knows what instance you want to run commands against; and also so you can have more then one running at once. Great for CI/CD, if you are running test in parallel on the same server.

Docker compose also has the same commands as docker so docker-compose --name setup1 exec webserver do_some_command

best part is, if you want to change db's or something like that for unit test you can include an additional .yml file to the up command and it will overwrite any items with similar names, I think of it as a key=>value replacement.

Example:

db.yml

```
version: "2"
services:
  webserver:
    environment:
      MOODLE_DOCKER_DBTYPE: oci
      MOODLE_DOCKER_DBNAME: XE
  db:
    image: moodlehq/moodle-db-oracle
```

Then call docker-compose --name setup1 up base.yml db.yml

This will overwrite the db. with a different setup. When needing to connect to these services from each container, you use the name set under service, in this case, webserver and db.

I think this might actually be a more useful setup in your case. Since you can set all the variables you need in the yml files and just run the command for docker compose when you need them started. So a more start it and forget it setup.

NOTE: I did not use the --port command, since exposing the ports is not needed for container->container communication. It is needed only if you want the host to connect to the container, or application from outside of the host. If you expose the port, then the port is open to all communication that the host allows. So exposing web on port 80 is the same as starting a webserver on the physical host and will allow outside connections, if the host allows it. Also, if you are wanting to run more then one web app at once, for whatever reason, then exposing port 80 will prevent you from running additional webapps if you try exposing on that port as well. So, for CI/CD it is best to not expose ports at all, and if using docker compose with the --name switch, all containers will be on their own network so they wont collide. So you will pretty much have a container of containers.

UPDATE: After using features further and seeing how others have done it for CICD programs like Jenkins. Network is also a viable solution.

Example:

docker network create test_network
The above command will create a "test_network" which you can attach other containers too. Which is made easy with the --network switch operator.

Example:

```
docker run \
    --detach \
    --name DB1 \
    --network test_network \
    -e MYSQL_ROOT_PASSWORD="${DBPASS}" \
    -e MYSQL_DATABASE="${DBNAME}" \
    -e MYSQL_USER="${DBUSER}" \
    -e MYSQL_PASSWORD="${DBPASS}" \
    --tmpfs /var/lib/mysql:rw \
    mysql:5
```

Of course, if you have proxy network settings you should still pass those into the containers using the "-e" or "--env-file" switch statements. So the container can communicate with the internet. Docker says the proxy settings should be absorbed by the container in the newer versions of docker; however, I still pass them in as an act of habit. This is the replacement for the "--link" switch which is going away. Once the containers are attached to the network you created you can still refer to those containers from other containers using the 'name' of the container. Per the example above that would be DB1. You just have to make sure all containers are connected to the same network, and you are good to go.

For a detailed example of using network in a cicd pipeline, you can refer to this link: https://git.in.moodle.com/integration/nightlyscripts/blob/master/runner/master/run.sh

Which is the script that is ran in Jenkins for a huge integration tests for Moodle, but the idea/example can be used anywhere. I hope this helps others.