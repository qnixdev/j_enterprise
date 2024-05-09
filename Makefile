module = kafka_broker_system
#module = task_management_system
#module = security_system

help:
	@echo ""
	@echo "usage: make COMMAND"
	@echo ""
	@echo "Commands:"
	@echo "  init                Create containers and network"
	@echo "  up                  Up containers"
	@echo "  ps                  Show containers info"
	@echo "  stop                Stop containers"
	@echo "  rm                  Remove containers"
	@echo "  log                 Show logs of containers"

init:
	mvn -f ./$(module) install -DskipTests
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env up -d --build

up:
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env up -d

ps:
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env ps

stop:
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env stop -t0

rm:
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env stop -t0
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env rm -f
	mvn -f ./$(module) clean

log:
	docker compose -f ./$(module)/docker-compose.yaml --env-file ./$(module)/docker/.env logs
