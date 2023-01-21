import { Routes, Route } from "react-router-dom";
import Login from "./Login/Login";
function Navigator() {
  return (
    <div>
      <Routes>
        <Route path="/user/login" element={<Login />} />
        <Route path="/user/signup" element={<div>회원가입페이지임</div>} />
      </Routes>
    </div>
  );
}
export default Navigator;
