import { Navigate, Outlet } from "react-router-dom";
import { useAuthStore } from "../store/authStore";

export function ProtectedRoute() {
  const token = useAuthStore((state) => state.accessToken);

  return token ? <Outlet /> : <Navigate to="/login" replace />;
}

export function OfficerRoute() {
  const role = useAuthStore((state) => state.role);

  return role === "OFFICER" ? (
    <Outlet />
  ) : (
    <Navigate to="/officer/dashboard" replace />
  );
}

export function CustomerRoute() {
  const role = useAuthStore((state) => state.role);

  return role === "CUSTOMER" ? (
    <Outlet />
  ) : (
    <Navigate to="/dashboard" replace />
  );
}
