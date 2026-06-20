import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";

import "./index.css";

import { router } from "./app/router";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Toaster } from "sonner";
import AppInitializer from "./app/providers/AppInitializer";

const queryClient = new QueryClient();

ReactDOM.createRoot(
  document.getElementById("root")!
).render(
  <React.StrictMode>
    <AppInitializer>
    <QueryClientProvider client={queryClient} >
    <RouterProvider router={router} />
    <Toaster richColors position="top-center" />
    </QueryClientProvider>
    </AppInitializer>
  </React.StrictMode>
);