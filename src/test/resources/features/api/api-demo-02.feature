#language: es
@api
Característica: Rest test (#2)
  ReqRes es una WEB API demo para fines de entrenamiento (por favor ver: https://reqres.in)

  -- DESCRIPCION --

  Para este ejercicio, es necesario ingresar a la página 'reqres.in' para conocer el contrato de los servicios para la
  automatizacion end to end que consiste en crear un usuario y actualizar a partir del ID.

  - Servicio de creación de usuarios --> POST::/api/users
  - Servicio de actualización de usuarios --> PUT::/api/users/{id}

  1) Crear un nuevo usuario, enviando el metodo y request necesario
  2) La creación del usuario devolverá como respuesta al nuevo usuario y un nuevo ID
  3) Con este ID, actualizar el usuario creado anteriormente, enviandole los parametros y body request necesario
  4) Resolver los errores de compilación y ejecución hasta completar correctamente el escensario
  5) Sientete libre de programar y agregar código si lo consideras necesario
  6) Genera el reporte HTML a partir de Cucumber o Serenity BDD

  @caso1
  Escenario: Crear un nuevo usuario y modificar sus datos
    Dado que la URL es: "url.base"
    #Crear un nuevo usuario
    Cuando creo un nuevo usuario
      | name     | job    |
      | morpheus | leader |
    Entonces valido que el estado del servicio sea 201
    Y obtengo el id del nuevo usuario creado
    #Actualizar el usuario creado
    Cuando actualizo el usuario creado con los nuevos datos
      | newName  | newJob        |
      | morpheus | zion resident |
    Entonces valido que el estado del servicio sea 200
    Y que los nuevos datos esten actualizados
