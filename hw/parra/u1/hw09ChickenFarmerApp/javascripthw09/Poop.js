class Poop {
  constructor(id, color, consistency) {
    this.id = id;
    this.color = color;
    this.consistency = consistency;
  }

  toString() {
    return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
  }
}

module.exports = Poop;
