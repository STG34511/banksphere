import { Link } from "react-router-dom";

const RegistrationForm = () => {
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
        <p className="text-sm text-text-secondary font-medium">
          New Customer
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
          Open Your Account
        </h1>

        <p
          className="
            mt-3
            text-text-secondary
            leading-7
          "
        >
          Complete the application below. An officer
          will review your information and create your
          account upon approval.
        </p>
      </div>

      {/* Form */}

      <form className="mt-10">
        <div className="grid md:grid-cols-2 gap-6">
          {/* Personal Information */}

          <div>
            <label className="block mb-2 text-sm font-semibold">
              First Name
            </label>

            <input
              type="text"
              placeholder="Enter first name"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Last Name
            </label>

            <input
              type="text"
              placeholder="Enter last name"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Date Of Birth
            </label>

            <input
              type="date"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Phone Number
            </label>

            <input
              type="tel"
              placeholder="Enter phone number"
              className="h-12 px-4"
            />
          </div>

          {/* Contact Information */}

          <div className="md:col-span-2">
            <label className="block mb-2 text-sm font-semibold">
              Email Address
            </label>

            <input
              type="email"
              placeholder="Enter email address"
              className="h-12 px-4"
            />
          </div>

          <div className="md:col-span-2">
            <label className="block mb-2 text-sm font-semibold">
              Residential Address
            </label>

            <textarea
              rows={4}
              placeholder="Enter full address"
              className="px-4 py-3 resize-none"
            />
          </div>

          {/* Identity Information */}

          <div>
            <label className="block mb-2 text-sm font-semibold">
              PAN Number
            </label>

            <input
              type="text"
              placeholder="ABCDE1234F"
              className="h-12 px-4 uppercase"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Aadhaar Number
            </label>

            <input
              type="text"
              placeholder="XXXX XXXX XXXX"
              className="h-12 px-4"
            />
          </div>

          {/* Security */}

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Password
            </label>

            <input
              type="password"
              placeholder="Create password"
              className="h-12 px-4"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm font-semibold">
              Confirm Password
            </label>

            <input
              type="password"
              placeholder="Confirm password"
              className="h-12 px-4"
            />
          </div>
        </div>

        {/* Declaration */}

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
              I certify that the information provided
              is accurate and may be used for identity
              verification and account creation.
            </span>
          </label>
        </div>

        {/* Submit */}

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
        >
          Submit Application
        </button>
      </form>

      {/* Footer */}

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