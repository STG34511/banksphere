import {
  createBrowserRouter,
  Navigate,
} from "react-router-dom";

import RootLayout from "../layout/RootLayout";
import AuthLayout from "../layout/AuthLayout";
import DashboardLayout from "../layout/DashboardLayout";
import LoginPage from "../../features/auth/pages/LoginPage";
/* ==========================================================================
   AUTH PAGES
========================================================================== */


const SignupPage = () => {
  return <div>Signup Page</div>;
};

const ChangePasswordPage = () => {
  return <div>Change Password Page</div>;
};

/* ==========================================================================
   CUSTOMER PAGES
========================================================================== */

const DashboardPage = () => {
  return <div>Customer Dashboard</div>;
};

const AccountsPage = () => {
  return <div>Accounts Page</div>;
};

const TransactionsPage = () => {
  return <div>Transactions Page</div>;
};

const BeneficiariesPage = () => {
  return <div>Beneficiaries Page</div>;
};

/* ==========================================================================
   ADMIN PAGES
========================================================================== */

const AdminDashboardPage = () => {
  return <div>Admin Dashboard</div>;
};

const OnboardingRequestsPage = () => {
  return <div>Onboarding Requests</div>;
};

const CustomersPage = () => {
  return <div>Customers Page</div>;
};


export const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,

    children: [
      {
        element: <AuthLayout />,

        children: [
          {
            index: true,
            element: <Navigate to="/login" replace />,
          },

          {
            path: "login",
            element: <LoginPage />,
          },

          {
            path: "signup",
            element: <SignupPage />,
          },

          {
            path: "change-password",
            element: <ChangePasswordPage />,
          },
        ],
      },

      {
        path: "dashboard",
        element: <DashboardLayout />,

        children: [
          {
            index: true,
            element: <DashboardPage />,
          },

          {
            path: "accounts",
            element: <AccountsPage />,
          },

          {
            path: "transactions",
            element: <TransactionsPage />,
          },

          {
            path: "beneficiaries",
            element: <BeneficiariesPage />,
          },
        ],
      },

      {
        path: "admin",
        element: <DashboardLayout />,

        children: [
          {
            index: true,
            element: <AdminDashboardPage />,
          },

          {
            path: "onboarding-requests",
            element: <OnboardingRequestsPage />,
          },

          {
            path: "customers",
            element: <CustomersPage />,
          },
        ],
      },
    ],
  },
]);