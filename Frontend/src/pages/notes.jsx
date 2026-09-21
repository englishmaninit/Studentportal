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
                        items-center
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
                            2xl:pt-10
                            justify-center
                            overflow-y-scroll
                            flex-1
                            xl:p-3
                            
                        
                        ">
                            {folders? folders.map((folder) =>(

                                <button className="
                                
                                    bg-blue-300
                                    2xl:w-45
                                    2xl:h-45
                                    rounded-xl
                                    hover:shadow-md

                                    xl:w-30
                                    xl:h-30
                                
                                ">
                                    <p className="
                                    
                                        font-bold
                                        tracking-wider
                                        text-white
                                        xl:font-semibold
                                        xl:text-[2vh]
                                    
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
                        xl:mb-5
                    
                    ">
                        <Link to={"/uploadnotes"} className="
                        
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
                            xl:h-10
                        ">
                            new note
                        </Link>
                        <button className="
                        
                            bg-sky-200
                            rounded-xl
                            h-15
                            w-[25vh]
                            font-semibold
                            hover:shadow-md
                            active:shadow-none

                            xl:h-10
                        
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
                            xl:h-10
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