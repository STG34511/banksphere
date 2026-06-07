import { Outlet } from "react-router-dom";
import OfficerSidebar from "./OfficerSidebar";
import TopNavbar from "./TopNavbar";

const OfficerLayout = () => {
  return (
    <div
      className="
        h-screen
        bg-background
        flex
        overflow-hidden
      "
    >
      <OfficerSidebar />

      <div
        className="
          flex-1
          flex
          flex-col
          overflow-hidden
        "
      >
        <TopNavbar />

        <main
          className="
            flex-1
            overflow-y-auto
            p-6
            lg:p-8
          "
        >
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default OfficerLayout;