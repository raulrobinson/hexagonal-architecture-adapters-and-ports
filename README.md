# Hexagonal Architecture with Adapters and Ports

Este servicio está diseñado siguiendo la arquitectura hexagonal (también conocida como arquitectura de puertos y adaptadores). Esta arquitectura permite una separación clara entre el núcleo de la aplicación y las dependencias externas, facilitando la mantenibilidad, escalabilidad y testabilidad del código.

### Reglas de dependencias:
- ***domain*** → **NO** depende de nadie.
- ***application*** → depende de domain.
- ***infrastructure:*** → dependen de application y domain.
- ***config*** → depende de todos (application, domain, infrastructure:*).
- ***boot*** → depende de config.

```text
hexagonal-architecture-adapters-and-ports
│
├─ settings.gradle
├─ build.gradle
│
├─ domain/
│  ├─ build.gradle
│  └─ src/main/java/com/demo/sd/sn/customer/domain/...
│
├─ application/
│  ├─ build.gradle
│  └─ src/main/java/com/demo/sd/sn/customer/application/...
│
├─ config/
│  ├─ build.gradle
│  └─ src/main/java/com/demo/sd/sn/customer/config/...
│
├─ infrastructure/
│  │ 
│  ├─ adapter-db/
│  │  ├─ build.gradle
│  │  └─ src/main/java/com/demo/sd/sn/customer/infrastructure/db/...
│  │
│  ├─ adapter-client/
│  │  ├─ build.gradle
│  │  └─ src/main/java/com/demo/sd/sn/customer/infrastructure/client/...
│  │
│  └─ adapter-rest/
│     ├─ build.gradle
│     └─ src/main/java/com/demo/sd/sn/customer/infrastructure/rest/...
│
└─ boot/
   ├─ build.gradle
   └─ src/main/java/com/demo/sd/sn/customer/boot/...
```

### Evolución a Clean + DDD táctico

Setup:
- DDD táctico
  - Entidades / Aggregates: Customer
  - Value Objects: CustomerId
  - Domain Services: CustomerDomainService
  - Repositorios (puertos): CustomerRepository, CreditScoreClient

- Clean Architecture / Hexagonal
  - Domain: centro de la arquitectura (domain/*)
  - Application: casos de uso (RegisterCustomerUseCase, commands/results)
  - Adapters:
    - DB: CustomerRepositoryAdapter (+ JPA)
    - Client: WebClientCreditScoreAdapter
    - API REST: CustomerController
  - Boot: composición/configuración, wiring de beans y arranque.

### Objetivo final:
- ***boot*** → solo main()
- ***config*** → TODOS los @Configuration, @Bean, WebClient, Datasource, UseCases
- ***domain*** → puro
- ***application*** → use cases
- ***infrastructure*** → adapters

### Diagrama:
![hexagon-adapters-ports.png](docs/hexagon-adapters-ports.png)

### Author

- **[Raul R. Bolivar Navas](https://github.com/raulrobinson)**

### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.