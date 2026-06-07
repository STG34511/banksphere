const AuthBranding = () => {
  return (
    <div
      className="hidden
    md:flex
    h-screen
    flex-col
    justify-between
    p-12
    bg-primary
    overflow-hidden"
    >

      <div className="">

        <div className="flex items-center gap-6 ">
          <div
            className="
              h-16
              w-16
              rounded-xl
              bg-accent/15
              border
              border-accent/20
              flex
              items-center
              justify-center
              text-accent-light
              font-bold
              text-2xl
              backdrop-blur-sm
            "
          >
            BS
          </div>

          <div>
            <h2 className="text-2xl font-bold text-text-inverse">
              BankSphere
            </h2>

            <p className="text-sm text-white/70">
              Enterprise Digital Banking
            </p>
          </div>
        </div>

        {/* Hero */}

        <div className="mt-16 max-w-140">
          <h1
            className="
              text-4xl
              xl:text-5xl
              font-bold
              leading-tight
              tracking-tight
              text-text-inverse
            "
          >
            Secure Banking
            <br />
            Built Around
            <br />
            Trust & Technology
          </h1>

          <p
            className="
              mt-6
              text-lg
              leading-8
              text-white/80
            "
          >
            Manage accounts, payments, cards and
            transactions through a secure platform
            designed for modern digital banking.
          </p>

          {/* Trust Indicators */}

          <div
            className="
              mt-8
              flex
              flex-wrap
              gap-x-6
              gap-y-3
              text-sm
              text-white/75
            "
          >
            <span>✓ Secure Authentication</span>
            <span>✓ Real-Time Payments</span>
            <span>✓ Enterprise Security</span>
          </div>
        </div>
      </div>

      {/* Stats */}

      <div
        className="
          relative
          z-10
          mt-12
          grid
          grid-cols-3
          gap-4
        "
      >
        <div className="glass-card p-5">
          <p className="text-3xl font-bold text-white">
            1M+
          </p>

          <p className="mt-2 text-sm text-white/70">
            Active Accounts
          </p>
        </div>

        <div className="glass-card p-5">
          <p className="text-3xl font-bold text-white">
            99.99%
          </p>

          <p className="mt-2 text-sm text-white/70">
            Availability
          </p>
        </div>

        <div className="glass-card p-5">
          <p className="text-3xl font-bold text-white">
            256-bit
          </p>

          <p className="mt-2 text-sm text-white/70">
            Encryption
          </p>
        </div>
      </div>
    </div>
  );
};

export default AuthBranding;