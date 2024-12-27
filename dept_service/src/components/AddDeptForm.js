import React, { useState } from "react";
import Button from "@mui/material/Button";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";

import TextField from "@mui/material/TextField";

export default function AddDeptForm({ handleClose, fetchDepts, editDept }) {
  console.log(editDept);
  const [deptName, setDeptName] = useState(
    editDept != null ? editDept.name : ""
  );
  const [discription, setDiscription] = useState(
    editDept != null ? editDept.description : ""
  );
  const createURL = "http://localhost:9003/departments/create";
  const updateURL = "http://localhost:9003/departments/update";
  const addDept = async (deptData) => {
    console.log("in addDept");
    try {
      const response = await fetch(createURL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json", // This header is important
        },
        body: JSON.stringify(deptData),
      });
      const responseBody = await response.text();
      if (response.ok) {
        await alert(`${responseBody}`);
        fetchDepts();
      } else {
        await alert(
          `failed to create department status code ${response.status}`
        );
      }
    } catch (e) {
      console.error(e);
    }
  };
  const updateDept = async (editedDeptData) => {
    console.log("in UpdateDept");
    try {
      const response = await fetch(updateURL, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json", // This header is important
        },
        body: JSON.stringify(editedDeptData),
      });
      const responseBody = await response.text();
      if (response.ok) {
        await alert(`${responseBody}`);
        fetchDepts();
      } else {
        await alert(
          `failed to update department status code ${response.status}`
        );
      }
    } catch (e) {
      console.error(e);
    }
  };
  const handleSubmit = (e) => {
    if (editDept != null) {
      const editedDeptData = {
        id: editDept.id,
        name: deptName,
        description: discription,
      };
      updateDept(editedDeptData);
    } else {
      const deptData = {
        name: deptName,
        description: discription,
      };
      addDept(deptData);
    }
   
    handleClose();
    e.preventDefault(); //this will not allow the component to rerender after submit.
  };

  const handleDeptNameChange = (e) => {
    setDeptName(e.target.value);
  };
  const handleDiscrpChange = (e) => {
    setDiscription(e.target.value);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <DialogContent>
          <DialogContentText>
            Please Enter the following details
          </DialogContentText>
          <TextField
            autoFocus
            required
            margin="dense"
            id="name"
            name="departmentName"
            label="Department Name"
            type="text"
            fullWidth
            variant="filled"
            value={deptName}
            onChange={handleDeptNameChange}
          />
          <TextField
            required
            margin="dense"
            id="description"
            name="departmentDescription"
            label="Description"
            type="text"
            fullWidth
            variant="filled"
            value={discription}
            onChange={handleDiscrpChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button type="submit">Add</Button>
        </DialogActions>
      </form>
    </div>
  );
}
