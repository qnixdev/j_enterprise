help:
	@echo ""
	@echo "usage: make COMMAND"
	@echo ""
	@echo "Commands:"
	@echo "  init                Create containers"
	@echo "  up                  Up containers"
	@echo "  ps                  Show containers info"
	@echo "  exec                Exec to app container"
	@echo "  stop                Stop containers"
	@echo "  rm                  Remove containers"

init:
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env up -d --build

up:
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env up -d

ps:
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env ps

exec:
	docker exec -it -u 1000:1000 tms-app sh

stop:
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env stop -t0

rm:
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env stop -t0
	docker compose -f ./task_management_system/docker-compose.yaml --env-file ./task_management_system/docker/.env rm -f
