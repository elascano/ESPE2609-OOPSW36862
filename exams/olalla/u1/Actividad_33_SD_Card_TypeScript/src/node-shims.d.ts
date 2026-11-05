declare module "fs" {
  export function existsSync(path: string): boolean;
  export function mkdirSync(path: string, options?: any): void;
  export function writeFileSync(path: string, data: string, encoding: string): void;
  export function readFileSync(path: string, encoding: string): string;
}
declare module "path" {
  export function dirname(path: string): string;
  export function resolve(...paths: string[]): string;
}
declare const __dirname: string;
declare const process: { exitCode?: number };
