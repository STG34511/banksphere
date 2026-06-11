import { useMutation } from "@tanstack/react-query";
import { registerUser } from "../services/authService";
import type { CreateApplicationRequest } from "../types/CreateApplicationRequest";

export const useSubmitApplication = () => {
  return useMutation({
    mutationFn: async (formData: CreateApplicationRequest) => {
      return await registerUser(formData);
    },
  });
};
