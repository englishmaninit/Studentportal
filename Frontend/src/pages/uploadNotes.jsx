import { Link } from "react-router-dom";
import Header from "../components/header"
import { useRef, useState } from "react";

function UploadNotes() {

    const [image, setImage] = useState();
    const [errorMessage, setErrorMessage] = useState("");
    const [saving, setSaving] = useState(false)
    const title = useRef("")
    const desc = useRef("")

    function uploadImage(image) {

        setErrorMessage("")

        if (!image || !image.type.startsWith("image/")) {

            setImage(null)
            setErrorMessage("not valid file type")

        }
        else {

            setImage(image)

        }

    }

    function saveNote() {

        if (!image) {
            setErrorMessage("Please select an image");
            return;
        }

        setSaving(true);

        const reader = new FileReader();

        reader.onloadend = () => {

            const base64Image = reader.result.split(",")[1];

            fetch("http://localhost:8080/upload-image", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({

                    userID: 4,
                    subjectID: 1,
                    noteTitle: title.current.value,
                    description: desc.current.value,
                    image: base64Image

                })

            })
            .then(response => response.text())
            .then(data => {

                console.log(data);
                setSaving(false);

            })
            .catch(error => {

                console.error(error);
                setErrorMessage("Failed to upload image");
                setSaving(false);

            });

        };

        reader.readAsDataURL(image);
    }

    return (
        <div className="bg-gradient-to-r from-sky-100 to bg-sky-50  w-screen min-h-screen   flex  flex-row bg-neutral-100">
            <Header active={"Resources"} />
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
                                self-start

                                xl:pt-0
                            
                            ">Notes</p>
                        <div className="
                        
                            w-9/10
                            h-9/10
                            flex
                            flex-col
                            items-center
                        
                        ">
                            <label className=" 
                                    flex flex-col 
                                    items-center 
                                    justify-center 
                                    w-7/10 
                                    h-8/10 
                                    mt-5 
                                    border-2 
                                    rounded-md 
                                    border-neutral-300 
                                    hover:border-neutral-500
                                    cursor-pointer 
                                    text-neutral-600 
                                    hover:text-black 
                                    active:text-neutral-600 
                
                                "
                            >
                                <div className={` 
                                
                                    flex 
                                    flex-col 
                                    items-center 
                                    ${image === null ? "" : "hidden"} 
                                    
                                
                                `}>
                                    <div>
                                        <p>upload image</p>
                                    </div>

                                    <p className="mt-2 text-gray-600">
                                        Click to upload an image
                                    </p>
                                </div>
                                <div className={` 
                                
                                    w-[80vh] 
                                    h-[40vh] 
                                    p-30 
                                    flex 
                                    justify-center 
                                    items-center 
                                    flex-col 
                                    ${image === null ? "hidden" : ""} 
                                
                                `}>
                                    <img src={image ? URL.createObjectURL(image) : ""} className=" 
                                    
                                        h-[30vh] 
                                    
                                    "/>
                                    <p>Click to change image</p>
                                </div>

                                <input disabled={saving} onChange={(e) => uploadImage(e.target.files[0])} type="file" accept="image/*" className="hidden" />
                            </label>

                            <div className="
                            
                                flex
                                flex-row
                                gap-4
                            
                            ">
                                <div className="
                                
                                    font-semibold
                                
                                ">
                                    
                            
                                    <input ref={title} placeholder="Title" type="text" className="
                                        
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
                                <div className="
                                
                                    font-semibold
                                
                                ">
                                    
                            
                                    <input ref={desc} placeholder="description" type="text" className="
                                        
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
                            </div>
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
                        <button onClick={saveNote} className=" 
                            
                                bg-sky-200 
                                rounded-xl 
                                h-15 
                                w-[25vh] 
                                font-semibold 
                                hover:shadow-md 
                                active:shadow-none 
                                xl:h-10
                            
                            ">
                            Upload
                        </button>
                        <Link to={"/notes"} className=" 
                            
                                bg-sky-200 
                                rounded-xl 
                                h-15 
                                w-[25vh] 
                                font-semibold 
                                hover:shadow-md 
                                active:shadow-none 
                                xl:h-10
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

export default UploadNotes