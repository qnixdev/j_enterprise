# Java enterprise course

## Configuration and Usage

Run the following command in project dir to install app:

Copy `.env.sample` as `.env`:
```sh
cp ./task_management_system/docker/.env.sample ./task_management_system/docker/.env
```
> Note: `modify .env` If you need to change settings.

Init & run application:
```sh
make init
```

To see all make commands run:
```sh
make help
```