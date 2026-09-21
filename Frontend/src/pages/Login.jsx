    import { useRef, useState } from "react";
    import { useNavigate } from "react-router-dom";


    function Login() {

        const [activePage, setActivePage] = useState("register")
        const username = useRef("")
        const password = useRef("")
        const username1 = useRef("")
        const firstname = useRef("")
        const lastName = useRef("")
        const dateOfBirth = useRef("")
        const password1 = useRef("")
        const password2 = useRef("")
        const [errorMessage, setErrorMessage] = useState("")
        const navigate = useNavigate()
        

        function test() {

            fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    username: username.current.value,
                    password: password.current.value
                })
            })
            .then(response => response.text())
            .then(data => {
                console.log("Backend response:", data);
                if("Login successful"){

                    navigate("/dashboard")

                }
                else{

                    setErrorMessage(data)

                }
            })
            .catch(error => {
                console.log("Error:", error);
                setErrorMessage(error)
            });
        } 

        function register() {

        fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username1.current.value,
                password: password1.current.value,
                password1: password2.current.value,
                dob: dateOfBirth.current.value,
                firstName: firstname.current.value,
                lastName: lastName.current.value
            })
        })
        .then(response => response.text())
        .then(data => {
            console.log("Backend response:", data);
            setErrorMessage(data)
        })
        .catch(error => {
            console.log("Error:", error);
            setErrorMessage(error)
        });
    }

        function switchRegister(){

            setActivePage("register")

        }
        
        function switchLogin(){

            setActivePage("login")

        }

        return(
        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex items-center flex-col bg-neutral-100 justify-center">

                <div className="
                
                    

                    w-8/10
                    bg-sky-50
                    h-[80vh]
                    rounded-2xl
                    flex
                    flex-row
                    justify-between
                    p-20
                    shadow-lg
                    transition
                    duration-200
                
                ">
                    <div className="
                    
                        flex-2
                        
                    
                    ">
                        <p className="
                        
                            text-blue-400
                            font-bold
                            text-2xl

                        
                        ">Student Success HUB</p>
                        <p className="
                        
                            2xl:text-7xl
                            font-semibold
                            2xl:w-1/2
                            pt-5

                            xl:text-5xl
                            xl:w-2/3
                        
                        ">The Ultimate Student Portal</p>
                        <p className="
                        
                            text-neutral-500
                            2xl:text-2xl
                            pr-10
                            mt-5
                            pt-3

                            xl:text-[2.5vh]
                        
                        ">Organise homework, build revision routines, explore resources, and get AI-supported practice from one modern student dashboard.</p>
                        <div className="
                        
                            flex
                            flex-row   
                            2xl:w-2/3
                            gap-3
                            mt-10
                            2xl:pl-5
                            h-10
                            
                            xl:w-9/10
                            xl:pl-0

                        ">
                            <p className="

                                2xl:text
                                bg-white
                                flex-1
                                rounded-2xl
                                text-center
                                flex
                                items-center
                                justify-center
                                xl:text-[2vh]
                            
                            ">Secure login</p>
                            <p className="
                            
                                bg-white
                                2xl:text
                                flex-1
                                rounded-2xl
                                text-center
                                flex
                                items-center
                                justify-center
                                xl:text-[2vh]
                            
                            ">Homework planner</p>
                            <p className="
                            
                                bg-white
                                flex-1
                                rounded-2xl
                                text-center
                                flex
                                2xl:text
                                items-center
                                justify-center
                                xl:text-[2vh]
                            
                            ">AI revision support</p>
                        </div>
                    </div>
                    <div className="
                    
                        flex-1
                        2xl:p-10
                        shrink-0

                        xl:p-0


                    ">
                        <div>
                            <p className="
                            
                                font-semibold
                                text-2xl
                            
                            ">Student access</p>
                            <p className="
                            
                                text-neutral-500
                                pt-5
                                text-[1.5vh]

                            
                            ">Create an account or sign in to your portal.</p>
                            <div className="
                            
                                bg-gray-200
                                h-[5vh]
                                rounded-3xl
                                flex
                                flex-row
                                items-center
                                pl-3
                                pr-3
                                gap-3
                                shadow-sm
                                mt-3

                            ">
                                <button onClick={switchLogin} className={`
                                
                                    font-bold
                                    h-[4vh]
                                    flex-1
                                    rounded-2xl
                                    ${activePage === "login"? "bg-white shadow-lg text-black": "bg-gray-200 text-gray-600 hover:text-black"}
                                
                                `}>Login</button>
                                <button onClick={switchRegister} className={`
                                
                                   font-bold
                                    h-[4vh]
                                    flex-1
                                    rounded-2xl
                                    ${activePage === "register"? "bg-white shadow-xl text-black": "bg-gray-200 text-gray-600 hover:text-black"}
                                
                                `}>Register</button>
                            </div>
                        </div>
                        <div className={`
                            
                            ${activePage === "login"? "": "hidden"}
                            mt-7
                            
                            2xl:overflow-y-visible
                            

                            xl:overflow-y-scroll
                            xl:h-3/4
                            xl:pb-5
                            xl:pr-5
                            
                            
                        `}>
                            <p className="
                            
                                font-bold
                                text-2xl
                            
                            ">Login</p>
                            <p className="
                            
                                text-neutral-500
                                pt-3
                                
                            
                            ">Sign in with your existing student account.</p>
                            <div className="


                                pt-5
                                pl-10
                            
                            ">
                                <div>
                                    <p className="
                                    
                                        font-semibold
                                    
                                    ">Username</p>
                                    <input ref={username} placeholder="Username" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3

                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none


                                    "/>  
                                </div>
                                <div>
                                    <p className="
                                    
                                        font-semibold
                                        pt-5
                                    
                                    ">Password</p>
                                    <input ref={password} placeholder="Password" type="password" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-5
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none
                                    
                                    "/>  
                                </div>
                                <button onClick={test} className="
                                
                                    w-[35vh]
                                    bg-gradient-to-r from-blue-400 to bg-blue-300
                                    h-10
                                    rounded-2xl
                                    font-bold
                                    text-white
                                    tracking-wider
                                    shadow-lg
                                    mt-5
                                    hover:shadow-xl
                                    active:shadow-inner
                                
                                ">Log In</button>
                            </div>
                        </div>
                        <div className={`
                            
                            ${activePage === "register"? "": "hidden"}
                            mt-7
                            overflow-y-scroll
                            h-3/4
                            pb-5
                            pr-5

                        `}>
                            <p className="
                            
                                font-bold
                                text-2xl
                            
                            ">Register</p>
                            <p className="
                            
                                text-neutral-500
                                pt-3
                            
                            ">Create a new student account to start using the portal.</p>
                            <div className="
                            
                                pt-5
                                pl-10
                            
                            ">
                            <div className="
                            
                                font-semibold
                            
                            ">
                                <p>Username</p>
                                <input ref={username1} placeholder="Username" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <div>
                                <p className="
                                
                                    font-semibold
                                
                                ">First Name</p>
                                <input ref={firstname} placeholder="First Name" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <div>
                                <p className="
                                
                                    font-semibold
                                
                                ">Last Name</p>
                                <input ref={lastName} placeholder="Last Name" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <div>
                                <p className="
                                
                                    font-semibold
                                
                                ">Date of Birth</p>
                                <input ref={dateOfBirth} placeholder="Date of Birth" type="date" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <div>
                                <p className="
                                
                                    font-semibold
                                
                                ">Password</p>
                                <input ref={password1} placeholder="Password" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <div>
                                <p className="
                                
                                    font-semibold
                                
                                ">Check Password</p>
                                <input ref={password2} placeholder="Password" type="text" className="
                                    
                                        bg-white
                                        w-[35vh]
                                        h-10
                                        rounded-2xl
                                        pl-3
                                        font-semibold
                                        gray-300
                                        mt-3
                                        hover:shadow-md
                                        focus:outline-none
                                        focus:border-none

                                    "/>  
                            </div>
                            <button onClick={register} className="
                                
                                    w-[35vh]
                                    bg-gradient-to-r from-blue-400 to bg-blue-300
                                    h-10
                                    rounded-2xl
                                    font-bold
                                    text-white
                                    tracking-wider
                                    shadow-lg
                                    mt-5
                                    hover:shadow-xl
                                    active:shadow-inner
                                
                                ">Create Acount</button>
                            </div>
                        </div>
                        <p className="
                        
                            text-red-600
                            font-semibold
                            mt-5
                        
                        ">{errorMessage}</p>
                    </div>
                    
                </div>

            </div>
        );
    }
    export default Login;