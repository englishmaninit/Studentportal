import Header from "../components/header"
import { useRef, useState } from "react";

function UploadNotes() {

    const [image, setImage] = useState();
    const [errorMessage, setErrorMessage] = useState("");
    const [saving, setSaving] = useState(false)

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
                            
                            ">Notes</p>
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
                            Upload
                        </button>
                        <button className=" 
                            
                                bg-sky-200 
                                rounded-xl 
                                h-15 
                                w-[25vh] 
                                font-semibold 
                                hover:shadow-md 
                                active:shadow-none 
                            
                            ">
                            Back
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )

}

export default UploadNotes