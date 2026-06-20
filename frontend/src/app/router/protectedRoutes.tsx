import { Navigate, Outlet } from "react-router-dom";
import { useAuthStore } from "../store/authStore";

export function ProtectedRoute() {
  const token = useAuthStore((state) => state.accessToken);

  const initialized = useAuthStore((state) => state.initialized);

  if (!initialized) {
    return null;
  }

  return token ? <Outlet /> : <Navigate to="/login" replace />;
}

export function OfficerRoute() {
  const role = useAuthStore((state) => state.role);

  if (role !== "OFFICER") {
    return <Navigate to="/dashboard" replace />;
  }

  return <Outlet />;
}

export function CustomerRoute() {
  const role = useAuthStore((state) => state.role);

  if (role !== "CUSTOMER") {
    return <Navigate to="/officer" replace />;
  }

  return <Outlet />;
}
