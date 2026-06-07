import StatCard from "../../dashboard/components/StatCard";

const OfficerStats = () => {
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
        title="Pending Applications"
        value="14"
      />

      <StatCard
        title="Approved Today"
        value="8"
      />

      <StatCard
        title="Flagged Transactions"
        value="3"
      />

      <StatCard
        title="Transactions Today"
        value="127"
      />
    </div>
  );
};

export default OfficerStats;