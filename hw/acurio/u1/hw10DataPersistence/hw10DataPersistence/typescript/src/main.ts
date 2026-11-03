import { main } from "./view/ChickenFarmApp";

main().catch(error => {
    console.error("Unexpected error:", error);
    process.exitCode = 1;
});
