# Descripcion general
Este proyecto tiene como fin el poder consumir una api de monedas y poder hacer conversiones con los datos que se proporcionan
- inicio que es donde sucede toda la interaccion de codigo usuario.
- La conexion a la API que cuenta con una clase record para poder transcribir de manera mas sencilla los datos que son pedidos 
a la API y asi poder trabajar con ellos.
- la tercera parte que es donde crear un archivo con el formato JSON que busca hacer de cache para la api y asi poder guardar
las conversiones del usuario. 

## inicio
este cuenta con dos if por separados que son lo que hacen las respetivas comprobaciones del formato, es decir que los 
usuarios solo puedan ingresar letras cuando se requieran letras para los codigos de las monedas y numeros en los
casos donde se necesite como tal la cantidad que se va a convertir de una moneda a otra.
- este cuenta con un do while,  para poder hacer las conversiones que el usario necesite
## Conexion a la API
dentro de la clase tenemos las siguientes caracteristicas
- dos variables las cuales estan presentes en los metodos que hacen las consultas se. 
- un metodo el cual consulta todas las monedas que soporta la api que se esta consuminedo
- el metodo obtener conversiones consulta las monedas en las que se puede dar una conversion entre la moneda seleccionada y la que
desea el usuario. 
- el metodo conversionMoneda el cual se hace la peticion a al api para que pueda convertir las monedas.

- Tenemos la clase DatosMoneda que es un record el cual nos permite tener una forma concisa de crear clases inmutables en Java. La clase 
DatosMoneda es un buen ejemplo de uso de Records, ya que encapsula eficientemente los datos de la moneda sin necesidad de escribir mucho 
código boilerplate. La función toString que se ha sobrescrito es una buena práctica, ya que permite imprimir los datos de la moneda de una 
manera más legible.
## Archivo JSON 
se tiene un codigo en comentario ya que aunque es funcional este no asegura como tal el correcto cierre del archivo fileWrite,
el bloque try-with-resources es una construcción de Java que ayuda a manejar correctamente el cierre de recursos que
implementan la interfaz AutoCloseable, como FileWriter. Cuando abres un FileWriter dentro de un bloque try-with-resources,
Java se asegura de que el FileWriter se cierre automáticamente al final del bloque, incluso si ocurre una excepción.
Esto puede ayudar a prevenir errores y fugas de recursos.
