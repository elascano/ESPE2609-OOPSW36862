from fastapi import FastAPI
from routes.customerRoutes import router as customerRouter

app = FastAPI()
app.include_router(customerRouter, prefix="/computerstore")
