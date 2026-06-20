import { apiClient } from "../../../services/apiClient";
import type { CreateApplicationRequest } from "../types/CreateApplicationRequest";
import type { ApiResponse } from "../../../types/ApiResponse";
import type { ApplicationResponse } from "../types/ApplicationResponse";
import type LoginResponse from "../types/LoginResponse";

export async function registerUser(
  userData: CreateApplicationRequest,
): Promise<ApiResponse<ApplicationResponse>> {
  const response = await apiClient.post("/applications", userData);

  return response.data;
}

export async function login(credentials: {
  username: string;
  password: string;
}): Promise<ApiResponse<LoginResponse>> {
  const response = await apiClient.post("/auth/login", credentials);

  return response.data;
}

export async function getCurrentUser(): Promise<
  ApiResponse<LoginResponse>
> {
  const response = await apiClient.get("/auth/me");
  return response.data;
}
