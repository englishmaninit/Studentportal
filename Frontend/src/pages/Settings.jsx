import { useState } from "react";
import { useNavigate } from "react-router-dom";;

function Settings(){


const navigate = useNavigate();
const [active, setActive] = useState("settings");


return(

        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex  flex-row bg-neutral-100 ">

            <div className="
                    
                    w-1/10
                    bg-sky-50
                    h-[100vh]
                    rounded-2xl
                    flex
                    flex-col

                    p-2
                    shadow-lg
                    transition
                    duration-200

                    pb-5    
                
                ">
                    <p className="
                    
                        font-bold
                        text-2xl
                        pt-5

                    ">The Ultimate Student Portal</p>
                    <div className="

                        flex
                        flex-col
                        mt-10   
                        gap-5
                        flex-3
                    
                    ">
                        <button className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            ${active === "dashboard"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Dashboard</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">Homework</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">AI Practice</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">Reasources</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">TimeTable</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">Revision</button>
                    </div>
                    <div className="
                    
                        flex-3
                    
                    ">
                        
                    </div>
                    <div className="
                    
                        shadow-md
                        rounded-xl
                        h-37
                        bg-white
                        p-3
                        flex-1
                    
                    ">
                        <p className="
                        
                            
                            tracking-wider
                            text-[1.5vh]
                        
                        ">Profile</p>
                        <p className="
                        
                            font-semibold
                            text-[1.5vh]
                        
                        ">Student Name</p>
                        <p>Year 11 |GCSE pathway </p>
                        <button className="
                        
                            border-t
                            w-10/10
                            border-neutral-400
                            mt-3
                            pt-2
                        
                        ">Settings</button>
                    </div>
                </div>
                </div>
                
                )
}
export default Settings;