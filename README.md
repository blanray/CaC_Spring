# CaC_Spring
CrudPB Codo a Codo - Spring

Se basa en dos tablas: productos y tipos de productos, relacionadas por el id de tipo de producto.
Como el proyecto lo empece mucho antes de ver algunos temas relacionados estrictamente con JPA, esta mas orientado a la BBDD que al objeto, pero implementa
todo el repositorio de JPA.
Tiene dos controladores, dos servicios y dos repositorios uno por cada tabla/entidad/Dto con las correspondientes validaciones, mapeos, etc.
Las consignas particulares eran:

   - Mostrar los 5 productos con mayor stock ordenados por tipo.
     Consultar según un determinado tipo de producto una lista con el precio ordenado de mayor a menor.

Todos los EP y los metodos de los servicios estan probados con test unitarios y test de integracion, que al dia de hoy me dan 84% de coverage sobre el total de la api.
Los test se hacen en H2 y la BBDD se implementa en mysql.
Se adjunta esquema exportado de workbench.

- Update 29/12/22: Se agrega JWT implementado con tablas en MySQL y repositorio JPA con relacion Many to Many.
                  Se actualizaron todos los EP para jugar con los tres perfiles de acceso en JWT, otorgando ciertos EP a cada perfil.
                  Se actualizan los archivos de application properties para que se configuren las tablas productivas de MySQL con datos y las tablas de test en H2 con datos de prueba.
                  Se agrega test para el EP de login.
                  

