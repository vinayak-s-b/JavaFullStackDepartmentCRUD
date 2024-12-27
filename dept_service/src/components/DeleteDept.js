import React, { useState } from "react";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";

import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogActions from "@mui/material/DialogActions";

export default function DeleteDept({ deptid, fetchDepts }) {
  const [openDelete, setOpenDelete] = useState(false);
  const deleteUrl = "http://localhost:9003/departments/delete/";
  const deleteDeptsHandler = async (deptid) => {
    try {
      const response = await fetch(deleteUrl + deptid, { method: "DELETE" });
      const responseBody = await response.text();
      if (response.ok) {
        alert(` ${responseBody} successfully`);
        fetchDepts();
      } else {
        alert(`failed to delete ${responseBody} `);
      }
    } catch (e) {
      console.log(e);
    }
  };
  const handleSubmit = () => {
    deleteDeptsHandler(deptid);
    setOpenDelete(false);
  };
  const deleteHandler = () => {
    setOpenDelete(true);
  };
  const handleClose = () => {
    setOpenDelete(false);
  };
  return (
    <div>
      <IconButton aria-label="delete" onClick={deleteHandler}>
        <DeleteIcon />
      </IconButton>
      <Dialog open={openDelete} onClose={handleClose}>
        <DialogTitle>
          Are you sure you want to delete this department ?
        </DialogTitle>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button type="submit" onClick={handleSubmit}>
            delete
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
