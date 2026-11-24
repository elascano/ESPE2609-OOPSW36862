"""Secure MongoDB Atlas connection manager using pymongo and python-dotenv."""

import os
from pathlib import Path
from typing import Optional

from dotenv import load_dotenv
from pymongo import MongoClient
from pymongo.collection import Collection
from pymongo.database import Database
from pymongo.errors import (
    ConfigurationError,
    ConnectionFailure,
    OperationFailure,
    ServerSelectionTimeoutError,
)


class DatabaseConnection:
    """Manages connection lifecycle to MongoDB Atlas using environment variables."""

    def __init__(self, env_path: Optional[Path] = None) -> None:
        project_root = Path(__file__).resolve().parent.parent
        dotenv_file = env_path if env_path is not None else project_root / ".env"
        load_dotenv(dotenv_path=dotenv_file)

        self.__uri: str = os.getenv("MONGO_URI", "").strip()
        self.__db_name: str = os.getenv("MONGO_DB_NAME", "oop").strip()
        self.__collection_name: str = os.getenv("MONGO_COLLECTION_NAME", "animals").strip()
        self.__client: Optional[MongoClient] = None
        self.__database: Optional[Database] = None

    def connect(self) -> Database:
        """Establish and verify the connection to MongoDB Atlas."""
        if not self.__uri:
            raise ValueError(
                "MONGO_URI is not configured. Create a .env file based on .env.example."
            )

        try:
            self.__client = MongoClient(
                self.__uri,
                serverSelectionTimeoutMS=5000,
                connectTimeoutMS=5000,
            )
            # Verify connectivity immediately
            self.__client.admin.command("ping")
            self.__database = self.__client[self.__db_name]
            return self.__database
        except (ConnectionFailure, ServerSelectionTimeoutError) as exc:
            self.close()
            raise ConnectionFailure(
                "Could not connect to MongoDB Atlas. Verify your internet connection, "
                "URI credentials, and IP whitelist (0.0.0.0/0 in Atlas Network Access)."
            ) from exc
        except (ConfigurationError, OperationFailure) as exc:
            self.close()
            raise ConnectionFailure(
                f"MongoDB Atlas configuration or authentication error: {exc}"
            ) from exc

    def get_collection(self, collection_name: Optional[str] = None) -> Collection:
        """Return the target MongoDB collection, connecting automatically if needed."""
        if self.__database is None:
            self.connect()
        assert self.__database is not None
        target_name = collection_name or self.__collection_name
        return self.__database[target_name]

    def get_db_name(self) -> str:
        return self.__db_name

    def get_collection_name(self) -> str:
        return self.__collection_name

    def close(self) -> None:
        """Close the active MongoDB client cleanly."""
        if self.__client is not None:
            self.__client.close()
            self.__client = None
            self.__database = None

    def __enter__(self) -> "DatabaseConnection":
        self.connect()
        return self

    def __exit__(self, exc_type, exc_val, exc_tb) -> None:
        self.close()
