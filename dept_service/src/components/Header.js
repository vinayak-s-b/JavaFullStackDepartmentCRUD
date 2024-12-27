import React from "react";
import "../styles/headerStyle.css";
export default function Header() {
  return (
    <div className="headContainer">
      <h1> Department CRUD Manager </h1>
      <p>
        "A full-stack web application to manage department data (ID, name,
        description) with CRUD operations, built using Java, Spring Boot, and
        React."
      </p>
    </div>
  );
}
