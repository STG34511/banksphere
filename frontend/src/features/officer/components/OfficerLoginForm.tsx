const OfficerLoginForm = () => {
  return (
    <div
      className="
        w-full
        max-w-md
        surface-card
        p-8
      "
    >
      <div>
        <p className="text-sm text-text-secondary">
          Internal Access
        </p>

        <h1
          className="
            mt-2
            text-3xl
            font-bold
          "
        >
          Officer Login
        </h1>

        <p
          className="
            mt-3
            text-text-secondary
          "
        >
          Sign in to review customer applications
          and manage operational workflows.
        </p>
      </div>

      <form className="mt-8 space-y-6">
        <div>
          <label
            className="
              block
              mb-2
              text-sm
              font-semibold
            "
          >
            Employee ID
          </label>

          <input
            type="text"
            placeholder="Enter employee ID"
            className="h-12 px-4"
          />
        </div>

        <div>
          <label
            className="
              block
              mb-2
              text-sm
              font-semibold
            "
          >
            Password
          </label>

          <input
            type="password"
            placeholder="Enter password"
            className="h-12 px-4"
          />
        </div>

        <button
          type="submit"
          className="
            w-full
            h-12
            rounded-xl
            bg-primary
            text-white
            font-semibold
          "
        >
          Sign In
        </button>
      </form>
    </div>
  );
};

export default OfficerLoginForm;