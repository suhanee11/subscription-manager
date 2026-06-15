import { useState } from "react";
import API from "./service/api";

function Dashboard() {
  const [name, setName] = useState("");
  const [cost, setCost] = useState("");
  const [type, setType] = useState("monthly");
  const [startDate, setStartDate] = useState("");
  const [status, setStatus] = useState("active");

  const handleAddSubscription = async () => {
    try {
      await API.post("/api/subscriptions", {
        name,
        cost: parseFloat(cost),
        type,
        startDate,
        status,
      });

      alert("Subscription added");

      setName("");
      setCost("");
      setType("monthly");
      setStartDate("");
      setStatus("active");
    } catch (error) {
      console.log(error);
      alert("Failed to add subscription");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Subscription Manager</h1>

      <input
        type="text"
        placeholder="Subscription Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      <br /><br />

      <input
        type="number"
        placeholder="Cost"
        value={cost}
        onChange={(e) => setCost(e.target.value)}
        required
      />
      <br /><br />

      <select
        value={type}
        onChange={(e) => setType(e.target.value)}
        required
      >
        <option value="monthly">Monthly</option>
        <option value="yearly">Yearly</option>
      </select>
      <br /><br />

      <input
        type="date"
        value={startDate}
        onChange={(e) => setStartDate(e.target.value)}
        required
      />
      <br /><br />

      <input
        type="text"
        placeholder="Status (e.g., active)"
        value={status}
        onChange={(e) => setStatus(e.target.value)}
      />
      <br /><br />

      <button onClick={handleAddSubscription}>
        Add Subscription
      </button>
    </div>
  );
}

export default Dashboard;