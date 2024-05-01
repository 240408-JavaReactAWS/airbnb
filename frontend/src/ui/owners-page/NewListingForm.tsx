import { useState } from "react"
import axios from "axios"

function NewListingForm() {
    
    const [address, setAddress] = useState('')
    const [city, setCity] = useState('')
    const [description, setDescription] = useState('')
    const [name, setName] = useState('')
    const [state, setState] = useState('')
    const [showError, setShowError] = useState(false)
    
    const handleSubmit = async (event: any) => {
        event.preventDefault()
        console.log("Form submitted")
        let parsedOwner
        if (localStorage.hasOwnProperty("user")) {
            const stringifiedOwner = localStorage.getItem("user");
            parsedOwner = stringifiedOwner ? JSON.parse(stringifiedOwner) : null;
        }
        try {
            let res = await axios.post("http://localhost:8080/listings", {
                address,
                city,
                description,
                name,
                state,
                ownerId: parsedOwner["userId"],
                photos: []
            }, {
                withCredentials: true,
                headers: {
                    'Content-Type': 'application/json',
                    'owner': JSON.stringify(parsedOwner["username"])
                }
            });

            console.log(res);
            // Handle successful create listing
            if (res.status === 201) {
                console.log("Listing created")
                
            }
        } catch (error) {
            setShowError(true);
        }
        setAddress('')
        setCity('')
        setDescription('')
        setName('')
        setState('')
        setShowError(false)
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">Name: </label>< br/>
                <input
                    required
                    id="name"
                    type="text"
                    value={name}
                    placeholder="What would you like us to call your listing?"
                    onChange={({ target }) => setName(target.value)}
                />< br/>
                <label htmlFor="address">Address: </label>< br/>
                <input
                    required
                    id="username"
                    type="text"
                    value={address}
                    placeholder="Phoenix, AZ"
                    onChange={({ target }) => setAddress(target.value)}
                />< br/>
                <label htmlFor="city">City: </label>< br/>
                <input
                    required
                    id="city"
                    type="text"
                    value={city}
                    placeholder="Margaritaville"
                    onChange={({ target }) => setCity(target.value)}
                />< br/>
                <label htmlFor="state">State: </label>< br/>
                <input
                    required
                    id="state"
                    type="text"
                    value={state}
                    placeholder="WA, CA, etc."
                    onChange={({ target }) => setState(target.value)}
                />< br/>
                <label htmlFor="description">Description: </label>< br/>
                <input
                    required
                    id="description"
                    type="text"
                    value={description}
                    placeholder="Tell us about your listing"
                    onChange={({ target }) => setDescription(target.value)}
                />< br/>
                <button name="renter" type="submit">Create</button>
            </form>
            {showError ? <p className='error-message'>Something went wrong when creating new listing!</p> : null}
        </>
    )
}
  
export default NewListingForm