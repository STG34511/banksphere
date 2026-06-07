import { NavLink } from "react-router-dom";

const navigationItems = [
  {
    label: "Dashboard",
    path: "/dashboard",
    icon: "🏠",
  },
  {
    label: "Transfers",
    path: "/transfers",
    icon: "💸",
  },
  {
    label: "Beneficiaries",
    path: "/beneficiaries",
    icon: "👥",
  },
  {
    label: "Transactions",
    path: "/transactions",
    icon: "📄",
  },
  {
    label: "Enquiries",
    path: "/enquiries",
    icon: "❓",
  },
  {
    label: "Settings",
    path: "/settings",
    icon: "⚙️",
  },
];

const Sidebar = () => {
  return (
    <aside
      className="
        w-72
        border-r
        border-border-light
        bg-surface
        flex
        flex-col
      "
    >
      {/* Brand */}

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
            flex
            items-center
            justify-center
            text-white
            font-bold
          "
        >
          BS
        </div>

        <div>
          <h2 className="font-bold text-lg">
            BankSphere
          </h2>

          <p className="text-xs text-text-secondary">
            Digital Banking
          </p>
        </div>
      </div>

      {/* Navigation */}

      <nav className="flex-1 p-4">
        <div className="space-y-2">
          {navigationItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
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

              <span className="font-medium">
                {item.label}
              </span>
            </NavLink>
          ))}
        </div>
      </nav>

      {/* Footer */}

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
            transition-all
          "
        >
          Logout
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;