import { useNavigate, useParams } from "react-router-dom";
import { useApplicationDtls } from "../hooks/useApplicationDtls";
import { formatDate } from "../../../app/utils/dateutils";
import { useState } from "react";
import { useRejectApplication } from "../hooks/useRejectApplication";
import { useApproveApplication } from "../hooks/useApproveApplication";
import { toast } from "sonner";

const ApplicationReviewPage = () => {
  const { reference } = useParams();

  const { data, isPending, isError } = useApplicationDtls(reference!);
  const application = data?.data;

  const [reason, setReason] = useState("");

  const approveMutation = useApproveApplication();

  const rejectMutation = useRejectApplication();

  const navigate = useNavigate();

  const handleApprove = () => {
    approveMutation.mutate(reference!, {
      onSuccess: () => {
        toast.success("Application approved successfully");

        navigate("/officer/applications");
      },

      onError: () => {
        toast.error("Failed to approve application");
      },
    });
  };

  const handleReject = () => {
    if (!reason.trim()) {
      toast.error("Rejection reason is required");
      return;
    }

    rejectMutation.mutate(
      {
        reference: reference!,
        reason,
      },
      {
        onSuccess: () => {
          toast.success("Application rejected successfully");

          navigate("/officer/applications");
        },

        onError: () => {
          toast.error("Failed to reject application");
        },
      },
    );
  };

  if (isPending) {
    return (
      <div className="p-6 text-center">
        <p>Loading application details...</p>
      </div>
    );
  }
  if (isError) {
    return (
      <div className="p-6 text-center text-error">
        <p>Error loading application details.</p>
      </div>
    );
  }

  return (
    <div className="space-y-8">
      {/* Header */}

      <div>
        <p className="text-sm text-text-secondary">Application Review</p>

        <h1
          className="
            mt-1
            text-3xl
            font-bold
          "
        >
          {application?.applicationReference || "Application Reference"}
        </h1>

        <p
          className="
            mt-2
            text-text-secondary
          "
        >
          Review customer onboarding details and decide whether to approve or
          reject the application.
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
          <p className="text-sm text-text-secondary">Current Status</p>

          <p
            className="
              mt-1
              font-semibold
              text-warning
            "
          >
            {application?.status || "Application Status"}
          </p>
        </div>

        <div>
          <p className="text-sm text-text-secondary">Submitted On</p>

          <p className="font-medium">
            {application ? formatDate(application.submittedAt) : "Submitted On"}
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
            value={application?.firstName || "First Name"}
          />

          <InfoField
            label="Last Name"
            value={application?.lastName || "Last Name"}
          />

          <InfoField
            label="Date Of Birth"
            value={
              application
                ? formatDate(application.dateOfBirth)
                : "Date Of Birth"
            }
          />

          <InfoField
            label="Phone Number"
            value={application?.mobileNumber || "Phone Number"}
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
            value={application?.email || "Email Address"}
          />

          <InfoField
            label="Residential Address"
            value={
              application?.address
                ? `${application.address.line1}, ${application.address.line2}, ${application.address.city}, ${application.address.state} - ${application.address.postalCode}`
                : "Residential Address"
            }
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
            value={application?.panNumber || "PAN Number"}
          />

          <InfoField
            label="Aadhaar Number"
            value={application?.aadharNumber || "Aadhaar Number"}
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
          onChange={(e) => setReason(e.target.value)}
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
          onClick={() => handleReject()}
          disabled={rejectMutation.isPending}
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
          {rejectMutation.isPending ? "Rejecting..." : "Reject Application"}
        </button>

        <button
          onClick={() => handleApprove()}
          disabled={approveMutation.isPending}
          className="
            px-6
            py-3
            rounded-xl
            bg-primary
            text-white
            font-semibold
          "
        >
          {approveMutation.isPending ? "Approving..." : "Approve Application"}
        </button>
      </div>
    </div>
  );
};

export default ApplicationReviewPage;

interface InfoFieldProps {
  label: string;
  value: string;
}

const InfoField = ({ label, value }: InfoFieldProps) => {
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
