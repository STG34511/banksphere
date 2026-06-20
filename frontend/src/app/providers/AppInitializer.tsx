import { useEffect, type PropsWithChildren } from "react";

import { useAuthStore } from "../store/authStore";

const AppInitializer = ({ children }: PropsWithChildren) => {
  const initialize = useAuthStore((state) => state.initialize);

  const initialized = useAuthStore((state) => state.initialized);

  useEffect(() => {
    initialize();
  }, [initialize]);

  if (!initialized) {
    return <div>Loading...</div>;
  }

  return children;
};

export default AppInitializer;
