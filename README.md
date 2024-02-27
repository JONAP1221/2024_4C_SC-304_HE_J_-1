
# Projecto de Estructura de Datos

Este proyecto tiene como objetivo desarrollar una página web similar a Twitter, donde los usuarios pueden tener seguidores y compartir sus "Tuits". Los seguidores podrán visualizar los mensajes de los usuarios a quienes siguen, y se permitirá responder a los tuits con un máximo de dos respuestas por mensaje.

## Autores

- Camerún Zuñiga Jimenez (https://github.com/Camerunzj)
- Jonathan López Garcia (https://github.com/JONAP1221)
- Joshtine Masis Fuentes (https://github.com/Joshmasis16)
- Brandon Vargas Moreira (https://github.com/Vargas0421)


## Plan de Proyecto

Módulo de Usuario:
Responsable: Brandon Vargas.

- Este módulo permite la gestión de usuarios, incluyendo agregar, modificar y eliminar usuarios, así como establecer seguimientos hacia otros usuarios.
- Cada usuario debe proporcionar y almacenar su nombre, apellido, correo electrónico único y fecha de ingreso.
- La funcionalidad de seguimiento de usuarios se realizará a través del correo electrónico. Se verificará la existencia del usuario destino y se evitará seguir a la misma persona más de una vez.
- Al modificar usuarios, se permitirá cambiar todos los campos excepto el correo electrónico. Por lo tanto, cada correo electrónico debe ser único.
- La información de los usuarios, junto con sus seguimientos, se almacenará en archivos planos en formato CSV.

Módulo de Creación de Mensajes y Respuestas:
Responsable: Por definir.

- Este módulo se encarga de la creación de mensajes y la generación de respuestas a los mensajes existentes.
- Los usuarios podrán crear nuevos mensajes.
- También podrán generar respuestas a los mensajes previamente creados. Para propósitos del curso, se limitará a dos respuestas por mensaje. Cualquier intento de ingresar más de dos respuestas mostrará un mensaje de error.
- Toda la información de los mensajes y respuestas se almacenará en un archivo plano en formato CSV.

Módulo de Visualización de Mensajes:
Responsable: Por definir.

- Este módulo se encarga de presentar los mensajes en formato de "Feed".
- Los usuarios podrán seleccionar un usuario específico para visualizar su "Feed".
- El "Feed" se construirá utilizando una cola basada en una pila de mensajes, incluyendo tanto los propios como los de los usuarios seguidos. 
- La cola se vaciará al finalizar la visualización.
- Cada entrada en el "Feed" contendrá la fecha, el nombre de usuario que publicó el mensaje. Para las respuestas a comentarios, se incluirá un distintivo y se alinearán a la derecha.

## Librerias a Utilizar

Se planea emplear las bibliotecas de Swing para facilitar la interacción con el usuario mediante la presentación de mensajes a través de JoptionPane. Además, se considera la inclusión de paneles (Panels) para una mayor versatilidad en la interfaz.
