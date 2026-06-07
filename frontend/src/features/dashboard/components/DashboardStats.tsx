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
        title="Transactions"
        value="18"
      />

      <StatCard
        title="Beneficiaries"
        value="5"
      />

      <StatCard
        title="Pending Reviews"
        value="2"
      />
    </div>
  );
};

export default DashboardStats;