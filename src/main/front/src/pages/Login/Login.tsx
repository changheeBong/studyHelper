import logoImg from "./images/login.png";
import "./Login.css";
import { Link } from "react-router-dom";
function Login() {
  return (
    <div className="LoginWrap">
      <img src={logoImg} alt="logo" className="loginLogo" />

      <form method="post" className="loginForm">
        <input className="email" type="text" placeholder="이메일" />
        <input className="password" type="password" placeholder="비밀번호" />
        <input type="checkbox" id="keepLogin" />
        <label htmlFor="keepLogin">로그인 유지</label>
        <input className="login" type="submit" value="로그인" />
      </form>

      <Link to="/user/signup" className="loginSignup">
        회원가입
      </Link>
      <div className="Oauth">카카오칸</div>
      <div className="Oauth">네이버칸</div>
    </div>
  );
}
export default Login;
