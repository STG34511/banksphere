import type AccountSummaryResponse from "./AccountSummaryResponse";


export default interface DashboardResponse {
    customerId : string,
    customerName: string,
    totalBalance: number,
    accounts: AccountSummaryResponse[],
    transactionsThisMonth: number,
    totalDebitThisMonth: number,
    totalCreditThisMonth: number
}