import { useQuery } from "@tanstack/react-query";
import { fetchRecentTransactions } from "../services/dashboardApi";

export const useRecentTransactions = () => {
  return useQuery({
    queryKey: ["recent-transactions"],
    queryFn: fetchRecentTransactions,
  });
};