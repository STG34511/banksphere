import { Link } from "react-router-dom";

const LoginForm = () => {
  return (
    <div
      className="
        w-full
        max-w-md
        surface-card
        p-8
        lg:p-10
      "
    >
      <div>
        <p className="text-sm text-text-secondary font-medium">
          Welcome back
        </p>

        <h1
          className="
            mt-2
            text-4xl
            font-bold
            tracking-tight
            text-text-primary
          "
        >
          Login to your account
        </h1>

        <p
          className="
            mt-3
            text-text-secondary
            leading-7
          "
        >
          Access your secure digital banking dashboard.
        </p>
      </div>

      <form className="mt-10 space-y-6">
        <div>
          <label
            className="
              block
              mb-2
              text-sm
              font-semibold
              text-text-primary
            "
          >
            Customer ID / Email
          </label>

          <input
            type="text"
            placeholder="Enter customer ID or email"
            className="h-12 px-4"
          />
        </div>

        <div>
          <div className="flex items-center justify-between mb-2">
            <label
              className="
                text-sm
                font-semibold
                text-text-primary
              "
            >
              Password
            </label>

            <button
              type="button"
              className="
                text-sm
                text-accent
                font-medium
                hover:underline
              "
            >
              Forgot password?
            </button>
          </div>

          <input
            type="password"
            placeholder="Enter password"
            className="h-12 px-4"
          />
        </div>

        <div>
          <label
            className="
              flex
              items-center
              gap-3
              text-sm
              text-text-secondary
              cursor-pointer
            "
          >
            <input
              type="checkbox"
              className="h-4 w-4"
            />

            Remember me
          </label>
        </div>

        <button
          type="submit"
          className="
            w-full
            h-12
            rounded-xl
            bg-primary
            text-text-inverse
            font-semibold
            hover:bg-primary-light
            transition-all
          "
        >
          Sign In Securely
        </button>
      </form>

      <div className="mt-8 pt-6 border-t border-border-light text-center">
        <p className="text-sm text-text-secondary">
          New customer?{" "}
          <Link
            to="/signup"
            className="
              text-accent
              font-semibold
              hover:underline
            "
          >
            Request New Account
          </Link>
        </p>
      </div>
    </div>
  );
};

export default LoginForm;