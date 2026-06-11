import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import type { CreateApplicationRequest } from "../types/CreateApplicationRequest";
import { useSubmitApplication } from "../hooks/useSubmitApplication";
import { toast } from "sonner";

const RegistrationForm = () => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<CreateApplicationRequest>();

  const submitApplicationMutation = useSubmitApplication();

  const onSubmit = (data: CreateApplicationRequest) => {
    submitApplicationMutation.mutate(data, {
      onSuccess: (response) => {
        toast.success(
          "Application submitted successfully! Reference: " +
            response.data.applicationReference,
        );
        reset();
      },
      onError: (error) => {
        toast.error(
          "Failed to submit application: " +
            (error instanceof Error ? error.message : "Unknown error"),
        );
      },
    });
  };

  return (
    <div
      className="
     w-full
     max-w-2xl
     surface-card
     p-8
     lg:p-10
   "
    >
      {/* Header */}
      <div>
        <p className="text-sm text-text-secondary font-medium">New Customer</p>

        <h1
          className="
        mt-2
        text-4xl
        font-bold
        tracking-tight
        text-text-primary
      "
        >
          Open Your Account
        </h1>

        <p
          className="
        mt-3
        text-text-secondary
        leading-7
      "
        >
          Complete the application below. An officer will review your
          information and create your account upon approval.
        </p>
      </div>

      <form className="mt-10" onSubmit={handleSubmit(onSubmit)}>
        <div className="grid md:grid-cols-2 gap-6">
          <div>
            <label className="block mb-2 text-sm font-semibold">
              First Name
            </label>
            {errors.firstName && (
              <div className="text-red-500 text-sm mb-2">
                {errors.firstName.message}
              </div>
            )}
            <input
              {...register("firstName", {
                required: "First name is required",
                maxLength: {
                  value: 100,
                  message: "Maximum 100 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter first name"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Last Name
            </label>
            {errors.lastName && (
              <div className="text-red-500 text-sm mb-2">
                {errors.lastName.message}
              </div>
            )}
            <input
              {...register("lastName", {
                required: "Last name is required",
                maxLength: {
                  value: 100,
                  message: "Maximum 100 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter last name"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Date Of Birth
            </label>
            {errors.dateOfBirth && (
              <div className="text-red-500 text-sm mb-2">
                {errors.dateOfBirth.message}
              </div>
            )}
            <input
              {...register("dateOfBirth", {
                required: "Date of birth is required",
              })}
              type="date"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Phone Number
            </label>
            {errors.mobileNumber && (
              <div className="text-red-500 text-sm mb-2">
                {errors.mobileNumber.message}
              </div>
            )}
            <input
              {...register("mobileNumber", {
                required: "Mobile number is required",
                pattern: {
                  value: /^[0-9]{10}$/,
                  message: "Mobile number must be exactly 10 digits",
                },
              })}
              type="tel"
              placeholder="Enter phone number"
              className="h-12 px-4"
            />
          </div>

          <div className="md:col-span-2">
            <label className="block mb-2 text-sm font-semibold">
              Email Address
            </label>
            {errors.email && (
              <div className="text-red-500 text-sm mb-2">
                {errors.email.message}
              </div>
            )}
            <input
              {...register("email", {
                required: "Email is required",
                pattern: {
                  value: /^[^\s@]+@[^\s@]+.[^\s@]+$/,
                  message: "Invalid email address",
                },
              })}
              type="email"
              placeholder="Enter email address"
              className="h-12 px-4"
            />
          </div>

          <div className="md:col-span-2">
            <label className="block mb-2 text-sm font-semibold">
              Address Line 1
            </label>
            {errors.addressLine1 && (
              <div className="text-red-500 text-sm mb-2">
                {errors.addressLine1.message}
              </div>
            )}
            <input
              {...register("addressLine1", {
                required: "Address line 1 is required",
                maxLength: {
                  value: 255,
                  message: "Maximum 255 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter address line 1"
              className="h-12 px-4"
            />
          </div>

          <div className="md:col-span-2">
            <label className="block mb-2 text-sm font-semibold">
              Address Line 2
            </label>
            {errors.addressLine2 && (
              <div className="text-red-500 text-sm mb-2">
                {errors.addressLine2.message}
              </div>
            )}
            <input
              {...register("addressLine2", {
                required: "Address line 1 is required",
                maxLength: {
                  value: 255,
                  message: "Maximum 255 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter address line 2"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">City</label>
            {errors.city && (
              <div className="text-red-500 text-sm mb-2">
                {errors.city.message}
              </div>
            )}
            <input
              {...register("city", {
                required: "City is required",
                maxLength: {
                  value: 100,
                  message: "Maximum 100 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter city"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">State</label>
            {errors.state && (
              <div className="text-red-500 text-sm mb-2">
                {errors.state.message}
              </div>
            )}
            <input
              {...register("state", {
                required: "State is required",
                maxLength: {
                  value: 100,
                  message: "Maximum 100 characters allowed",
                },
              })}
              type="text"
              placeholder="Enter state"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Postal Code
            </label>
            {errors.postalCode && (
              <div className="text-red-500 text-sm mb-2">
                {errors.postalCode.message}
              </div>
            )}
            <input
              {...register("postalCode", {
                required: "Postal code is required",
                pattern: {
                  value: /^[0-9]{6}$/,
                  message: "Invalid postal code",
                },
              })}
              type="text"
              placeholder="400001"
              className="h-12 px-4"
            />
          </div>
          <div>
            <label className="block mb-2 text-sm font-semibold">
              PAN Number
            </label>
            {errors.panNumber && (
              <div className="text-red-500 text-sm mb-2">
                {errors.panNumber.message}
              </div>
            )}

            <input
              {...register("panNumber", {
                required: "PAN number is required",
                pattern: {
                  value: /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/,
                  message: "Invalid PAN number",
                },
              })}
              type="text"
              placeholder="ABCDE1234F"
              className="h-12 px-4 uppercase"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Aadhaar Number
            </label>
            {errors.aadhaarNumber && (
              <div className="text-red-500 text-sm mb-2">
                {errors.aadhaarNumber.message}
              </div>
            )}
            <input
              {...register("aadhaarNumber", {
                required: "Aadhaar number is required",
                pattern: {
                  value: /^[0-9]{12}$/,
                  message: "Invalid Aadhaar number",
                },
              })}
              type="text"
              placeholder="123456789012"
              className="h-12 px-4"
            />
          </div>
        </div>

        <div className="mt-8">
          <label
            className="
          flex
          items-start
          gap-3
          text-sm
          text-text-secondary
          cursor-pointer
        "
          >
            <input
              type="checkbox"
              className="
            h-4
            w-4
            mt-1
            shrink-0
          "
            />

            <span>
              I certify that the information provided is accurate and may be
              used for identity verification and account creation.
            </span>
          </label>
        </div>

        <button
          type="submit"
          className="
        mt-8
        w-full
        h-12
        rounded-xl
        bg-primary
        text-text-inverse
        font-semibold
        hover:bg-primary-light
        transition-all
      "
          disabled={submitApplicationMutation.isPending}
        >
          {submitApplicationMutation.isPending
            ? "Submitting..."
            : "Submit Application"}
        </button>
      </form>

      <div className="mt-8 pt-6 border-t border-border-light text-center">
        <p className="text-sm text-text-secondary">
          Already have an account?{" "}
          <Link
            to="/login"
            className="
          text-accent
          font-semibold
          hover:underline
        "
          >
            Sign In
          </Link>
        </p>
      </div>
    </div>
  );
};

export default RegistrationForm;
