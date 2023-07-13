# Quinto-Challenge


## Como ejecutar:
- Correr el comando `docker compose up` en la carpeta db.

Una vez ejecutado el comando, se iniciara la db a partir de una imagen docker
y se ejecutara con un volume asociado, que corresponde al archivo data.sql.
Este ultimo se encarga de levantar la tabla users e instertar datos de prueba en ella.

- Ejecutar `./mvnw spring-boot:run`  desde la terminal en la raiz del proyecto.



## Pendientes:
- Implementar SpringSecurity para la Autorizacion y la Autenticacion.
- Implementar rutas de Login y Register
- Implementar Thymeleaf para generar las vistas.
- Realizar test unitarios para verificar el correcto funcionamiento.
