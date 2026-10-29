import { RectanglePerimeter } from "./RectanglePerimeter";
const base = Number(process.argv[2] ?? 4);
const height = Number(process.argv[3] ?? 3);
console.log(`Rectangle perimeter: ${new RectanglePerimeter().calculatePerimeter(base, height).toFixed(2)}`);
