#language: es
@web
Característica: Demo Form (#3)

  @caso2
  Escenario: Agregar y validar la creación de un nuevo empleado
    Dado que el usuario abre la pagina OrangeLoginPage
    E inicia sesión con las credenciales
      | user  | password |
      | Admin | admin123 |
    Cuando me dirijo al menú "PIM"
    Cuando presiono el botón para agregar un nuevo empleado
    Y completo la información del nuevo empleado
      | firstName | middleName  | lastName  |
      | Test      | Middle Test | Last Test |
    Entonces valido que el nuevo empleado se haya creado correctamente
    Y cierro sesión
