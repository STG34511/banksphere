import AuthBranding from "../components/AuthBranding";
import RegistrationForm from "../components/RegistrationForm";

const RegisterPage = () => {
  return (
    <div
      className="
        h-screen
        overflow-hidden
        grid
        lg:grid-cols-[52%_48%]
      "
    >
      <AuthBranding />

      <div
        className="
          h-screen
          overflow-y-auto
          bg-background
        "
      >
        <div
          className="
            flex
            justify-center
            px-8
            py-12
          "
        >
          <RegistrationForm />
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;