# Capitole Consulting Tech Proof

## Arquitectura

Para la realización de está prueba técnica se ha utilizado Maven junto con el Framework de Spring y una base de datos PostgreSQL con los datos proporcionados en los ficheros CSV. Se ha utilizado un patrón DDD para estructurar el código de la aplicación.

Se ha utilizado Flyway para proporcionar la estructura de la base de datos inicial y además se ha añadido un 'Callback' para poder insertar los datos que vienen en los CSV, por lo que éstos serán insertados una vez se ejecute la aplicación de forma automática.

## Estructuras de datos utilizadas en el algoritmo

Las estructuras utilizadas para este algoritmo han sido 'Listas', debido, principalmente, a que se tienen que recorrer todos los elementos de éstas sin necesidad de que estén ordenadas.

## Complejidad temporal del algoritmo

Dado que la entidad 'Stock' mantiene una proporción 1:1 con la entidad de 'Size', la complejidad del algoritmo es de orden "O(n2)" dado que se tiene que comprobar todas las tallas de cada producto. Esto es debido, fundamentalmente, a las casuísticas especiales, que impiden realizar busquedas dicotómicas que reduzcan el orden del algoritmo.

Para poder reducir la complejidad del algoritmo, se me ocurre que la entidad de "Producto" contenga un campo que indique si tiene o no stock según los criterios del enunciado. Cada vez que una talla de un producto sea actualizada, se tendría que verificar dicho campo y establecer si tiene stock o no. De esta forma se podría reducir la complejidad del algoritmo a orden "O(n)" puesto que solamente se recorerría la lista de productos.

## Pre-requisitos

Este proyecto require que se tenga instalado Maven, JDK 17, Docker Engine y Docker Compose para poder ejecutarlo según las instrucciones de Instalación.

Para realizar pruebas, se recomiendo utilizar un cliente REST tipo Postman, o en su lugar, se tendrá que utilizar CURL.

## Instalación

Una vez instalado el software requerido, primeramente se procederá con la ejecución de los contenedores Docker necesarios.

Para ello, se abrira un terminal o consola y nos colocaremos sobre la carpeta "<REPO_BASE_PATH>/docker-compose"

```
cd <REPO_BASE_PATH>/docker-compose
docker-compose.exe -f docker-compose.yml pull
docker-compose.exe -f docker-compose.yml -p capitoleconsulting-techproof up -d
```

Una vez realizado este paso, se habrá creado una instancia de PostgreSQL con las bases de datos y usuario necesarios para la ejecución.

A continuación se procedera a compilar, crear ejecutables y pasar los tests unitarios. Para ello se utilizará Maven con los siguientes comandos:

```
cd <REPO_BASE_PATH>
mvn clean package
```

Una vez terminado este proceso, se habrá generado un archivo *.jar ejecutable el cual se tendrá que lanzar de la siguiente manera:

```
cd <REPO_BASE_PATH>/target
java -jar X.jar
```

Esperar hasta que el servidor arranque por completo. Eso será cuando se muestre el siguiente mensaje en consola:

```
Started TechProofApplication in X.YYY seconds (process running for X.YY)
```

## Pruebas

Para realizar las pruebas será neceario utilizar CURL o algún cliente REST tipo Postman. La request se realizará de la siguiente manera:

```
GET: http://localhost:8080/capitoleconsulting-inditex/products
```

Una vez ejecutada la request, devolverá un 200 con los identificadores de los productos ordenados según su secuencia:

```
[
    5,
    1,
    3
]
```