export interface ApplicationDetailsResponse {
  applicationReference: string;
  firstName: string;
  lastName: string;
  email: string;
  mobileNumber: string;
  panNumber: string;
  aadharNumber: string;
  status: "PENDING" | "APPROVED" | "REJECTED";
  submittedAt: string;
  dateOfBirth: string;
  address: {
    line1: string;
    line2: string;
    city: string;
    state: string;
    postalCode: string;
  };
}