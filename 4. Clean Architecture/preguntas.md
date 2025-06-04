- 1. ¿Qué problema busca resolver Clean Architecture en el desarrollo de software?
    - Clean Architecture busca resolver el problema del acoplamiento excesivo entre los distintos componentes de una aplicación, permitiendo que la lógica de negocio sea independiente de los frameworks, bases de datos, interfaces de usuario y otros detalles de implementación. Esto facilita la mantenibilidad, escalabilidad, testabilidad y flexibilidad del sistema.
    - 
    - 2. ¿Cuáles son las principales capas de Clean Architecture y qué responsabilidad tiene cada una?
        - Entidad (Domain): Contiene las reglas de negocio más importantes y entidades del dominio. No depende de ninguna tecnología externa.
        - Casos de uso (Application): Define los flujos específicos de negocio (interacciones entre entidades y procesos).
        - Adaptadores (Interface Adapters): Contiene la lógica de transformación de datos entre la capa de aplicación y la infraestructura (ejemplo: controladores, DTOs, repositorios).
        - Infraestructura (Frameworks & Drivers): Contiene detalles de implementación como bases de datos, frameworks web, ORMs, API REST, etc.
    - 
    - 3. ¿Qué relación tiene Clean Architecture con el principio de Inversión de Dependencias (DIP) en SOLID?
    - Clean Architecture sigue el principio de Inversión de Dependencias (DIP) al estructurar el software de manera que los detalles dependen de las abstracciones y no al revés. Las capas externas dependen de las internas, pero las internas no conocen las externas. Esto se logra mediante interfaces que permiten desacoplar dependencias.
    - 
    - 4. ¿Por qué es importante desacoplar la lógica de negocio de la infraestructura en una aplicación?
    - Porque permite:
        - Independencia tecnológica: Puedes cambiar la base de datos, framework o UI sin afectar la lógica de negocio.
        - Mejor mantenibilidad: El código es más fácil de entender y modificar.
        - Mayor testabilidad: Se pueden escribir pruebas unitarias sin depender de componentes externos como bases de datos o frameworks.
    - 
    - 5. ¿Cómo Clean Architecture facilita la escalabilidad y mantenibilidad de un sistema?
        - Escalabilidad: Permite cambiar o agregar nuevas tecnologías sin afectar la lógica central.
        - Mantenibilidad: La separación de responsabilidades hace que el código sea más limpio y fácil de entender.
        - Extensibilidad: Se pueden agregar nuevas funcionalidades sin afectar el código existente.
        - Testabilidad: Al desacoplar la lógica de negocio, se pueden escribir pruebas unitarias más fácilmente.
    - 
    - 6. ¿Qué diferencia hay entre la capa de dominio y la capa de aplicación en Clean Architecture?
        - Capa de dominio (Domain): Contiene las entidades y las reglas de negocio puras. No depende de ninguna tecnología externa.
        - Capa de aplicación (Application): Define los casos de uso, que representan flujos específicos del negocio y orquestan las interacciones entre las entidades de dominio.
    - 
    - 7. ¿Por qué los controladores (controllers) y la base de datos deben estar en la capa de infraestructura?
    - Porque son detalles de implementación que pueden cambiar con el tiempo. Los controladores manejan la entrada/salida de datos (API REST, UI), mientras que la base de datos es solo un medio de almacenamiento. La lógica de negocio no debería depender directamente de ellos.
    - 
    - 8. ¿Qué ventajas tiene usar una interfaz en la capa de dominio para definir repositorios en lugar de usar directamente **JpaRepository**?
        - Desacoplamiento: La lógica de negocio no depende directamente de JPA ni de ningún ORM.
        - Flexibilidad: Se puede cambiar la implementación de persistencia sin afectar la lógica de negocio.
        - Testabilidad: Se pueden usar mocks o implementaciones en memoria para pruebas sin necesidad de una base de datos real.
    - Ejemplo:
    - public interface UserRepository {
    User findById(Long id);
    void save(User user);
}
    - Luego, en la infraestructura, puedes implementar esta interfaz con JpaRepository:
    - @Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaRepository;

    @Override
    public User findById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        jpaRepository.save(user);
    }
}
    - 
    - 9. ¿Cómo interactúan los casos de uso (UseCases) con las entidades de dominio en Clean Architecture?
    - Los casos de uso representan los flujos de negocio y utilizan las entidades de dominio para ejecutar las reglas de negocio.
        - Los casos de uso NO contienen lógica de negocio compleja, solo coordinan las entidades de dominio.
        - Los casos de uso NO dependen de la base de datos ni de controladores, sino de interfaces.
    - Ejemplo en Java:
    - public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        userRepository.save(user);
    }
}
    - 
    - 10. ¿Cómo se puede aplicar Clean Architecture en una aplicación de microservicios con Spring Boot?
        - Capa de Dominio
            - Define entidades y lógica de negocio pura.
        - Capa de Aplicación
            - Define casos de uso como servicios independientes.
        - Capa de Adaptadores
            - Implementa repositorios, controladores y conversión de datos (DTOs).
        - Capa de Infraestructura
            - Contiene controladores REST, configuración de bases de datos, seguridad y frameworks.
    - Ejemplo de estructura en un microservicio Spring Boot: 
    - /src/main/java/com/example
 ├── domain
 │   ├── entity
 │   ├── repository
 ├── application
 │   ├── usecase
 ├── adapters
 │   ├── repository (implementación de repositorios)
 │   ├── controller (API REST)
 ├── infrastructure
 │   ├── config
 │   ├── database
 │   ├── security
    - Este enfoque permite que los microservicios sean modulares, fáciles de escalar y mantenibles. 