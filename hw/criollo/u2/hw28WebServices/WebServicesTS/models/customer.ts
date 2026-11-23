import mongoose, { Schema, Document } from "mongoose";

export interface ICustomer extends Document {
  id: number;
  name: string;
  age: number;
  moneySpent: number;
}

const customerSchema = new Schema<ICustomer>(
  {
    id: { type: Number, required: true, unique: true },
    name: { type: String, required: true },
    age: { type: Number, required: true },
    moneySpent: { type: Number, required: true },
  },
  { collection: "Customer" },
);

export const Customer = mongoose.model<ICustomer>("Customer", customerSchema);
