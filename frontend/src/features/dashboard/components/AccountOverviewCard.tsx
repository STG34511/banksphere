import type DashboardResponse from "../types/DashboardResponse";
import CircularProgress from "@mui/material/CircularProgress";

const AccountOverviewCard = ({
  dashboard,
  dashboardLoading,
}: {
  dashboard: DashboardResponse | undefined;
  dashboardLoading: boolean;
}) => {
  const primaryAccount = dashboard?.accounts?.find(
    (account) => account.primary,
  );
  if (dashboardLoading) {
    return (
      <div className="surface-card p-8 flex justify-center items-center min-h-50">
        <CircularProgress size={32} />
      </div>
    );
  }
  return (
    <div className="surface-card p-8">
      <p className="text-sm text-text-secondary">Primary Account</p>

      <h2
        className="
          mt-3
          text-4xl
          font-bold
        "
      >
        {primaryAccount?.accountNumber || "N/A"}
      </h2>

      <div
        className="
          mt-8
          flex
          gap-10
        "
      >
        <div>
          <p className="text-sm text-text-secondary">Status</p>

          <p className="font-semibold credit-text">
            {primaryAccount?.status || "N/A"}
          </p>
        </div>

        <div>
          <p className="text-sm text-text-secondary">Customer ID</p>

          <p className="font-semibold">{dashboard?.customerId || "N/A"}</p>
        </div>
      </div>
    </div>
  );
};

export default AccountOverviewCard;
