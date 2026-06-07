import OfficerStats from "../components/OfficerStats";
import PendingApplicationsTable from "../components/PendingApplicationsTable";

const OfficerDashboardPage = () => {
  return (
    <div className="space-y-8">
      <div>
        <p className="text-sm text-text-secondary">
          Operations Portal
        </p>

        <h1
          className="
            mt-1
            text-3xl
            font-bold
          "
        >
          Officer Dashboard
        </h1>

        <p
          className="
            mt-2
            text-text-secondary
          "
        >
          Review customer onboarding requests,
          compliance reviews and daily activity.
        </p>
      </div>

      <OfficerStats />

      <PendingApplicationsTable />
    </div>
  );
};

export default OfficerDashboardPage