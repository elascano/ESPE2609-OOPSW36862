import { Customer } from "../models/customer";
export async function findAllCustomers() {
    return await Customer.find();
}
export async function findCustomerById(customerId) {
    return await Customer.findOne({ id: customerId });
}
export async function createCustomer(customerData) {
    const newCustomer = new Customer(customerData);
    return await newCustomer.save();
}
export async function updateCustomerById(customerId, updatedData) {
    return await Customer.findOneAndUpdate({ id: customerId }, updatedData, {
        new: true,
    });
}
export async function deleteCustomerById(customerId) {
    return await Customer.findOneAndDelete({ id: customerId });
}
export function calculateTotalSpent(customers) {
    return customers.reduce((total, current) => total + (current.moneySpent || 0), 0);
}
export function calculateAverageSpent(customers) {
    if (customers.length === 0)
        return 0;
    return calculateTotalSpent(customers) / customers.length;
}
export function findHighestSpender(customers) {
    if (customers.length === 0)
        return null;
    return customers.reduce((highest, current) => (current.moneySpent || 0) > (highest.moneySpent || 0) ? current : highest);
}
export async function getSpendingAnalytics() {
    const customers = await findAllCustomers();
    return {
        totalSpent: calculateTotalSpent(customers),
        averageSpent: calculateAverageSpent(customers),
        highestSpender: findHighestSpender(customers),
    };
}
