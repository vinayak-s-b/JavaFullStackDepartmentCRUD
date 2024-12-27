
import React, { useState } from 'react'
import AddDeptForm from './AddDeptForm'
import Dialog from "@mui/material/Dialog";
import EditIcon from '@mui/icons-material/Edit';
import DialogTitle from "@mui/material/DialogTitle";
import IconButton from "@mui/material/IconButton";

function UpdateDept({dept,fetchDepts}) {
    const[open,Setopen]=useState(false)
    const handleClickOpen=()=>{
      Setopen(true);
    }
    const handleClose=()=>{
      Setopen(false);
    }
  return (
    <>
    <div className="create">
    <IconButton aria-label="edit" onClick={handleClickOpen}>
        <EditIcon />
      </IconButton>
      </div>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Update Department</DialogTitle>
        <AddDeptForm handleClose={handleClose} fetchDepts={fetchDepts} editDept={dept}/>
      </Dialog>
    </>
  )
}

export default UpdateDept