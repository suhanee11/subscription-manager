function Navbar() {

  const handleLogout = () => {

    localStorage.removeItem("token");

    window.location.href = "/";

  };

  return (

    <div
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "20px",
        backgroundColor: "black",
        color: "white",
      }}
    >

      <h2>Subscription Manager</h2>

      <button onClick={handleLogout}>
        Logout
      </button>

    </div>
  );
}

export default Navbar;