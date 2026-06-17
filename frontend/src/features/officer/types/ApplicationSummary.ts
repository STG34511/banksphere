
export interface ApplicationSummary {
  applicationReference: string;
  fullName: string;
  email: string;
  status: "PENDING" | "APPROVED" | "REJECTED";
  submittedAt: string;
}