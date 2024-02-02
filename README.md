
# Prueba Técnica

Artefacto que permite la creacion de usuarios

https://github.com/chidalgogalvez/javatest-spring-boot





## Tech Stack

**Server:** Java 11, maven, spring boot, H2 data base, Hibernate


## API Reference

#### all items

```http
  POST /user/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name`    | `string` | **Required**. user name    |
| `email`   | `string` | **Required**. user email   |
| `password`| `string` | **Required**. user password|
| `phones`  | `list`   | **Required**. phone list   |
| `number`      | `string`   | **Required**. phone number     |
| `citycode`    | `string`   | **Required**. phone citycode   |
| `countrycode` | `string`   | **Required**. phone country    |


```http
  GET /user/{uuid}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `uuid`    | `UUID`   | **Required**. user Id      |


```http
  PUT /user/{uuid}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `uuid`    | `UUID`   | **Required**. user Id      |
| `name`    | `string` | **Required**. user name    |
| `email`   | `string` | **Required**. user email   |
| `password`| `string` | **Required**. user password|
| `isActive`| `string` | **Required**. user state   |
| `phones`  | `list`   | **Required**. phone list   |
| `number`      | `string`   | **Required**. phone number     |
| `citycode`    | `string`   | **Required**. phone citycode   |
| `countrycode` | `string`   | **Required**. phone country    |


```http
  DELETE /user/{uuid}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `uuid`    | `UUID`   | **Required**. user Id      |



```http
  GET /user/prueba/
```



## Deployment

Para implementar este proyecto, ejecute

```bash
  mvn spring-boot:run
```


## Authors

- [@chidalgogalvez](https://www.github.com/chidalgogalvez)


## Aditional Features

Las credenciales de acceso a la DB se encuentran en el archivo de configuración (application.yml)

- http://localhost:8080/h2-console/
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/actuator
- Implementación de Looger
- Wrapper con estructura de respuestas de los servicios "success and error"
- Implementación de ControllerExceptionHandler
- Implementacion de MapStruct para mapeo de datos
- Arquitectura basado en capas: controller -> service -> repository -> entity
- Implementació de Collections de postman ruta src-main-resources-postman



## 🚀 About Me
I'm a full stack developer...

