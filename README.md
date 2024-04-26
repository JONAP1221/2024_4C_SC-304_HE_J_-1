
# Projecto de Estructura de Datos

Este proyecto tiene como objetivo desarrollar una página web similar a Twitter, donde los usuarios pueden tener seguidores y compartir sus "Tuits". Los seguidores podrán visualizar los mensajes de los usuarios a quienes siguen, y se permitirá responder a los tuits con un máximo de dos respuestas por mensaje.

## Autores

- Camerún Zuñiga Jimenez (https://github.com/Camerunzj)
- Jonathan López Garcia (https://github.com/JONAP1221)
- Brandon Vargas Moreira (https://github.com/Vargas0421)


## Plan de Proyecto

Cronograma de actividades:

- Clase Pila: Camerún Zuñiga
- Clase Grafo:  Brandon Vargas
- Clase ListaSimple: Brandon Vargas
- Clase ListaDobleCircular: Brandon Vargas
- Clase Usuario: Brandon Vargas
- Clase Post: Jonathan López
- Clase Arbol: Jonathan López
- Clase Cola:  Jonathan López
- Clase NodoArbol: Jonathan López
- Clase NodoCola: Camerún Zuñiga
- Clase NodoPila: Camerún Zuñiga
- Clase NodoListaSimple: Brandon Vargas
- Clase NodoListaDobleCircular: Brandon Vargas

  ## Modulos
  Módulo de Usuario:
 Responsable: Brandon Vargas.

- Este módulo permite la gestión de usuarios, incluyendo agregar, modificar y eliminar usuarios, así como establecer seguimientos hacia otros usuarios.
- Cada usuario debe proporcionar y almacenar su nombre, apellido, correo electrónico único y fecha de ingreso.
- La funcionalidad de seguimiento de usuarios se realizará a través del correo electrónico. Se verificará la existencia del usuario destino y se evitará seguir a la misma persona más de una vez.
- Al modificar usuarios, se permitirá cambiar todos los campos excepto el correo electrónico. Por lo tanto, cada correo electrónico debe ser único.
- La información de los usuarios, junto con sus seguimientos, se almacenará en archivos planos en formato CSV.

  Módulo de Creación de Mensajes y Respuestas:
 Responsable: Jonathan López.

- Este módulo se encarga de la creación de mensajes y la generación de respuestas a los mensajes existentes.
- Los usuarios podrán crear nuevos mensajes.
- También podrán generar respuestas a los mensajes previamente creados. Para propósitos del curso, se limitará a dos respuestas por mensaje. Cualquier intento de ingresar más de dos respuestas mostrará un mensaje de error.
- Toda la información de los mensajes y respuestas se almacenará en un archivo plano en formato CSV.

  Módulo de Visualización de Mensajes:
 Responsable: Camerún Zuñiga.

- Este módulo se encarga de presentar los mensajes en formato de "Feed".
- Los usuarios podrán seleccionar un usuario específico para visualizar su "Feed".
- El "Feed" se construirá utilizando una cola basada en una pila de mensajes, incluyendo tanto los propios como los de los usuarios seguidos. 
- La cola se vaciará al finalizar la visualización.
- Cada entrada en el "Feed" contendrá la fecha, el nombre de usuario que publicó el mensaje. Para las respuestas a comentarios, se incluirá un distintivo y se alinearán a la derecha.

## Librerias a Utilizar

Se planea emplear las bibliotecas de Swing para facilitar la interacción con el usuario mediante la presentación de mensajes a través de JoptionPane. Además, se considera la inclusión de paneles (Panels) para una mayor versatilidad en la interfaz.

java.util.ArrayList: Esta librería proporciona la implementación de la interfaz de lista en Java. La clase ArrayList es una implementación de la interfaz List que proporciona una estructura de datos dinámica para almacenar elementos en una secuencia ordenada. Permite agregar, eliminar, acceder y modificar elementos de la lista de manera eficiente.

java.time.LocalDateTime: Esta librería proporciona la funcionalidad para representar una fecha y hora local sin referencia a una zona horaria específica. La clase LocalDateTime permite trabajar con fechas y horas en un contexto local, sin considerar los ajustes de zona horaria o los desplazamientos.

java.time.format.DateTimeFormatter: Esta librería proporciona la funcionalidad para formatear y analizar fechas y horas en Java. La clase DateTimeFormatter permite crear objetos que definen patrones de formato para formatear instancias de LocalDateTime en cadenas de texto, así como analizar cadenas de texto en instancias de LocalDateTime.

java.io.BufferedWriter: Esta librería proporciona clases para escribir texto en un flujo de salida de manera eficiente, especialmente cuando se necesita escribir grandes cantidades de datos de forma secuencial. El BufferedWriter en particular ayuda a mejorar el rendimiento al almacenar temporalmente los datos en un búfer antes de escribirlos en el flujo subyacente.

java.io.FileWriter: Esta clase permite escribir caracteres en un archivo de texto. Es útil cuando necesitas escribir datos de texto en un archivo de manera sencilla.

java.io.BufferedReader: Proporciona una forma eficiente de leer texto de un flujo de entrada, como un archivo de texto. Al igual que BufferedWriter, BufferedReader ayuda a mejorar el rendimiento al almacenar temporalmente los datos leídos en un búfer, lo que reduce el número de accesos al sistema de archivos.

java.io.FileReader: Esta clase se utiliza para leer caracteres desde un archivo. Es una forma sencilla de leer datos de texto de un archivo de manera eficiente.

java.awt.Image: Esta clase representa una imagen en Java. Proporciona funcionalidades para cargar y manipular imágenes en aplicaciones de Java, especialmente en el contexto de interfaces gráficas de usuario (GUI) donde se necesitan imágenes para elementos visuales como botones, fondos, etc.

javax.swing.ImageIcon: Esta clase proporciona una forma conveniente de cargar imágenes desde varios tipos de recursos, como archivos en el sistema de archivos, URL o arrays de bytes. Es especialmente útil en aplicaciones de GUI Swing para mostrar imágenes en componentes como JLabel, JButton, etc.

java.text.ParseException: Esta excepción se utiliza para indicar que se ha producido un error durante el análisis (parsing) de una cadena en un formato específico. Es comúnmente utilizada con las clases de formato de fecha y hora en Java para manejar errores al convertir cadenas de texto en objetos Date u otros tipos de datos formateados.

java.text.SimpleDateFormat: Esta clase se utiliza para formatear y analizar fechas y horas según un patrón específico. Permite convertir objetos Date en cadenas de texto con un formato definido por el usuario, y viceversa. Es útil cuando necesitas trabajar con fechas y horas en diferentes formatos, como en aplicaciones de gestión de eventos, calendarios, etc.

java.util.Date: Esta clase representa un punto en el tiempo, con una precisión de milisegundos. Se utiliza para almacenar y manipular fechas y horas en Java. Sin embargo, esta clase ha sido reemplazada en gran medida por las clases del paquete java.time introducidas en Java 8, ya que ofrece una API más moderna y mejorada para el manejo de fechas y horas.

javax.swing.JComboBox: Es una clase específica dentro de javax.swing que representa un componente desplegable en una interfaz de usuario. Permite al usuario seleccionar una opción de una lista desplegable de elementos. Es útil para ofrecer opciones en forma de lista desplegable en aplicaciones Java.
