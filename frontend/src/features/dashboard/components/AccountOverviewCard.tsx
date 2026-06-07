const AccountOverviewCard = () => {
  return (
    <div className="surface-card p-8">
      <p className="text-sm text-text-secondary">
        Primary Account
      </p>

      <h2
        className="
          mt-3
          text-4xl
          font-bold
        "
      >
        BS000001
      </h2>

      <div
        className="
          mt-8
          flex
          gap-10
        "
      >
        <div>
          <p className="text-sm text-text-secondary">
            Status
          </p>

          <p className="font-semibold credit-text">
            ACTIVE
          </p>
        </div>

        <div>
          <p className="text-sm text-text-secondary">
            Customer ID
          </p>

          <p className="font-semibold">
            CUS000001
          </p>
        </div>
      </div>
    </div>
  );
};

export default AccountOverviewCard;