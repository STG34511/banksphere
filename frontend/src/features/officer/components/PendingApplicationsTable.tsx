import { Link } from "react-router-dom";

const applications = [
  {
    id: "APP000001",
    customerName: "Rahul Sharma",
    email: "rahul@email.com",
    date: "07 Jun 2026",
    status: "PENDING",
  },
  {
    id: "APP000002",
    customerName: "Priya Singh",
    email: "priya@email.com",
    date: "07 Jun 2026",
    status: "PENDING",
  },
  {
    id: "APP000003",
    customerName: "Amit Verma",
    email: "amit@email.com",
    date: "06 Jun 2026",
    status: "PENDING",
  },
];

const PendingApplicationsTable = () => {
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
            <th className="px-6 py-4">
              Reference
            </th>

            <th className="px-6 py-4">
              Customer
            </th>

            <th className="px-6 py-4">
              Email
            </th>

            <th className="px-6 py-4">
              Applied On
            </th>

            <th className="px-6 py-4">
              Status
            </th>

            <th className="px-6 py-4">
              Action
            </th>
          </tr>
        </thead>

        <tbody>
          {applications.map((application) => (
            <tr
              key={application.id}
              className="
                border-t
                border-border-light
              "
            >
              <td className="px-6 py-4 font-medium">
                {application.id}
              </td>

              <td className="px-6 py-4">
                {application.customerName}
              </td>

              <td className="px-6 py-4">
                {application.email}
              </td>

              <td className="px-6 py-4">
                {application.date}
              </td>

              <td className="px-6 py-4">
                <span className="text-warning">
                  {application.status}
                </span>
              </td>

              <td className="px-6 py-4">
                <Link
                  to={`/officer/applications/${application.id}`}
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
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PendingApplicationsTable;