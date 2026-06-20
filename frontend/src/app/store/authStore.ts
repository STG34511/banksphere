import { create } from "zustand";
import { persist } from "zustand/middleware";
import { getCurrentUser } from "../../features/auth/services/authService";

interface AuthState {
  accessToken: string | null;
  username: string | null;
  role: string | null;
  initialized: boolean;

  login: (token: string, username: string, role: string) => void;

  logout: () => void;

  initialize: () => Promise<void>;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set, get) => ({
      accessToken: null,
      username: null,
      role: null,
      initialized: false,

      login: (
        accessToken,
        username,
        role
      ) => {
        set({
          accessToken,
          username,
          role,
          initialized: true,
        });
      },

      logout: () => {
        localStorage.removeItem("auth-storage");
        set({
          accessToken: null,
          username: null,
          role: null,
          initialized: true,
        });
      },

      initialize: async () => {
        const token = get().accessToken;

        if (!token) {
          set({
            initialized: true,
          });

          return;
        }

        try {
          const response =
            await getCurrentUser();

          set({
            username:
              response.data.username,
            role:
              response.data.role,
            initialized: true,
          });
        } catch {
          get().logout();
        }
      },
    }),
    {
      name: "auth-storage",
    }
  )
);