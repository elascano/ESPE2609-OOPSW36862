declare module "node:readline/promises" {
    export function createInterface(options: {input: unknown; output: unknown}): {
        question(prompt: string): Promise<string>;
        close(): void;
    };
}
declare module "node:process" {
    export const stdin: unknown;
    export const stdout: unknown;
}

declare module "node:fs" {
    export function existsSync(path: string): boolean;
    export function readFileSync(path: string, encoding: string): string;
    export function writeFileSync(path: string, content: string, encoding: string): void;
}
declare const process: { exit(code: number): never };
