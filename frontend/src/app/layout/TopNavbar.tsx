const TopNavbar = () => {
  return (
    <header
      className="
        h-20
        bg-surface
        border-b
        border-border-light
        px-8
        flex
        items-center
        justify-between
      "
    >
      {/* Left */}

      <div>
        <p className="text-sm text-text-secondary">
          Welcome Back
        </p>

        <h1
          className="
            text-2xl
            font-bold
            text-text-primary
          "
        >
          Shivansh Pandey
        </h1>
      </div>

      {/* Right */}

      <div className="flex items-center gap-6">
        <button
          className="
            relative
            h-10
            w-10
            rounded-full
            bg-surface-secondary
            flex
            items-center
            justify-center
          "
        >
          🔔
        </button>

        <div className="text-right">
          <p
            className="
              text-sm
              font-semibold
            "
          >
            Customer ID
          </p>

          <p
            className="
              text-sm
              text-text-secondary
            "
          >
            CUS000001
          </p>
        </div>
      </div>
    </header>
  );
};

export default TopNavbar;