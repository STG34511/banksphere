export interface CreateApplicationRequest {
  firstName: string;
  lastName: string;
  email: string;
  mobileNumber: string;
  panNumber: string;
  aadhaarNumber: string;
  dateOfBirth: string;
  addressLine1: string;
  addressLine2?: string;
  city: string;
  state: string;
  postalCode: string;
}