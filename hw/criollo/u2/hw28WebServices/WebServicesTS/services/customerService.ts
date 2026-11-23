import { Customer, ICustomer } from "../models/customer";

export async function findAllCustomers(): Promise<ICustomer[]> {
  return await Customer.find();
}

export async function findCustomerById(
  customerId: number,
): Promise<ICustomer | null> {
  return await Customer.findOne({ id: customerId });
}

export async function createCustomer(
  customerData: Partial<ICustomer>,
): Promise<ICustomer> {
  const newCustomer = new Customer(customerData);
  return await newCustomer.save();
}

export async function updateCustomerById(
  customerId: number,
  updatedData: Partial<ICustomer>,
): Promise<ICustomer | null> {
  return await Customer.findOneAndUpdate({ id: customerId }, updatedData, {
    new: true,
  });
}

export async function deleteCustomerById(
  customerId: number,
): Promise<ICustomer | null> {
  return await Customer.findOneAndDelete({ id: customerId });
}

export function calculateTotalSpent(customers: ICustomer[]): number {
  return customers.reduce(
    (total, current) => total + (current.moneySpent || 0),
    0,
  );
}

export function calculateAverageSpent(customers: ICustomer[]): number {
  if (customers.length === 0) return 0;
  return calculateTotalSpent(customers) / customers.length;
}

export function findHighestSpender(customers: ICustomer[]): ICustomer | null {
  if (customers.length === 0) return null;
  return customers.reduce((highest, current) =>
    (current.moneySpent || 0) > (highest.moneySpent || 0) ? current : highest,
  );
}

export async function getSpendingAnalytics() {
  const customers = await findAllCustomers();
  return {
    totalSpent: calculateTotalSpent(customers),
    averageSpent: calculateAverageSpent(customers),
    highestSpender: findHighestSpender(customers),
  };
}
