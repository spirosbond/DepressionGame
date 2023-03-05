### This is not a Game: [The Depression Game](http://depressionga.me)

## Docker files

In this folder you will see the docker file configuration for this project :computer:

- Under `core\` there is the core executable software that is cloned and used to corrupt itself.

- To build the docker image use the following command:

```shell
sudo docker build . --build-arg file=$f -t depressiongame:latest
```

- To run the container, I use `docker-compose` v.3.5. To start the container run:

```
sudo docker-compose up
```

- The `run-war.sh` is used to execute the .war file inside the container. Make sure the port used there matches the one in `docker-compose.yaml`.

Alternatively you can also use the `Makefile` which has some pre-programmed shortcuts, that help also deploying this to my VPS.

~Enjoy~
