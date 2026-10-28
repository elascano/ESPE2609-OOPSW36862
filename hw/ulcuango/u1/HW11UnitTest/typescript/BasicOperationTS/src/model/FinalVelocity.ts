export class FinalVelocity {
    private initialVelocity: number;
    private acceleration: number;
    private time: number;

    constructor(initialVelocity: number, acceleration: number, time: number) {
        if (time < 0) {
            throw new Error("Time cannot be negative");
        }
        this.initialVelocity = initialVelocity;
        this.acceleration = acceleration;
        this.time = time;
    }

    public calculateFinalVelocity(): number {
        return this.initialVelocity + (this.acceleration * this.time);
    }

    public getInitialVelocity(): number {
        return this.initialVelocity;
    }

    public setInitialVelocity(initialVelocity: number): void {
        this.initialVelocity = initialVelocity;
    }

    public getAcceleration(): number {
        return this.acceleration;
    }

    public setAcceleration(acceleration: number): void {
        this.acceleration = acceleration;
    }

    public getTime(): number {
        return this.time;
    }

    public setTime(time: number): void {
        if (time < 0) {
            throw new Error("Time cannot be negative");
        }
        this.time = time;
    }
}