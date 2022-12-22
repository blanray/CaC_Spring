# CaC_Spring
CrudPB Codo a Codo - Spring

Se basa en dos tablas: productos y tipos de productos, relacionadas por el id de tipo de producto.
Como el proyecto lo empece mucho antes de ver algunos temas relacionados estrictamente con JPA, esta mas orientado a la BBDD que al objeto, pero implementa
todo el repositorio de JPA.
Tiene dos controladores, dos servicios y dos repositorios uno por cada tabla/entidad/Dto con las correspondientes validaciones, mapeos, etc.
Todos los EP y los metodos de los servicios estan probados con test unitarios y test de integracion, que al dia de hoy me dan 84% de coverage sobre el total de la api.
Los test se hacen en H2 y la BBDD se implementa en mysql.
Se adjunta esquema exportado de workbench.

