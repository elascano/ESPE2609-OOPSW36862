/** Java's deprecated Date(year, month, day): year since 1900, month zero-based. */
export function javaDate(year: number, month: number, day: number): Date {
  return new Date(year + 1900, month, day);
}

export function javaDateText(value: Date): string {
  return `${value.getDate()} / ${value.getMonth() + 1} / ${value.getFullYear()}`;
}

/** Equivalent formatting to Java's Date.toString() for Sheep.lastSheering. */
export function javaDateToString(value: Date): string {
  const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  const pad = (n: number): string => (n < 10 ? "0" + n : String(n));
  const match = /\(([^)]+)\)$/.exec(value.toString());
  const zone = match?.[1] ?? "GMT";
  const zoneName = zone === "Coordinated Universal Time" ? "UTC" : zone;
  return `${days[value.getDay()]} ${months[value.getMonth()]} ${pad(value.getDate())} ` +
    `${pad(value.getHours())}:${pad(value.getMinutes())}:${pad(value.getSeconds())} ` +
    `${zoneName} ${value.getFullYear()}`;
}
