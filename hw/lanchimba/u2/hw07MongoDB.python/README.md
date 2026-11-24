# hw07MongoDB.python

Aplicación de Programación Orientada a Objetos en **Python** conectada a **MongoDB Atlas** mediante `pymongo` para ejecutar operaciones **CRUD completas** sobre la entidad `FarmAnimal`.

## Estructura del Proyecto

```text
hw07MongoDB.python/
├── config/
│   ├── __init__.py
│   └── database.py                # Conexión segura a MongoDB Atlas vía .env
├── model/
│   ├── __init__.py
│   └── farm_animal.py             # Entidad FarmAnimal con to_dict() y from_dict()
├── controller/
│   ├── __init__.py
│   └── farm_animal_repository.py  # Repositorio DAO con CRUD completo
├── view/
│   ├── __init__.py
│   └── main.py                    # Menú interactivo por consola
├── .env                           # Credenciales locales (ignorado por Git)
├── .env.example                   # Plantilla segura sin contraseñas
├── .gitignore
├── requirements.txt
└── README.md
```

## Requisitos Previos

1. **Python 3.10+** instalado.
2. **Acceso de red en MongoDB Atlas:**
   - Ingresa a [MongoDB Atlas](https://cloud.mongodb.com/).
   - En el menú izquierdo ve a **Security -> Network Access**.
   - Haz clic en **Add IP Address** y selecciona **Allow Access from Anywhere (`0.0.0.0/0`)** para pruebas de desarrollo (o agrega tu IP pública actual).
   - Verifica en **Database Access** que tu usuario tenga permisos de lectura y escritura (`readWriteAnyDatabase` o sobre la base de datos `oop`).

## Instalación

Desde la carpeta `hw07MongoDB.python/`:

```bash
# (Opcional recomendado) Crear y activar entorno virtual
python -m venv .venv
# Windows (PowerShell):
.\.venv\Scripts\Activate.ps1
# Linux / macOS:
# source .venv/bin/activate

# Instalar dependencias
pip install -r requirements.txt
```

## Configuración de Variables de Entorno

Si clonas el repositorio en otro equipo, copia `.env.example` a `.env` y coloca tu URI de MongoDB Atlas:

```env
MONGO_URI=mongodb+srv://<usuario>:<password>@cluster0.ejemplo.mongodb.net/
MONGO_DB_NAME=oop
MONGO_COLLECTION_NAME=animals
```

> **Seguridad:** El archivo `.env` está incluido en `.gitignore` para evitar exponer contraseñas en GitHub. Se recomienda rotar la contraseña en MongoDB Atlas si fue compartida en un entorno externo.

## Ejecución

```bash
python view/main.py
```

O como módulo:

```bash
python -m view.main
```

### Operaciones disponibles en el menú
1. **Insertar animal** (`insert_one`) — valida ID único e inserta el documento BSON.
2. **Listar todos los animales** (`find_all`) — recupera y deserializa todos los documentos.
3. **Buscar animal por ID** (`find_by_id`) — consulta un documento específico.
4. **Actualizar animal por ID** (`update_by_id`) — actualiza campos mediante `$set`.
5. **Eliminar animal por ID** (`delete_by_id`) — elimina el documento de la colección.
