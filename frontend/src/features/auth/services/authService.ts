import { apiClient } from "../../../services/apiClient";
import type { CreateApplicationRequest } from "../types/CreateApplicationRequest";
import type { ApiResponse } from "../../../types/ApiResponse";
import type { ApplicationResponse } from "../types/ApplicationResponse";

export async function registerUser(
  userData: CreateApplicationRequest,
): Promise<ApiResponse<ApplicationResponse>> {
  const response = await apiClient.post("/applications", userData);

  return response.data;
}
