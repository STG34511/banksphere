import { useMutation, useQueryClient } from "@tanstack/react-query";
import { rejectApplication } from "../services/officerService";

export function useRejectApplication() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({
      reference,
      reason,
    }: {
      reference: string;
      reason: string;
    }) => rejectApplication(reference, reason),

    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["applications"],
      });
    },
  });
}