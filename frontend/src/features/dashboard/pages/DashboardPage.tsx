import AccountOverviewCard from "../components/AccountOverviewCard";
import DashboardStats from "../components/DashboardStats";
import QuickActions from "../components/QuickActions";
import RecentTransactionsTable from "../components/RecentTransactionsTable";

const DashboardPage = () => {
  return (
    <div className="space-y-8">
      <DashboardStats />

      <AccountOverviewCard />

      <QuickActions />

      <RecentTransactionsTable />
    </div>
  );
};

export default DashboardPage;