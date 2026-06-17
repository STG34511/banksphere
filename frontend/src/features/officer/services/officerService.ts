// features/officer/services/officerService.ts

import { apiClient } from "../../../services/apiClient";
import type { ApiResponse } from "../../../types/ApiResponse";
import type { PageResponse } from "../../../types/PageResponse";
import type { ApplicationDetailsResponse } from "../types/ApplicationDetailsResponse";
import type { ApplicationSummary } from "../types/ApplicationSummary";

export async function getApplications(
  page = 0,
  size = 10,
  status = "PENDING"
): Promise<ApiResponse<PageResponse<ApplicationSummary>>> {
  const response = await apiClient.get(
    "/officer/applications",
    {
      params: {
        page,
        size,
        status,
      },
    }
  );

  return response.data;
}

export async function getApplicationDetails(reference: string): Promise<ApiResponse<ApplicationDetailsResponse>> {
  const response = await apiClient.get(
    `/officer/applications/${reference}`
  );

  return response.data;
}

export async function approveApplication(
  reference: string
) {
  const response = await apiClient.post(
    `/officer/applications/${reference}/approve`
  );

  return response.data;
}


export async function rejectApplication(
  reference: string,
  reason: string
) {
  const response = await apiClient.post(
    `/officer/applications/${reference}/reject`,
    {
      reason,
    }
  );

  return response.data;
}