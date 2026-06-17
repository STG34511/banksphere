import { useQuery } from "@tanstack/react-query";
import { getApplications } from "../services/officerService";

export function useApplications(
  page = 0,
  size = 10,
  status = "PENDING"
) {
  return useQuery({
    queryKey: ["applications", page, size, status],
    queryFn: () =>
      getApplications(page, size, status),
  },
);
}