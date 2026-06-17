import {
  createBrowserRouter,
  Navigate,
} from "react-router-dom";

import RootLayout from "../layout/RootLayout";
import AuthLayout from "../layout/AuthLayout";
import DashboardLayout from "../layout/DashboardLayout";
import LoginPage from "../../features/auth/pages/LoginPage";
import RegisterPage from "../../features/auth/pages/RegisterPage";
import DashboardPage from "../../features/dashboard/pages/DashboardPage";
import OfficerLoginPage from "../../features/officer/pages/OfficerLoginPage";
import ApplicationReviewPage from "../../features/officer/pages/ApplicationReviewPage";
import TransactionReviewsPage from "../../features/officer/pages/TransactionReviewsPage";
import ApplicationsPage from "../../features/officer/pages/ApplicationsPage";
import ReportsPage from "../../features/officer/pages/ReportsPage";
import OfficerDashboardPage from "../../features/officer/pages/OfficerDashboardPage";
import OfficerLayout from "../layout/OfficerLayout";
/* ==========================================================================
   AUTH PAGES
========================================================================== */


const ChangePasswordPage = () => {
  return <div>Change Password Page</div>;
};

/* ==========================================================================
   CUSTOMER PAGES
========================================================================== */


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
            element: <RegisterPage />,
          },

          {
            path: "change-password",
            element: <ChangePasswordPage />,
          },
          {
            path: "officer/login",
            element: <OfficerLoginPage />,
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
        path: "officer",
        element: <OfficerLayout />,

        children: [
          {
            index: true,
            element: <OfficerDashboardPage />,
          },

          {
            path: "applications",
            element: <ApplicationsPage />,
          },

          {
            path: "applications/:reference",
            element: <ApplicationReviewPage />,
          },
          {
            path: "transaction-reviews",
            element: <TransactionReviewsPage />,
          },

          {
            path: "reports",
            element: <ReportsPage />,
          },
        ],
      },
    ],
  },
]);