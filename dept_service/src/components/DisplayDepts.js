import React from "react";
import "../styles/table.css";

import DeleteDept from "./DeleteDept";
import UpdateDept from "./UpdateDept";
function DisplayDepts({ depts, fetchDepts}) {
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Delete</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          {depts.map((dept) => (
            <tr key={dept.id}>
              <td>{dept.id}</td>
              <td>{dept.name}</td>
              <td>{dept.description}</td>
              <td>
                <DeleteDept deptid={dept.id} fetchDepts={fetchDepts} />
              </td>
              <td>
                <UpdateDept dept={dept} fetchDepts={fetchDepts} />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default DisplayDepts;
