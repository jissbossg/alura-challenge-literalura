# Aplicacion Liter Alura en Java & Spring

<image src="https://aprende.goodneighbors.cl/wp-content/uploads/2022/02/ONE_logo_rgb-768x408.png" alt="Logo Alura Latam + Oracle"/>

# 

###  Este proyecto, desarrollado en **Java** y **Spring**, permite **consultar libros y autores** mediante el consumo de la API gratuita de libros **[Gutendex](https://gutendex.com/)**. La aplicación cuenta con un menú interactivo que ofrece diversas opciones para que el usuario pueda realizar búsquedas de manera eficiente.

### Cada libro consultado se guarda localmente en una base de datos **PostgreSQL**, donde se almacenan tanto los detalles del libro como la información del autor. Esto permite realizar consultas posteriores a la base de datos, incluso sin necesidad de acceder a la API nuevamente. 

# 

## Tabla de Contenidos
[//]: # (Este comentario no se verá en el archivo cuando se visualice en GitHub)

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Contacto](#contacto)

# 

## Características

- **Consumo de la API:** El proyecto utiliza la API gratuita de Gutendex para obtener información sobre libros y autores. A través de solicitudes HTTP, la aplicación consume datos de la API, que proporciona información como títulos de libros, nombres de autores, años de publicación, idiomas, entre otros. La conexión con la API se realiza de manera eficiente, utilizando métodos estándar para manejar las respuestas y garantizar que los datos obtenidos sean precisos y actualizados.
- **Manejo de entradas no válidas:** El programa solicita nuevamente la entrada si el usuario ingresa un valor incorrecto.
- **Análisis de la Respuesta JSON:** Una vez realizada la solicitud a la API, la respuesta se recibe en formato JSON. La aplicación analiza esta respuesta utilizando bibliotecas de Java, como Jackson o Gson, para convertir los datos JSON en objetos Java que puedan ser fácilmente manipulados. El análisis de la respuesta JSON permite extraer información relevante, como el título de los libros, los autores, las fechas de publicación y otros metadatos, que luego se utilizan en las consultas o se almacenan en la base de datos.
- **Inserción y consulta en la base de datos:** os datos obtenidos de la API se almacenan en una base de datos local PostgreSQL para su posterior consulta. Cada vez que el usuario realiza una búsqueda, el sistema verifica si la información ya está presente en la base de datos. Si no es así, se realiza una nueva consulta a la API y los resultados se insertan en la base de datos. Esto asegura que las búsquedas futuras sean rápidas, sin necesidad de hacer múltiples consultas a la API. La inserción y consulta de los datos se realiza mediante sentencias SQL eficientes y con el uso de un ORM (como JPA o Hibernate) o consultas directas con JDBC.
- **Exhibición de resultados a los usuarios:** Una vez que se han obtenido y procesado los datos, los resultados se presentan al usuario de manera clara y organizada. El sistema muestra la información relevante en una interfaz de usuario interactiva, utilizando un menú que permite acceder a las distintas funcionalidades, como la búsqueda de libros, autores, y la visualización de libros por idioma o por año. Los resultados se presentan en un formato legible, con detalles como el título, autor, año de publicación y otros metadatos importantes. El diseño de la interfaz se realiza para ser sencillo y accesible, permitiendo una experiencia de usuario fluida y eficiente.

# 

## Requisitos

- **Java Development Kit (JDK)**: versión 17 o superior.
- **Spring Framework**: Versión mínima recomendada: Spring Boot 2.5.x o superior (idealmente, la versión más reciente disponible).
- **Conexión a Internet**: Necesaria para acceder a la API.
- **Maven**: Para gestionar las dependencias del proyecto.
- **PostgreSQL**: versión 12 o superior.
- **Librerias Java**:
    - **Gson o Jackson**: JSON Processing (para analizar la respuesta JSON de la API).
    - **JDBC o JPA/Hibernate**: para la interacción con la base de datos PostgreSQL.
    - **PostgreSQL JDBC Driver**: controlador JDBC de PostgreSQL.
- **Dependencias Spring Framework**:
    - **spring-boot-starter-data-jpa**: Para trabajar con Spring Data JPA y facilitar la interacción con la base de datos.
    - **spring-boot-starter-test**: Para pruebas unitarias y de integración con JUnit, Mockito, y otras herramientas de testing.
    - **spring-boot-maven-plugin**: Para permitir la construcción y ejecución de aplicaciones Spring Boot a través de Maven.
# 

## Instalación

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/jissbossg/alura-challenge-literalura.git
   ```
# 

## Uso

### El sistema inicia con un saludo
```bash
¡Bienvenidos a la aplicación LiterluAra!
Iniciando sistema......
```
### Segidamente se muestran las opciones disponibles y solicita seleccionar una para poder continuar
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Seleccion de opcion 1.
```bash
1
```
### Solicita ingresar el nombre del libro a buscar
```bash
Escriba el nombre del libro:
Peter Pan
```
### Retorna los datos arojados por la api y a su vez registra los mismos en la base de datos local
```bash
Titulo: Peter Pan
Autor: Barrie, J. M. (James Matthew)
Idioma: EN
Número de descargas: 7768
```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 2. para listar los libros que se encuentran ya registrados en la base de datos
```bash
2
```
### Muestra los datos de los libros consultados y registrados anteriormente en la base de datos
```bash
Libros Registrados
___________________________________________________________________________
___________________________________________________________________________

 Titulo: Letters of a Javanese Princess
 Autor: Kartini, Raden Adjeng
 Idioma: EN
 Número de descargas: 1228
___________________________________________________________________________

 Titulo: The Merry Adventures of Robin Hood
 Autor: Pyle, Howard
 Idioma: EN
 Número de descargas: 2424
___________________________________________________________________________

 Titulo: The War of the Worlds
 Autor: Wells, H. G. (Herbert George)
 Idioma: EN
 Número de descargas: 6138
___________________________________________________________________________

 Titulo: La Odisea
 Autor: Homer
 Idioma: ES
 Número de descargas: 3312
___________________________________________________________________________

 Titulo: Frankenstein; Or, The Modern Prometheus
 Autor: Shelley, Mary Wollstonecraft
 Idioma: EN
 Número de descargas: 78467
___________________________________________________________________________

 Titulo: Don Quijote
 Autor: Cervantes Saavedra, Miguel de
 Idioma: ES
 Número de descargas: 12877
___________________________________________________________________________

 Titulo: The Ruins; Or, Meditation on the Revolutions of Empires and the Law of Nature
 Autor: Volney, C.-F. (Constantin-François)
 Idioma: EN
 Número de descargas: 527
___________________________________________________________________________

 Titulo: Grimms' Fairy Tales
 Autor: Grimm, Jacob
 Idioma: EN
 Número de descargas: 17728
___________________________________________________________________________

 Titulo: Moby Dick; Or, The Whale
 Autor: Melville, Herman
 Idioma: EN
 Número de descargas: 74858
___________________________________________________________________________

 Titulo: Alice's Adventures in Wonderland
 Autor: Carroll, Lewis
 Idioma: EN
 Número de descargas: 51356
___________________________________________________________________________

 Titulo: Little Women; Or, Meg, Jo, Beth, and Amy
 Autor: Alcott, Louisa May
 Idioma: EN
 Número de descargas: 50288
___________________________________________________________________________

 Titulo: Peter Pan
 Autor: Barrie, J. M. (James Matthew)
 Idioma: EN
 Número de descargas: 7768
___________________________________________________________________________
``` 
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 3. para buscar un autor por su nombre
```bash
3
```
### Solicita ingresar el nombre del autor
```bash
Escribe el nombre del autor que deseas buscar: 
Barrie
```
### Muestra los datos del autor
```bash
Nombre: Barrie, J. M. (James Matthew)
Fecha de Nacimiento: 1860
Fecha de Fallecimiento: 1937
```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 4. para listar los autores que se encuentran ya registrados
```bash
4
```
### Muestra los datos de los autores consultados y registrados anteriormente en la base de datos
```bash
Autores Registrados
___________________________________________________________________________
___________________________________________________________________________

 Nombre: Kartini, Raden Adjeng
 Fecha de Nacimiento: 1879
 Fecha de Fallecimiento: 1904
 Libros: [Letters of a Javanese Princess]
___________________________________________________________________________

 Nombre: Pyle, Howard
 Fecha de Nacimiento: 1853
 Fecha de Fallecimiento: 1911
 Libros: [The Merry Adventures of Robin Hood]
___________________________________________________________________________

 Nombre: Wells, H. G. (Herbert George)
 Fecha de Nacimiento: 1866
 Fecha de Fallecimiento: 1946
 Libros: [The War of the Worlds]
___________________________________________________________________________

 Nombre: Homer
 Fecha de Nacimiento: -750
 Fecha de Fallecimiento: -650
 Libros: [La Odisea]
___________________________________________________________________________

 Nombre: Shelley, Mary Wollstonecraft
 Fecha de Nacimiento: 1797
 Fecha de Fallecimiento: 1851
 Libros: [Frankenstein; Or, The Modern Prometheus]
___________________________________________________________________________

 Nombre: Cervantes Saavedra, Miguel de
 Fecha de Nacimiento: 1547
 Fecha de Fallecimiento: 1616
 Libros: [Don Quijote]
___________________________________________________________________________

 Nombre: Volney, C.-F. (Constantin-François)
 Fecha de Nacimiento: 1757
 Fecha de Fallecimiento: 1820
 Libros: [The Ruins; Or, Meditation on the Revolutions of Empires and the Law of Nature]
___________________________________________________________________________

 Nombre: Grimm, Jacob
 Fecha de Nacimiento: 1785
 Fecha de Fallecimiento: 1863
 Libros: [Grimms' Fairy Tales]
___________________________________________________________________________

 Nombre: Melville, Herman
 Fecha de Nacimiento: 1819
 Fecha de Fallecimiento: 1891
 Libros: [Moby Dick; Or, The Whale]
___________________________________________________________________________

 Nombre: Carroll, Lewis
 Fecha de Nacimiento: 1832
 Fecha de Fallecimiento: 1898
 Libros: [Alice's Adventures in Wonderland]
___________________________________________________________________________

 Nombre: Alcott, Louisa May
 Fecha de Nacimiento: 1832
 Fecha de Fallecimiento: 1888
 Libros: [Little Women; Or, Meg, Jo, Beth, and Amy]
___________________________________________________________________________

 Nombre: Barrie, J. M. (James Matthew)
 Fecha de Nacimiento: 1860
 Fecha de Fallecimiento: 1937
 Libros: [Peter Pan]
___________________________________________________________________________
```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 5. para listar los autores con vida en un año
```bash
5
```
### Pide ingresar el año que se desea consultar
```bash
Ingrese el año vivo del autor(es) que desea buscar:
1900 
```
### Muestra los datos de los autores correpondientes a año solicitado
```bash
___________________________________________________________________________
Autores vivos para el año: 1900
___________________________________________________________________________

 Nombre: Kartini, Raden Adjeng
 Fecha de Nacimiento: 1879
 Fecha de Fallecimiento: 1904
 Libros: [Letters of a Javanese Princess]
___________________________________________________________________________

 Nombre: Pyle, Howard
 Fecha de Nacimiento: 1853
 Fecha de Fallecimiento: 1911
 Libros: [The Merry Adventures of Robin Hood]
___________________________________________________________________________

 Nombre: Wells, H. G. (Herbert George)
 Fecha de Nacimiento: 1866
 Fecha de Fallecimiento: 1946
 Libros: [The War of the Worlds]
___________________________________________________________________________

 Nombre: Barrie, J. M. (James Matthew)
 Fecha de Nacimiento: 1860
 Fecha de Fallecimiento: 1937
 Libros: [Peter Pan]
___________________________________________________________________________

```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 6. para lsitar los libros por idioma
```bash
6
```
### Pide ingresar el idioma  aconsultar las opciones validas de busqueda para esta consulta son:
- **es / español**
- **en / ingles**
- **fr / frances**
- **pt / portuges**
- **la / latin**
- **it / italiano**
```bash
es
```
```bash
español
```
### Muestra los datos de los libros en el idioma solicitado
```bash
___________________________________________________________________________
Listado de libros por idioma: ES - ÉSPAÑOL
___________________________________________________________________________
___________________________________________________________________________

 Titulo: La Odisea
 Autor: Homer
 Idioma: ES
 Número de descargas: 3312
___________________________________________________________________________

 Titulo: Don Quijote
 Autor: Cervantes Saavedra, Miguel de
 Idioma: ES
 Número de descargas: 12877
___________________________________________________________________________
```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Se escoje la opcion 7. para consultar los 10 libros mas descargados de la paltaforma **[Project Gutenberg eBooks](https://www.gutenberg.org/)**
```bash
7
```
### Muestra lso datos del top 10 de libros mas descargados
```bash
___________________________________________________________________________
Top 10 libros más descargados
___________________________________________________________________________
1. Titulo de libro: Frankenstein; Or, The Modern Prometheus
    Numero de descargas: 174761
___________________________________________________________________________
2. Titulo de libro: Moby Dick; Or, The Whale
    Numero de descargas: 74858
___________________________________________________________________________
3. Titulo de libro: Romeo and Juliet
    Numero de descargas: 62300
___________________________________________________________________________
4. Titulo de libro: Pride and Prejudice
    Numero de descargas: 58812
___________________________________________________________________________
5. Titulo de libro: A Room with a View
    Numero de descargas: 51907
___________________________________________________________________________
6. Titulo de libro: Alice's Adventures in Wonderland
    Numero de descargas: 51356
___________________________________________________________________________
7. Titulo de libro: Little Women; Or, Meg, Jo, Beth, and Amy
    Numero de descargas: 50288
___________________________________________________________________________
8. Titulo de libro: The Scarlet Letter
    Numero de descargas: 45002
___________________________________________________________________________
9. Titulo de libro: The Enchanted April
    Numero de descargas: 44077
___________________________________________________________________________
10. Titulo de libro: The Adventures of Ferdinand Count Fathom — Complete
    Numero de descargas: 41549
___________________________________________________________________________
```
### Muestra el menu nuevanente para que el usuario siga interactuando con la aplicacion
```bash
Menú
Escribe el número de la opción que quieres hacer.
1). Buscar libro por título
2). Listar libros registrados
3). Buscar autor por nombre
4). Listar autores registrados
5). Listar autores vivos en un determinado año
6). Listar libros por idioma
7). Top 10 libros más descargados
0). Salir
```
### Seleccion de opcion 0.
```bash
0
```
### Con la seleccion de esta opcion el sistema se finaliza mostrando un mensaje de agradecimiento y se finaliza el sistema
```bash
¡Gracias por usar LiterluAra!...
Saliendo...
```

# 

## Contacto
[LinkedIn](https://www.linkedin.com/in/jissbossg)
[Links](https://linktr.ee/jissbossg)