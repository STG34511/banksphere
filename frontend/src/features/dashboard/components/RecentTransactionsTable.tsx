const transactions = [
  {
    id: 1,
    beneficiary: "Amit Sharma",
    amount: "₹ 5,000",
    status: "SUCCESS",
    date: "07 Jun 2026",
  },
  {
    id: 2,
    beneficiary: "Rahul Verma",
    amount: "₹ 2,500",
    status: "SUCCESS",
    date: "06 Jun 2026",
  },
  {
    id: 3,
    beneficiary: "Priya Singh",
    amount: "₹ 10,000",
    status: "PROCESSING",
    date: "05 Jun 2026",
  },
];

const RecentTransactionsTable = () => {
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
        <h2 className="font-bold text-lg">
          Recent Transactions
        </h2>
      </div>

      <table className="w-full">
        <thead>
          <tr
            className="
              bg-surface-secondary
              text-left
            "
          >
            <th className="px-6 py-4">
              Beneficiary
            </th>

            <th className="px-6 py-4">
              Amount
            </th>

            <th className="px-6 py-4">
              Status
            </th>

            <th className="px-6 py-4">
              Date
            </th>
          </tr>
        </thead>

        <tbody>
          {transactions.map((transaction) => (
            <tr
              key={transaction.id}
              className="
                border-t
                border-border-light
              "
            >
              <td className="px-6 py-4">
                {transaction.beneficiary}
              </td>

              <td className="px-6 py-4 font-medium">
                {transaction.amount}
              </td>

              <td className="px-6 py-4">
                {transaction.status}
              </td>

              <td className="px-6 py-4 text-text-secondary">
                {transaction.date}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default RecentTransactionsTable;