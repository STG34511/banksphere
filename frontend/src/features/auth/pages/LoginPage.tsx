import AuthBranding from "../components/AuthBranding";
import LoginForm from "../components/LoginForm";

const LoginPage = () => {
  return (
    <div className="min-h-screen grid md:grid-cols-[58%_42%]">
      <AuthBranding />

      <div
        className="
          flex
          items-center
          justify-center
          px-8
          lg:px-12
          bg-background
        "
      >
        <LoginForm />
      </div>
    </div>
  );
};

export default LoginPage;
