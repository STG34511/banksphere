import { apiClient } from "../../../services/apiClient";
import { type ApiResponse } from "../../../types/ApiResponse";
import type DashboardResponse from "../types/DashboardResponse";
import type { PageResponse } from "../../../types/PageResponse";
import type {TransactionSummaryResponse} from "../types/TransactionSummaryResponse";

export async function fetchDashboardData(): Promise<DashboardResponse> {
    const response = await apiClient.get<ApiResponse<DashboardResponse>>('/customer/dashboard');
    return response.data.data;
}

export async function fetchRecentTransactions(): Promise<PageResponse<TransactionSummaryResponse>> {
    const response = await apiClient.get<ApiResponse<PageResponse<TransactionSummaryResponse>>>('/customer/transactions');
    return response.data.data;
}