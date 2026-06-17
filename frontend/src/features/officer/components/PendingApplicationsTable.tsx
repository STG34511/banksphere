import { Link } from "react-router-dom";
import { formatDate } from "../../../app/utils/dateutils";
import { useApplications } from "../hooks/useApplications";

const PendingApplicationsTable = () => {
  const { data, isPending, isError } = useApplications(0);
  const applications = data?.data.content || [];

  if (isPending) {
    return <div className="p-6 text-center">Loading applications...</div>;
  }

  if (isError) {
    return (
      <div className="p-6 text-center text-error">
        Error loading applications.
      </div>
    );
  }

  return (
    <div
      className="
        surface-card
        overflow-hidden
      "
    >
      <div
        className="
          px-6
          py-5
          border-b
          border-border-light
        "
      >
        <h2
          className="
            text-lg
            font-bold
          "
        >
          Pending Applications
        </h2>
      </div>

      <table className="w-full">
        <thead>
          <tr
            className="
              bg-surface-secondary
              text-left
            "
          >
            <th className="px-6 py-4">Reference</th>

            <th className="px-6 py-4">Customer</th>

            <th className="px-6 py-4">Email</th>

            <th className="px-6 py-4">Applied On</th>

            <th className="px-6 py-4">Status</th>

            <th className="px-6 py-4">Action</th>
          </tr>
        </thead>

        <tbody>
          {applications.length === 0 ? (
            <tr>
              <td
                colSpan={6}
                className="
          px-6
          py-12
          text-center
          text-text-secondary
        "
              >
                No pending applications to review.
              </td>
            </tr>
          ) : (
            applications.map((application) => (
              <tr
                key={application.applicationReference}
                className="
                border-t
                border-border-light
              "
              >
                <td className="px-6 py-4 font-medium">
                  {application.applicationReference}
                </td>

                <td className="px-6 py-4">{application.fullName}</td>

                <td className="px-6 py-4">{application.email}</td>

                <td className="px-6 py-4">
                  {formatDate(application.submittedAt)}
                </td>

                <td className="px-6 py-4">
                  <span className="text-warning">{application.status}</span>
                </td>

                <td className="px-6 py-4">
                  <Link
                    to={`/officer/applications/${application.applicationReference}`}
                    className="
                    text-accent
                    font-medium
                    hover:underline
                  "
                  >
                    Review
                  </Link>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default PendingApplicationsTable;
