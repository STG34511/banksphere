import StatCard from "./StatCard";

const DashboardStats = () => {
  return (
    <div
      className="
        grid
        md:grid-cols-2
        xl:grid-cols-4
        gap-4
      "
    >
      <StatCard
        title="Available Balance"
        value="₹ 1,24,583"
      />

      <StatCard
        title="Transactions This Month"
        value="18"
      />

      <StatCard
        title="Total Debit this Month"
        value="₹ 24,583"
      />
      <StatCard
        title="Total Credit this Month"
        value="₹ 1,00,000"
      />

    </div>
  );
};

export default DashboardStats;