import React, { useState,useEffect } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import AddDeptForm from "./AddDeptForm";
import DisplayDepts from "./DisplayDepts.js";
import "../styles/navBar.css";
export default function AppBody(handleSearchFilter) {
  
  const getUrl = "http://localhost:9003/departments/get";
  const [open, setOpen] = useState(false);
  const [searchDept, setSearchDept] = useState("");
  const [depts, setDepts] = useState([]);


  const fetchDepts = () => {
    fetch(getUrl)
      .then((response) => response.json())
      .then((data) => setDepts(data))
      .catch((e) => {
        console.error(e);
      });
  };
  useEffect(() => {
    fetchDepts();
  }, []);

  const filteredDepts = depts.filter((dept)=>
    dept.name.toLowerCase().includes(searchDept.toLowerCase())
  );

  const handleSearch = (e) => {
    setSearchDept(e.target.value);
  };
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
    <div className="nav">
      <span className="servicName">Department Service</span>
      <input
        type="search"
        placeholder="Search By Department Name"
        className="searchbar"
        value={searchDept}
        onChange={handleSearch}
      />
      <div className="create">
        <Button variant="contained" onClick={handleClickOpen}>
          Create
        </Button>
      </div>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Create Department</DialogTitle>
        <AddDeptForm handleClose={handleClose} fetchDepts={fetchDepts}/>
      </Dialog>
    </div>
    <DisplayDepts depts={filteredDepts} fetchDepts={fetchDepts}/>
    </div>
  );
}
