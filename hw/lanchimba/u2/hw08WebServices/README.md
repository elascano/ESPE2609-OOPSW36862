# hw08WebServices

Servicio Web **RESTful** desarrollado con **Node.js**, **Express** y **MongoDB Atlas (Mongoose)** aplicando arquitectura modular (`config/`, `models/`, `routes/`) y códigos de estado HTTP estándar, siguiendo los lineamientos de Programación Orientada a Objetos de la ESPE.

## Estructura del Proyecto

```text
hw08WebServices/
├── config/
│   └── db.js                 # Conexión a MongoDB Atlas con Mongoose y dotenv
├── models/
│   └── Customer.js           # Esquema y modelo de Mongoose (sin __v)
├── routes/
│   └── customerRoutes.js     # Rutas RESTful CRUD con express.Router()
├── index.js                  # Punto de entrada del servidor Express
├── package.json
├── .env                      # Variables locales (ignorado por Git)
├── .env.example              # Plantilla segura sin credenciales
├── .gitignore
└── README.md
```

## Instalación y Ejecución

```bash
cd hw08WebServices
npm install
npm start
```

El servidor quedará activo en `http://localhost:3005` (puerto configurable mediante `PORT` en `.env`).

## Endpoints REST (`/api/customers`)

| Método | Ruta | Descripción | Códigos HTTP |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | Estado del servicio y lista de endpoints | `200` |
| `GET` | `/api/customers` | Lista todos los clientes ordenados por `id` | `200`, `500` |
| `GET` | `/api/customers/:id` | Busca un cliente por su `id` numérico | `200`, `400`, `404`, `500` |
| `POST` | `/api/customers` | Crea un nuevo cliente desde JSON (`req.body`) | `201`, `400`, `500` |
| `PUT` | `/api/customers/:id` | Actualiza un cliente existente por `id` | `200`, `400`, `404`, `500` |
| `DELETE` | `/api/customers/:id` | Elimina un cliente por `id` | `200`, `400`, `404`, `500` |

## Ejemplos de Prueba (`curl` / Postman / Thunder Client)

### Crear cliente (`POST /api/customers`)
```bash
curl -X POST http://localhost:3005/api/customers \
  -H "Content-Type: application/json" \
  -d "{\"id\": 1, \"name\": \"Cristhian Lanchimba\", \"email\": \"cdlanchimba@espe.edu.ec\", \"cellphoneNumber\": \"0991234567\"}"
```

### Listar clientes (`GET /api/customers`)
```bash
curl http://localhost:3005/api/customers
```

### Consultar cliente por ID (`GET /api/customers/1`)
```bash
curl http://localhost:3005/api/customers/1
```

### Actualizar cliente (`PUT /api/customers/1`)
```bash
curl -X PUT http://localhost:3005/api/customers/1 \
  -H "Content-Type: application/json" \
  -d "{\"name\": \"Cristhian D. Lanchimba\", \"cellphoneNumber\": \"0987654321\"}"
```

### Eliminar cliente (`DELETE /api/customers/1`)
```bash
curl -X DELETE http://localhost:3005/api/customers/1
```
