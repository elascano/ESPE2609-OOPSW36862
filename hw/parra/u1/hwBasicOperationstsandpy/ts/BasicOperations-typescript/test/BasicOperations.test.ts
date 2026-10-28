import { test } from "node:test";
import assert from "node:assert/strict";
import { main } from "../src/view/BasicOperations.js";

test("main", (t) => {
  const log = t.mock.method(console, "log", () => {});
  main();
  const lines = log.mock.calls.map((c) => String(c.arguments[0]));
  log.mock.restore();
  assert.equal(lines[0], "Operations");
  const prefix = "The addition of 1.2 and 2.4 ---> ";
  for (const l of lines.slice(1, 4)) {
    assert.ok(l.startsWith(prefix));
    assert.ok(Math.abs(parseFloat(l.slice(prefix.length)) - 3.6) < 1e-9);
  }
  assert.equal(lines[4], "divide ---> Infinity");
});
