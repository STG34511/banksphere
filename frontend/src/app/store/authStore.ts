import { create } from "zustand";
import { persist } from "zustand/middleware";

interface AuthState {
  accessToken: string | null;
  username: string | null;
  role: string | null;

  login: (
    token: string,
    username: string,
    role: string
  ) => void;

  logout: () => void;
}

export const useAuthStore =
  create<AuthState>()(
    persist(
      (set) => ({
        accessToken: null,
        username: null,
        role: null,

        login: (
          accessToken,
          username,
          role
        ) => {
          set({
            accessToken,
            username,
            role,
          });
        },

        logout: () => {
          set({
            accessToken: null,
            username: null,
            role: null,
          });
        },
      }),
      {
        name: "auth-storage",
      }
    )
  );