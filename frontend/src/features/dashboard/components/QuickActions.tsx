const actions = [
  "Transfer Money",
  "Add Beneficiary",
  "View Transactions",
  "Raise Enquiry",
];

const QuickActions = () => {
  return (
    <div>
      <h2
        className="
          text-xl
          font-bold
          mb-4
        "
      >
        Quick Actions
      </h2>

      <div
        className="
          grid
          md:grid-cols-2
          xl:grid-cols-4
          gap-4
        "
      >
        {actions.map((action) => (
          <button
            key={action}
            className="
              surface-card
              p-6
              text-left
              hover:shadow-lg
              transition-all
            "
          >
            <p className="font-semibold">
              {action}
            </p>
          </button>
        ))}
      </div>
    </div>
  );
};

export default QuickActions;