import { NavLink } from "react-router-dom";

const navigationItems = [
  {
    label: "Dashboard",
    path: "/officer",
    icon: "📊",
  },
  {
    label: "Applications",
    path: "/officer/applications",
    icon: "📋",
  },
  {
    label: "Transaction Reviews",
    path: "/officer/transaction-reviews",
    icon: "🛡️",
  },
  {
    label: "Reports",
    path: "/officer/reports",
    icon: "📈",
  },
];

const OfficerSidebar = () => {
  return (
    <aside
      className="
        w-72
        bg-surface
        border-r
        border-border-light
        flex
        flex-col
      "
    >
      <div
        className="
          h-20
          px-6
          border-b
          border-border-light
          flex
          items-center
          gap-4
        "
      >
        <div
          className="
            h-12
            w-12
            rounded-xl
            bg-primary
            text-white
            flex
            items-center
            justify-center
            font-bold
          "
        >
          BS
        </div>

        <div>
          <h2 className="font-bold">
            BankSphere
          </h2>

          <p className="text-xs text-text-secondary">
            Officer Portal
          </p>
        </div>
      </div>

      <nav className="flex-1 p-4">
        <div className="space-y-2">
          {navigationItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              end={item.path === "/officer"}
              className={({ isActive }) =>
                `
                flex
                items-center
                gap-3
                px-4
                py-3
                rounded-xl
                transition-all
                ${
                  isActive
                    ? "bg-primary text-white"
                    : "text-text-secondary hover:bg-surface-secondary"
                }
              `
              }
            >
              <span>{item.icon}</span>

              <span>{item.label}</span>
            </NavLink>
          ))}
        </div>
      </nav>

      <div
        className="
          p-4
          border-t
          border-border-light
        "
      >
        <button
          className="
            w-full
            px-4
            py-3
            rounded-xl
            text-left
            text-danger
            hover:bg-red-50
          "
        >
          Logout
        </button>
      </div>
    </aside>
  );
};

export default OfficerSidebar;