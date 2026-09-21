import { useState } from "react"
import Header from "../components/header"
import { Link } from "react-router-dom"

function Notes(){

    const [folders, setFolders] = useState([{name:"Macbeth"},{name:"Animail Farm"},{name:"Rivers Geo"},{name:"CS databases"},{name:"Maths algebra"},{name:"french town"},{name:"Re christianity"}])

    return(

        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex  flex-row bg-neutral-100">
            <Header active={"Resources"}/>
            <div className="
            
          
                flex-1
                ml-20
                mr-20
                mt-10
                mb-20
                bg-white
                rounded-xl
                shadow-lg
                flex
                flex-col
                

            ">
                <p className="
                
                    text-3xl
                    pt-5
                    pl-10
                    font-semibold
                
                ">Study Resources</p>
                <div className="
                
                    flex
                    flex-col
                    pt-5
                    gap-3
        
                    h-9/10
                    items-center
                
                ">
                    <div className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-3
                        flex
                        flex-col
                        items-start
                        pl-5

                        

                    ">
                        <p className="
                        
                            pt-5
                            text-[2vh]
                            font-semibold
                        
                        ">Folders</p>
                        <div className="
                        
                            flex
                            gap-3
                            flex-wrap
                            pt-10
                            justify-center
                            overflow-y-scroll
                            flex-1
                        
                        ">
                            {folders? folders.map((folder) =>(

                                <button className="
                                
                                    bg-blue-300
                                    w-50
                                    h-50
                                    rounded-xl
                                    hover:shadow-md
                                
                                ">
                                    <p className="
                                    
                                        font-bold
                                        tracking-wider
                                        text-white
                                    
                                    ">{folder.name}</p>
                                </button>

                            )): (

                                <div>
                                    <p>No folders</p>
                                </div>

                            )}
                        </div>

                    </div>

                    <div className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-1
                        flex
                        flex-row
                        justify-center
                        pl-5
                        items-center
                        gap-5
                    
                    ">
                        <button className="
                        
                            bg-sky-200
                            rounded-xl
                            h-15
                            w-[25vh]
                            font-semibold
                            hover:shadow-md
                            active:shadow-none
                        
                        ">
                            Make Folder
                        </button>
                        <Link to={"/resources"} className="
                        
                            bg-sky-200
                            rounded-xl
                            h-15
                            w-[25vh]
                            font-semibold
                            hover:shadow-md
                            active:shadow-none
                            flex
                            items-center
                            justify-center
                        
                        ">
                            Back
                        </Link>
                    </div>
                </div>
            </div>
        </div>

    )

}

export default Notes