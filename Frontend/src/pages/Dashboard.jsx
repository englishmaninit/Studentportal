import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {

    const [active, setActive] = useState("dashboard")
    const navigate = useNavigate();

    const handleNavigation = (tabKey, path) => {
        setActive(tabKey);
        navigate(path);
    };


    return (

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
                        
                        ">Resources</button>
                        <button className="
                        
                            flex
                            justify-start
                            pl-5
                            font-semibold
                            h-15
                            items-center
                            text-[1.5vh]
                        
                        ">Timetable</button>
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
            <div className="
        
                outline
                w-[130vh]
                h-[90vh]
                ml-50
                mt-10          
                flex
                flex-col          
            
            ">
                <div className="
                
                            flex
                            flex-row
                            justify-between
                            
                ">
                    <div>
                            <p className="
                            
                                text-blue-400
                                font-semibold   
                                text-[1.5vh]
                            
                            ">Student dashboard</p>
                            <p className="
                            
                                text-2xl
                                font-bold
                                tracking-wider
                                pt-5
                            
                            
                            ">Welcome back, Demo</p>
                            <p className="

                                pt-5
                                text-neutral-500

                            
                            ">Your school day, homework, revision, and resources in one place.</p>
                    </div>
                    <div className="
                    
                            bg-white
                            h-20
                            w-[20vh]
                            rounded-2xl
                            flex
                            p-2
                            flex
                            flex-col
                    
                    ">
                            <p className="
                            
                                font-semibold

                            ">Ready for Period 3</p>
                            <p className="
                            
                                text-neutral-400
                            
                            ">Maths starts at 11:15</p>
                    </div>
                </div>
                <div className="
                
                            outline
                            h-[70vh]
                            grid-rows-3
                            grid-flow-3
                
                ">
                    <div>
                        <p>Demo Student</p>
                    </div>
                    <div>
                        <p>
                            Homework
                        </p>
                        <p>
                            3
                        </p>
                        <p>
                            tasks currently planned
                        </p>
                    </div>
                    <div>
                        <p>
                            Revision
                        </p>
                        <p>
                            4/5
                        </p>
                        <p>
                            sessions completed this week
                        </p>
                    </div>
                    <div>
                        <p>Today's timetable</p>
                        <div>
                            <div>
                                <p>09:00</p>
                                <p>English</p>
                            </div>
                            <div>
                                <p>09:00</p>
                                <p>English</p>
                            </div>
                            <div>
                                <p>09:00</p>
                                <p>English</p>
                            </div>
                        </div>
                    </div>
                    <div>
                        <p>alerts</p>
                    </div>
                    <div>
                        <p>notes</p>
                    </div>
                    <div>
                        <p>progress</p>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default Dashboard;