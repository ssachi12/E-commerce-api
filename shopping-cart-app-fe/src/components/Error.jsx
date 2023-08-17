import React from 'react'

function Error() {
  return (
    <div style={{display:"flex",alignItems:"center",justifyContent:"center",flexDirection:"column"}} >
      <h4 style={{textAlign:"center",color:"red",marginTop:"100px"}}>Unable to fetch the page</h4>
      <div  style={{height:"200px",width:"250px",display:"flex",alignItems:"center",justifyContent:"center",marginTop:"150px"}}>
        <img src="https://media3.giphy.com/media/8L0Pky6C83SzkzU55a/200w.gif?cid=790b7611qu65skwjr4prb2tcpmsndtosp3dgp4na0226khgh&ep=v1_gifs_search&rid=200w.gif&ct=g" alt=""/>
      </div>
    </div>
  )
}

export default Error
