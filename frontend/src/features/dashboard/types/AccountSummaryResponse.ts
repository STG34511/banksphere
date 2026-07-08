export default interface AccountSummaryResponse {
    accountNumber: string,
    accountType: string,
    status: string,
    balance: number,
    primary: boolean
}