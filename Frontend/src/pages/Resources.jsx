import { useState } from "react";
import { useNavigate } from "react-router-dom";;

function Resources(){


const navigate = useNavigate();
const [active, setActive] = useState("resources");


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
                        <div className="
                        w-full max-w-4xl
                         bg-sky-50/50 p-8 
                         rounded-3xl border
                          border-sky-100 
                          font-sans">
                            
                        </div>
                    <div className="flex-1 p-8">
                <div className="
                    w-full max-w-4xl
                    bg-sky-50/50 p-8 
                    rounded-3xl border
                    border-sky-100 
                    font-sans
                ">
                    <div className="
                        flex items-center
                        justify-between mb-6
                    ">
                        <h2 className="
                            text-2xl font-bold
                            text-slate-900 
                            tracking-tight
                        ">
                            Study resources
                        </h2>
                        <span className="text-sm text-slate-700 bg-white/80 px-4 py-2 rounded-full border border-sky-100/80 shadow-sm font-medium">
                            Quick access
                        </span>
                    </div>

                    <div className=" flex flex-col gap-4">
                        <div className="bg-[#e7f0fd]/70 hover:bg-[#deebfd] transition-colors p-6 rounded-2xl border border-sky-200/50 cursor-pointer">
                            <h3 className="text-lg font-bold text-slate-900 mb-1">
                                Past papers
                            </h3>
                            <p className="text-slate-500 text-base">
                                Browse exam-style practice sets
                            </p>
                        </div>

                        <div className="bg-[#e7f0fd]/70 hover:bg-[#deebfd] transition-colors p-6 rounded-2xl border border-sky-200/50 cursor-pointer">
                            <h3 className="text-lg font-bold text-slate-900 mb-1">
                                Mark schemes
                            </h3>
                            <p className="text-slate-500 text-base">
                                Check methods and common mistakes
                            </p>
                        </div>

                        <div className="bg-[#e7f0fd]/70 hover:bg-[#deebfd] transition-colors p-6 rounded-2xl border border-sky-200/50 cursor-pointer">
                            <h3 className="text-lg font-bold text-slate-900 mb-1">
                                Revision videos
                            </h3>
                            <p className="text-slate-500 text-base">
                                Watch short topic explainers
                            </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
              </div>
              </div>

    );
}
export default Resources;
