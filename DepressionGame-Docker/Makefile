.PHONY: *

#ex. make deploy-prod f=./standalone/prod/CoolEngineer-me-0.2-emb.war
deploy-prod:
	echo "Deploying-prod $f..."
	-ssh root@194.233.161.25 'mv /root/depressiongame/*.war /root/depressiongame/archive/'
	scp $f root@194.233.161.25:/root/depressiongame/
	ssh root@194.233.161.25 'cd /root/depressiongame/ && make build f=$(notdir $f)'
# 	$(MAKE) restart-docker-compose

# ex. run: make build f=CoolEngineer-me-0.2-emb.war
build:
	echo "Building $f..."
	sudo docker build . --build-arg file=$f -t depressiongame:latest

#ex. make remote-build-prod
remote-build:
	echo "Remote-building-prod..."
	scp ./Dockerfile ./Makefile ./run-war.sh root@194.233.161.25:/root/depressiongame/
	ssh root@194.233.161.25 'cd /root/depressiongame/ && make build f=*.war'

#ex. make remote-build-docker-compose
remote-build-docker-compose:
	echo "Remote-building docker-compose..."
	scp ./docker-compose.yaml root@194.233.161.25:/root/depressiongame/
	ssh root@194.233.161.25 'cd /root/depressiongame/ && docker-compose up -d'

#ex. make remote-restart-docker
remote-restart-docker:
	echo "Restarting docker..."
	ssh root@194.233.161.25 'systemctl restart docker && cd /root/depressiongame/ && docker-compose up -d'

#ex. make remote-restart-docker-compose
remote-restart-docker-compose:
	echo "Restarting docker-compose..."
	ssh root@194.233.161.25 'cd /root/depressiongame/ && docker-compose up -d'