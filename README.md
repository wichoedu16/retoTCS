# Banco App

API REST para gestión bancaria.

## Requisitos

- Java 17
- Maven
- Docker
- MySQL

## Instalación

1. Clonar repositorio:
```git clone https://github.com/TU-USUARIO/banco-app.git```

2. Iniciar base de datos:
```docker-compose up -d```

3. Ejecutar aplicación:
```mvn spring-boot:run```

## API Endpoints

- POST /api/clientes - Crear cliente
- GET /api/clientes - Listar clientes
- POST /api/cuentas - Crear cuenta
- POST /api/movimientos - Realizar movimiento
