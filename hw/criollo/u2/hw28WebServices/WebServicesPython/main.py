from fastapi import FastAPI
from routes.customerRoutes import router as customerRouter

app = FastAPI()

@app.get("/")
def inicio():
    return {"proyecto": "WebServicesPython", "responsable": "Dennis Criollo"}

app.include_router(customerRouter, prefix="/computerstore")