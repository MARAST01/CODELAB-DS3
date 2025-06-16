### 1. ¿Cuál es el propósito principal de Clean Architecture en el desarrollo de software?

Clean Architecture busca organizar el código de forma que cada parte del sistema tenga una única responsabilidad y esté lo más independiente posible del resto. El objetivo es que la lógica del negocio esté protegida de los cambios tecnológicos (como bases de datos, frameworks o APIs externas), lo que hace que el software sea mucho más fácil de mantener, probar y evolucionar.

---

### 2. ¿Qué beneficios aporta Clean Architecture a un microservicio en Spring Boot?

Aplicar Clean Architecture en un microservicio ayuda a que:
- Cada parte del código esté bien organizada y con una función clara.
- Sea más fácil reemplazar tecnologías (por ejemplo, cambiar la base de datos).
- Se pueda escalar agregando nuevas funcionalidades sin romper lo que ya existe.
- La lógica de negocio sea fácil de probar y mantener, sin depender del resto del sistema.

---

### 3. ¿Cuáles son las principales capas de Clean Architecture y qué responsabilidad tiene cada una?

Las capas son:

- **Dominio:** Aquí va la lógica central del negocio, como las entidades y reglas. No depende de nada externo.
- **Aplicación:** Orquesta las operaciones del sistema mediante casos de uso. Se comunica con el dominio e infraestructura usando interfaces.
- **Infraestructura:** Maneja detalles técnicos como la base de datos, servicios externos, etc.
- **Presentación:** Contiene los controladores REST y gestiona la entrada/salida de datos.

Cada capa sólo se comunica con las capas internas, nunca con las externas.

---

### 4. ¿Por qué se recomienda desacoplar la lógica de negocio de la infraestructura en un microservicio?

Porque eso permite que la lógica del negocio:
- No dependa de tecnologías como JPA, MongoDB o Spring.
- Se pueda probar de forma independiente y sencilla.
- Se mantenga estable aunque cambie la base de datos, APIs o librerías externas.

Así, el negocio sigue funcionando aunque cambien los detalles técnicos.

---

### 5. ¿Cuál es el rol de la capa de aplicación y qué tipo de lógica debería contener?

La capa de aplicación se encarga de ejecutar los casos de uso del sistema, como listar productos, crear órdenes, etc. Su función es coordinar las acciones entre el dominio y la infraestructura, pero sin involucrarse en detalles técnicos ni en cómo se muestran los datos al usuario.

---

### 6. ¿Qué diferencia hay entre un UseCase y un Service en Clean Architecture?

- Un **Service** tradicional a veces mezcla lógica de negocio con acceso a la base de datos.
- Un **UseCase** representa una acción específica del sistema (por ejemplo, “listar productos”) y se enfoca sólo en eso.

Usar UseCases permite tener un código más claro, modular y fácil de mantener, especialmente cuando el sistema empieza a crecer.

---

### 7. ¿Por qué se recomienda definir Repositories como interfaces en la capa de dominio en lugar de usar directamente JpaRepository?

Porque así el dominio no queda atado a una tecnología específica como JPA. Si mañana se cambia la base de datos o se decide usar otro sistema (como una API externa), no hay que tocar el dominio. Solo se reemplaza la implementación, manteniendo la lógica del negocio intacta.

---

### 8. ¿Cómo se implementa un UseCase en un microservicio con Spring Boot y qué ventajas tiene?

Un UseCase se implementa como una clase de servicio en la capa de aplicación. Esta clase:
- Inyecta una interfaz de repositorio.
- Ejecuta una acción del negocio (por ejemplo, buscar productos).
- No depende de cómo o dónde se guardan los datos.

**Ventajas:**
- El código es más limpio y enfocado.
- Si cambia la lógica, sólo se modifica el UseCase.
- Los controladores y la base de datos quedan completamente separados.

---

### 9. ¿Qué problemas podrían surgir si no aplicamos Clean Architecture en un proyecto de microservicios?

Sin una arquitectura limpia:
- La lógica de negocio puede terminar mezclada con los controladores o repositorios, volviendo todo más complejo.
- Cambiar tecnologías o agregar funcionalidades se vuelve riesgoso y propenso a errores.
- Las pruebas unitarias son más difíciles de hacer.
- El sistema se vuelve más difícil de escalar y mantener a largo plazo.

---

### 10. ¿Cómo Clean Architecture facilita la escalabilidad y mantenibilidad en un entorno basado en microservicios?

Al tener cada parte del sistema bien separada, se puede:
- Escalar fácilmente agregando nuevos casos de uso sin romper lo existente.
- Mantener el sistema actualizando o reemplazando solo lo necesario (como cambiar la base de datos).
- Hacer pruebas más sencillas.
- Trabajar en equipo sin que varios desarrolladores pisen el mismo código, ya que cada capa está bien definida.

---