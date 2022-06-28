import "./App.css"
import {useState} from "react"
import axios from "axios"
function App() {
  const [rows,setRows] = useState([])
  const [err,setErr] = useState(false)

  console.log(rows)

  const handleFile = (e)=>{
    const file = e.target.files[0]
    if(!file.name.substring(file.name.length-5).includes(".csv")){
        return setErr(true)
    }
    const sendFile = async (file)=>{
      try {
        const formdata = new FormData()
        formdata.append("file",file)
        const res = await axios({
          method:"post",
          url:"http://localhost:8080/api/filehandler",
          data:formdata,
          headers:{"Content-Type":"multipart/form-data"}
        })
       setRows(res.data)
      } catch (error) {
        console.log(err)
      }
    }
    sendFile(file);
    setErr(false);
  }
  return (
    <div className="main-page">
      <div className="container"> 
        <div className="header">
          <h1>Employee Collaboration Time Calculator </h1>
          <hr />
        </div>
        {err && <label className="alert alert-danger" style={{"width":"100%"}}>Please enter a csv file!</label>}
        <label htmlFor="file" className="label-button">Upload File
              <input type="file" name="file" id="file" className="file-input" onChange={(e)=>handleFile(e)}/>
        </label>
        <div className="table-div">
          <table className="table">
            <thead>
              <tr>
                <th scope="col">EmployeeId #1</th>
                <th scope="col">EmployeeId #2</th>
                <th scope="col">ProjectId</th>
                <th scope="col">Days Worked</th>
              </tr>
            </thead>
            <tbody>
            {rows.map((row)=>{
              console.log(row)
              return(
                <tr>
                  <td>{row.employee1Id}</td>
                  <td>{row.employee2Id}</td>
                  <td>{row.projectId}</td>
                  <td>{row.daysWorked}</td>
                </tr>
              )
            })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default App;
