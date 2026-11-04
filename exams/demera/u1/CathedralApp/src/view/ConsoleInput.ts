import * as readline from "readline/promises";

export class ConsoleInput {
  private readonly lineReader = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  async askText(question: string): Promise<string> {
    while (true) {
      const answer = (await this.lineReader.question(question)).trim();
      if (answer !== "") {
        return answer;
      }
      console.log("The value cannot be empty.");
    }
  }

  async askPositiveInteger(question: string): Promise<number> {
    while (true) {
      const answer = Number(await this.lineReader.question(question));
      if (Number.isInteger(answer) && answer > 0) {
        return answer;
      }
      console.log("Please enter a positive whole number.");
    }
  }

  close(): void {
    this.lineReader.close();
  }
}
