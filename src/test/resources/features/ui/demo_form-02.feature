#language: es
@web
Característica: Demo Form (#2)
  SauceDemo es un formulario WEB UI ejemplo para fines de entrenamiento
  Por favor ver (https://www.saucedemo.com/)

  -- DESCRIPCION --

  Para este ejercicio, es necesario ingresar a la página 'saucedemo' para conocer el comportamiento de el formulario
  de inicio de sesión, validar el comportamiento actual del escenario, validar los mensajes de error que controlan el formulario

  1) Ingresa a la página y analiza el formulario para encontrar y AGREGAR más casos de prueba para el login incorrecto.
  Por ejemplo: En el escenario actual se está utilizando un usuario bloqueado, esto es validado por el formulario
  de login y finalmente me va a mostrar un mensaje de error controlado.
  2) Resolver los errores de compilación y ejecución hasta completar correctamente el escensario.
  3) Sientete libre de programar y agregar código si lo consideras necesario.
  4) (+) Puedes hacer uso de DataTable para enviar las credenciales
  5) (+) Genera el reporte HTML a partir de Cucumber o Serenity BDD

  @caso1
  Esquema del escenario: validar los mensajes de error de login fallido
    Dado que el usuario visita la pagina SauceDemoLoginPage
    Cuando ingresa las credenciales "<user>" y "<pass>" para iniciar sesión
    Entonces valida que el mensaje de error contiene "<error>"
    Ejemplos:
      | user            | pass            | error                                                       |
      | locked_out_user | secret_sauce    | Sorry, this user has been locked out.                       |
      | secret_sauce    | locked_out_user | Username and password do not match any user in this service |
      | 1arq!#!         | PRuebas#125     | Username and password do not match any user in this service |
      |                 |                 | Username is required                                        |