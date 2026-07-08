import AccountOverviewCard from "../components/AccountOverviewCard";
import DashboardStats from "../components/DashboardStats";
import QuickActions from "../components/QuickActions";
import RecentTransactionsTable from "../components/RecentTransactionsTable";
import { useDashboard } from "../hooks/useDashboard";
import { useRecentTransactions } from "../hooks/useRecentTransaction";

const DashboardPage = () => {
  const { data: dashboard, isLoading: dashboardLoading } = useDashboard();

  const { data: transactions, isLoading: transactionLoading } = useRecentTransactions();
  return (
    <div className="space-y-8">
      <DashboardStats  />

      <AccountOverviewCard dashboard={dashboard} dashboardLoading = {dashboardLoading} />

      <QuickActions />

      <RecentTransactionsTable transactionPage={transactions} isTransactionsLoading = {transactionLoading} />
    </div>
  );
};

export default DashboardPage;
