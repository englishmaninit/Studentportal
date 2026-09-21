import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Header from "../components/header";

function Resources(){


const navigate = useNavigate();



return(

        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex  flex-row bg-neutral-100 ">

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
                    
        
                    2xl:h-9/10
                    items-center
                    xl:h-7/10

                    
                
                ">
                    <button className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-1
                        flex
                        flex-col
                        items-start
                        pl-5
                        hover:border-sky-200

                        
                        

                    ">
                        <p className="
                        
                            2xl:text-3xl
                            2xl:pt-5
                            font-semibold
                            xl:text-2xl
                            xl:pt-2
                        
                        ">Past paper</p>
                        <p className="
                        
                            2xl:pt-5
                            xl:pt-2
                            
                        ">Browse exam-style practice tests</p>
                    </button>
                    <button className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-1
                        flex
                        flex-col
                        items-start
                        pl-5
                        hover:border-sky-200
                    
                    ">
                        <p className="
                        
                            2xl:text-3xl
                            2xl:pt-5
                            font-semibold
                            xl:text-2xl
                            xl:pt-2
                        
                        ">Past paper</p>
                        <p className="
                        
                            2xl:pt-5
                            xl:pt-2
                            
                        ">Browse exam-style practice tests</p>
                    </button>
                    <button className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-1
                        flex
                        flex-col
                        items-start
                        pl-5
                        hover:border-sky-200
                    
                    ">
                        <p className="
                        
                            2xl:text-3xl
                            2xl:pt-5
                            font-semibold
                            xl:text-2xl
                            xl:pt-2
                        
                        ">Past paper</p>
                        <p className="
                        
                            2xl:pt-5
                            xl:pt-2
                            
                        ">Browse exam-style practice tests</p>
                    </button>
                    <Link to={"/notes"} className="
                    
                        bg-sky-50
                        w-9/10
                        rounded-xl
                        shadow-md
                        border-2
                        border-sky-100
                        flex-1
                        flex
                        flex-col
                        items-start
                        pl-5
                        hover:border-sky-200
                    
                    ">
                        <p className="
                        
                            2xl:text-3xl
                            2xl:pt-5
                            font-semibold
                            xl:text-2xl
                            xl:pt-2
                        
                        ">Your Notes</p>
                        <p className="
                        
                            2xl:pt-5
                            xl:pt-2
                            
                        ">Browse exam-style practice tests</p>
                    </Link>
                </div>
            </div>
        </div>

    );
}
export default Resources;
