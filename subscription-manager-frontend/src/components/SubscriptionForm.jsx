import { useState } from "react";
import API from "../service/api";

function SubscriptionForm() {
  const [name, setName] = useState("");
  const [cost, setCost] = useState("");
  const [type, setType] = useState("monthly");
  const [startDate, setStartDate] = useState("");
  const [status, setStatus] = useState("active");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await API.post("/subscriptions", {
        name,
        cost: parseFloat(cost),
        type,
        startDate,
        status,
      });

      alert("Subscription Added");

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
    <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "15px",
        maxWidth: "400px",
        margin: "30px auto",
      }}
    >
      <input
        type="text"
        placeholder="Netflix"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />

      <input
        type="number"
        placeholder="499"
        value={cost}
        onChange={(e) => setCost(e.target.value)}
        required
      />

      <select
        value={type}
        onChange={(e) => setType(e.target.value)}
        required
      >
        <option value="monthly">Monthly</option>
        <option value="yearly">Yearly</option>
      </select>

      <input
        type="date"
        value={startDate}
        onChange={(e) => setStartDate(e.target.value)}
        required
      />

      <input
        type="text"
        placeholder="Status (e.g., active)"
        value={status}
        onChange={(e) => setStatus(e.target.value)}
      />

      <button type="submit">Add Subscription</button>
    </form>
  );
}

export default SubscriptionForm;