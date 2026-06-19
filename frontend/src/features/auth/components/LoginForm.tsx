import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import type LoginRequest from "../types/LoginRequest";
import { useLogin } from "../hooks/useLogin";
import { toast } from "sonner";
import { useAuthStore } from "../../../app/store/authStore";

const LoginForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginRequest>();

  const { mutate: login, isPending } = useLogin();
  const loginUser = useAuthStore((state) => state.login);
  const navigate = useNavigate();

  const onSubmit = (data: LoginRequest) => {
    login(data, {
      onSuccess: (response) => {
        if (response.data.role !== "CUSTOMER") {
          toast.error("Please use the officer login page.");
          return;
        }
        loginUser(
          response.data.accessToken,
          response.data.username,
          response.data.role,
        );
        navigate("/dashboard", { replace: true });
        toast.success("Login successful");
      },
      onError: (error) => {
        const message =
          error instanceof Error
            ? error.message
            : "Please check your credentials and try again.";

        toast.error(`Login failed. ${message}`);
      },
    });
  };

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
        <p className="text-sm text-text-secondary font-medium">Welcome back</p>

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

      <form onSubmit={handleSubmit(onSubmit)} className="mt-10 space-y-6">
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
          {errors.username && (
            <div className="text-red-500 text-sm mb-2">
              {errors.username.message}
            </div>
          )}
          <input
            {...register("username", {
              required: "Username is required",
              maxLength: {
                value: 100,
                message: "Maximum 100 characters allowed",
              },
              minLength: {
                value: 3,
                message: "Minimum 3 characters required",
              },
            })}
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
          {errors.password && (
            <div className="text-red-500 text-sm mb-2">
              {errors.password.message}
            </div>
          )}

          <input
            {...register("password", {
              required: "Password is required",
              minLength: {
                value: 3,
                message: "Password must be at least 3 characters long",
              },
            })}
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
            <input type="checkbox" className="h-4 w-4" />
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
          disabled={isPending}
        >
          {isPending ? "Signing In..." : "Sign In Securely"}
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
        <div className="mt-6 text-center">
          <p className="text-sm text-text-secondary">
            Are you an officer?{" "}
            <Link
              to="/officer/login"
              className="text-accent font-semibold hover:underline"
            >
              Officer Login
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
