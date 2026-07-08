import { useQuery } from "@tanstack/react-query";
import { fetchDashboardData } from "../services/dashboardApi";

export const useDashboard = () => {
  return useQuery({
    queryKey: ["dashboard"],
    queryFn: fetchDashboardData,
  });
};