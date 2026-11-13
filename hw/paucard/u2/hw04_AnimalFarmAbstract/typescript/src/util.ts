/** Formatea una fecha como `d / m / yyyy` (igual que el toString() original en Java). */
export function formatDate(value: Date): string {
  return `${value.getDate()} / ${value.getMonth() + 1} / ${value.getFullYear()}`;
}
