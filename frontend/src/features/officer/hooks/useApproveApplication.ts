import { useMutation, useQueryClient } from "@tanstack/react-query";
import { approveApplication } from "../services/officerService";

export function useApproveApplication() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: approveApplication,

    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["applications"],
      });
    },
  });
}