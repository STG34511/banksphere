import { useMutation } from "@tanstack/react-query"
import type LoginRequest from "../types/LoginRequest"
import { login } from "../services/authService";

export const useLogin = () => {
    return useMutation({
        mutationFn: async (data: LoginRequest) => {
            return await login(data);
        }
    })
}