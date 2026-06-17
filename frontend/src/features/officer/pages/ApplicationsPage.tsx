import PendingApplicationsTable from "../components/PendingApplicationsTable";

const ApplicationsPage = () => {
  return (
    <div className="space-y-8">

      <div>
        <p className="text-sm text-text-secondary">
          Officer Dashboard
        </p>

        <h1
          className="
            mt-1
            text-3xl
            font-bold
          "
        >
          Applications
        </h1>

        <p
          className="
            mt-2
            text-text-secondary
          "
        >
          Review and process customer onboarding
          applications.
        </p>
      </div>

      <PendingApplicationsTable />

    </div>
  );
};

export default ApplicationsPage;