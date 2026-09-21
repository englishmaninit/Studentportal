import { Link } from "react-router-dom"


function Header({active}){

    return(

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
                        <Link to={"/dashboard"} className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "Dashboard"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Dashboard</Link>
                        <Link className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "Homework"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Homework</Link>
                        <Link className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "AI Practice"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>AI Practice</Link>
                        <Link to={"/resources"} className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "Resources"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Resources</Link>
                        <Link  className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "Timetable"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Timetable</Link>
                        <Link className={`
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]

                            hover:bg-sky-100

                            ${active === "Revision"? "bg-sky-100 border-l border-l-4 border-amber-600": ""}
                        
                        `}>Revision</Link>
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

    )


}

export default Header