from chicken_coop import ChickenCoop
from chicken_farm_app import ChickenFarmApp
from json_storage import load_coop_from_json, save_coop_to_json

if __name__ == "__main__":
    coop = ChickenCoop(101)
    
    load_coop_from_json(coop)
    
    ChickenFarmApp.main(coop)
    
    save_coop_to_json(coop)