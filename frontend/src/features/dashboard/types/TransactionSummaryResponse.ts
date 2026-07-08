export interface TransactionSummaryResponse {
        transferId : string,
        referenceNumber: string,
        transferType: string,
        direction: string,
        counterpartyName: string,
        amount: number,
        status: string,
        transactionDate: Date
}