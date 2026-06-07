import AuthBranding from "../../auth/components/AuthBranding";
import OfficerLoginForm from "../components/OfficerLoginForm";

const OfficerLoginPage = () => {
  return (
    <div
      className="
        h-screen
        overflow-hidden
        grid
        lg:grid-cols-[58%_42%]
      "
    >
      <AuthBranding />

      <div
        className="
          flex
          items-center
          justify-center
          bg-background
          px-8
        "
      >
        <OfficerLoginForm />
      </div>
    </div>
  );
};

export default OfficerLoginPage;