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
