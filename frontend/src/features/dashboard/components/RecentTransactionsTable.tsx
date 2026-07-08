import type { PageResponse } from "../../../types/PageResponse";
import type { TransactionSummaryResponse } from "../types/TransactionSummaryResponse";

const RecentTransactionsTable = ({
  transactionPage,
  isTransactionsLoading,
}: {
  transactionPage: PageResponse<TransactionSummaryResponse> | undefined;
  isTransactionsLoading: boolean;
}) => {
  const transactions = transactionPage?.content || [];
  return (
    <div
      className="
        surface-card
        overflow-hidden
      "
    >
      <div
        className="
          px-6
          py-5
          border-b
          border-border-light
        "
      >
        <h2 className="font-bold text-lg">Recent Transactions</h2>
      </div>
      <table className="w-full">
        <thead>
          <tr
            className="
              bg-surface-secondary text-left border-t border-border-light
            "
          >
            <th className="px-6 py-4 text-center">Type</th>

            <th className="px-6 py-4 text-center">Amount</th>

            <th className="px-6 py-4 text-center">Status</th>

            <th className="px-6 py-4 text-center">Date</th>
          </tr>
        </thead>

        {isTransactionsLoading && (
          <tbody>
            <tr>
              <td colSpan={4} className="p-6 text-center">
                <p>Loading...</p>
              </td>
            </tr>
          </tbody>
        )}

        {transactions.length === 0 && !isTransactionsLoading && (
          <tbody>
            <tr>
              <td colSpan={4} className="p-6 text-center">
                <p>No transactions found.</p>
              </td>
            </tr>
          </tbody>
        )}

        {transactions && (
          <tbody>
            {transactions.map((transaction) => (
              <tr
                key={transaction.transferId}
                className="
                border-t
                border-border-light
              "
              >
                <td className="px-6 py-4 text-center">
                  {transaction.transferType}
                </td>

                <td className="px-6 py-4 font-medium text-center">
                  {transaction.amount}
                </td>

                <td className="px-6 py-4 text-center ">{transaction.status}</td>

                <td className="px-6 py-4 text-text-secondary text-center">
                  {transaction.transactionDate.toLocaleDateString()}
                </td>
              </tr>
            ))}
          </tbody>
        )}
      </table>
    </div>
  );
};

export default RecentTransactionsTable;
