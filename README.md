# Descripcion general
el proyecto conversor de monedas busca poder enriquecer y complemetar los concimientos obtenidos para poder consumit una API, 
este proyecto cuenta con 3 partes las cuales se dividen en:
- inicio que es donde sucede toda la interaccion de codigo usuario.
- La conexion a la API que cuenta con una clase record para poder transcribir de manera mas sencilla los datos que son pedidos 
a la API y asi poder trabajar con ellos.
- la tercera parte que es donde crear un archivo con el formato JSON que busca hacer de cache para la api y asi poder guardar
las conversiones del usuario. 

## inicio
- El inicio cuenta con un menu dado por un bloque de String al usar las triple comillas lo cual nos permite dar saltos de 
linea sin nigun problema y tal como su nombre lo indica hacer un bloque. 
- luego se le da a elegir una opcion al usuario y dependiendo de esta se da la convesion sin embargo para gestionar estas deciosiones
se uso un metodo con unico parametro y un switch para poder dar por si el usuario ingresa un número que no está en las opciones (como 18),
la función getMensaje devolverá una cadena vacía (“”). Luego, en el código principal, se verifica si el mensaje es una cadena 
vacía y, si es así, se imprime “Ingrese una opción válida” y se vuelve al inicio del bucle. 
- se tiene un if donde se dice que si la opcion es 9 de un break para poder salir del bucle do while y para la ejecucion del programa. 
- se pide la cantidad de monto que se quiere convertir de una moneda a otra. 
- luego se manda los datos para que estos sean procesodos e impresos en pantalla al igual que almacenados en un arraylist para el historial
## Conexion a la API
dentro de la clase tenemos las siguientes caracteristicas
- en el metodo onversionMoneda, se tiene como primer lugar la URI la cual permite almacenar la conexion que se va usar hacia la API
- tenemos las siguientes instancias que sin ellas no se podrian hacer las peticiones a la api que serian, httpcliente y httprequest
- luego tenemos un try el cual captura los posibles errores en nuestro codigo, dentro del try contiene el response el cual devuelve la respuesta
de la solicitud que se le hizo a la API
- si todo salir bien este devuelve un abjeto tipo Gson mas especifico formjson el cual nos permite traducir el formato json a codigo o 
como tal json a datos en este caso.

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
