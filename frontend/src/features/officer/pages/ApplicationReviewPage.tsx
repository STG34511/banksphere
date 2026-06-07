const ApplicationReviewPage = () => {
  return (
    <div className="space-y-8">
      {/* Header */}

      <div>
        <p className="text-sm text-text-secondary">
          Application Review
        </p>

        <h1
          className="
            mt-1
            text-3xl
            font-bold
          "
        >
          APP000001
        </h1>

        <p
          className="
            mt-2
            text-text-secondary
          "
        >
          Review customer onboarding details and
          decide whether to approve or reject the
          application.
        </p>
      </div>

      {/* Status */}

      <div
        className="
          surface-card
          p-6
          flex
          items-center
          justify-between
        "
      >
        <div>
          <p className="text-sm text-text-secondary">
            Current Status
          </p>

          <p
            className="
              mt-1
              font-semibold
              text-warning
            "
          >
            PENDING
          </p>
        </div>

        <div>
          <p className="text-sm text-text-secondary">
            Submitted On
          </p>

          <p className="font-medium">
            07 Jun 2026
          </p>
        </div>
      </div>

      {/* Personal Information */}

      <div className="surface-card p-8">
        <h2
          className="
            text-xl
            font-bold
            mb-6
          "
        >
          Personal Information
        </h2>

        <div
          className="
            grid
            md:grid-cols-2
            gap-6
          "
        >
          <InfoField
            label="First Name"
            value="Rahul"
          />

          <InfoField
            label="Last Name"
            value="Sharma"
          />

          <InfoField
            label="Date Of Birth"
            value="14 Jan 1998"
          />

          <InfoField
            label="Phone Number"
            value="+91 9876543210"
          />
        </div>
      </div>

      {/* Contact Information */}

      <div className="surface-card p-8">
        <h2
          className="
            text-xl
            font-bold
            mb-6
          "
        >
          Contact Information
        </h2>

        <div className="space-y-6">
          <InfoField
            label="Email Address"
            value="rahul@email.com"
          />

          <InfoField
            label="Residential Address"
            value="Mumbai, Maharashtra, India"
          />
        </div>
      </div>

      {/* Identity Information */}

      <div className="surface-card p-8">
        <h2
          className="
            text-xl
            font-bold
            mb-6
          "
        >
          Identity Information
        </h2>

        <div
          className="
            grid
            md:grid-cols-2
            gap-6
          "
        >
          <InfoField
            label="PAN Number"
            value="ABCDE1234F"
          />

          <InfoField
            label="Aadhaar Number"
            value="XXXX XXXX 1234"
          />
        </div>
      </div>

      {/* Remarks */}

      <div className="surface-card p-8">
        <h2
          className="
            text-xl
            font-bold
            mb-4
          "
        >
          Officer Remarks
        </h2>

        <textarea
          rows={4}
          placeholder="Enter remarks if rejecting application..."
          className="
            px-4
            py-3
            resize-none
          "
        />
      </div>

      {/* Actions */}

      <div
        className="
          flex
          justify-end
          gap-4
        "
      >
        <button
          className="
            px-6
            py-3
            rounded-xl
            border
            border-danger
            text-danger
            font-semibold
            hover:bg-red-50
          "
        >
          Reject
        </button>

        <button
          className="
            px-6
            py-3
            rounded-xl
            bg-primary
            text-white
            font-semibold
          "
        >
          Approve Application
        </button>
      </div>
    </div>
  );
};

export default ApplicationReviewPage;

/* -------------------------------------------------------------------------- */
/* Helper Component */
/* -------------------------------------------------------------------------- */

interface InfoFieldProps {
  label: string;
  value: string;
}

const InfoField = ({
  label,
  value,
}: InfoFieldProps) => {
  return (
    <div>
      <p
        className="
          text-sm
          text-text-secondary
        "
      >
        {label}
      </p>

      <p
        className="
          mt-1
          font-medium
        "
      >
        {value}
      </p>
    </div>
  );
};