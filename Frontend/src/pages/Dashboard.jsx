import { use, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/header";

function Dashboard() {

    const today = new Date()
    const [active, setActive] = useState("dashboard")
    const navigate = useNavigate();
    const [revisionText, setRevisionText] = useState("")
    const [revisionArray, setRevisionArray] = useState([{subject:"", status:"completed"},{subject:"", status:"completed"},{subject:"", status:"none"}])
    const [timeTableArray, setTimeTableArray] = useState([{time:"09:00", subject:"english", active:"passed"}, {time:"11:15", subject:"Maths", active:"passed"}, {time:"13:20", subject:"Science", active:"passed"}, {time:"09:00", subject:"english",active:"now"}, {time:"11:15", subject:"Maths",active:"upcomming"}, {time:"13:20", subject:"Science", active:"upcomming"}])
    const [alerts, setAlerts] = useState([{subject:"english", summary:"assesment due soon"},{subject:"maths", summary:"exam soon"}, {subject:"IT", summary:"Presentation soon"}])

    const handleNavigation = (tabKey, path) => {
        setActive(tabKey);
        navigate(path);
    };

    useEffect(()=>{

        let finish = 0;
        for(let i = 0; i < revisionArray.length; i++){

            if(revisionArray[i].status === "completed"){

                finish = finish + 1;

            }

        }   

        setRevisionText(`${finish}/${revisionArray.length}`)

    }, [])

    return (

        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex  flex-row bg-neutral-100 ">

            <Header active={"Dashboard"}/>
            <div className="
        
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
                        items-center
                        
                            
                ">
                    <div className="
                    
                            flex
                            flex-col
                            gap-3
                    
                    ">
                            <p className="
                            
                                text-blue-400
                                font-semibold   
                                text-[1.5vh]
                            
                            ">Student dashboard</p>
                            <p className="
                            
                                text-2xl
                                font-bold
                                tracking-wider
                       
                            
                            
                            ">Welcome back, Demo</p>
                            <p className="

                        
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
                    
                    mt-5
                    flex-1
                    min-h-0
                    grid
                    grid-cols-4
                    grid-rows-[repeat(5,minmax(0,1fr))]
                    gap-3
                
                ">
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            col-span-2
                    
                    ">
                        <p>Demo Student</p>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            col-span-1
                            pl-5
                            pt-2
                            flex
                            flex-col
                            gap-3
                    
                    ">
                        <p className="
                        
                            font-semibold
                            text-red-600
                            
                        
                        ">
                            Homework
                        </p>
                        <p className="
                        
                            text-4xl
                            font-bold
                        
                        ">
                            3
                        </p>
                        <p className="
                        
                            
                        
                        ">
                            tasks currently planned
                        </p>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            col-span-1
                            pl-5
                            pt-2
                            flex
                            flex-col
                            gap-3
                    ">
                        <p className="
                        
                            font-semibold
                            text-red-600
                            
                        
                        ">
                            Revision
                        </p>
                        <p className="
                        
                            text-4xl
                            font-bold
                        
                        ">
                            {revisionText}
                        </p>
                        <p>
                            sessions completed this week
                        </p>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            row-span-2
                            col-span-3
                            p-5
                            flex
                            flex-col
                    
                    ">
                        <div className="
                        
                            flex
                            flex-row
                            justify-between
                            pr-5
                            
                        
                        ">
                        <p className="
                        
                            font-semibold
                            text-[1.8vh]

                        
                        ">Today's timetable</p>
                        <p className="
                        
                            border-2
                            
                            rounded-xl
                            w-[10vh]
                            border-neutral-200
                            flex
                            items-center
                            justify-center
                        
                        ">{today.toLocaleDateString("en-GB", { weekday: "long" })}</p>
                        </div>
                        <div className="

                            mt-5
                            flex
                            flex-col
                            gap-3
                            overflow-y-scroll
                            
                        
                        ">
                            {timeTableArray? timeTableArray.map((lesson) => (

                                <div className={`
                                
                                    border-2
                                    border-neutral-200
                                    rounded-xl
                                    flex
                                    h-15
                                    items-center
                                    pl-5
                                    gap-4
                                    shrink-0
                                    ${lesson.active === "now"? "bg-red-100 border-red-200":""}
                                    ${lesson.active === "passed"? "bg-neutral-200 border-neutral-400":""}
                                
                                `}>
                                    <p className={`
                                    
                                        font-semibold
                                        ${lesson.active === "passed"? "text-neutral-500":"text-blue-400"}
                                        
                                    
                                    `}>{lesson.time}</p>
                                    <p className="
                                    
                                        font-semibold
                                    
                                    ">{lesson.subject}</p>
                                </div>


                            )): ""}

                        </div>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            row-span-2
                            col-span-1
                            flex
                            flex-col
                    
                    ">
                        <p className="
                        
                            text-[2vh]
                            
                            font-semibold
                            pl-5
                            pt-2
                        
                        ">alerts</p>
                        <div className="
                        
                            pl-10
                            pt-5
                            mr-5
                            flex-1
                            overflow-y-scroll
                            mb-10
                            flex
                            flex-col
                            gap-3
                        
                        ">
                            {alerts? alerts.map((alert) => (

                                <div className="
                                
                                    border-2
                                    rounded-xl
                                    border-neutral-200
                                    pl-2

                                ">
                                    <p className="
                                    
                                        font-semibold
                                    
                                    ">{alert.subject}</p>
                                    <p className="
                                    
                                        text-neutral-600
                                        pl-2
                                    
                                    ">{alert.summary}</p>
                                </div>

                            )): ""}
                        </div>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            row-span-1
                            col-span-3
                    
                    ">
                        <p>notes</p>
                    </div>
                    <div className="
                    
                            bg-white
                            shadow-md
                            rounded-xl
                            row-span-1
                            col-span-1
                    
                    ">
                        <p>progress</p>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default Dashboard;