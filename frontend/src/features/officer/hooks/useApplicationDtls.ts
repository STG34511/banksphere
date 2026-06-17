import { useQuery } from "@tanstack/react-query";
import { getApplicationDetails } from "../services/officerService";

export function useApplicationDtls(reference: string) {
  return useQuery({
    queryKey: ["application", reference],
    queryFn: () => getApplicationDetails(reference),
    enabled: !!reference,
  });
}