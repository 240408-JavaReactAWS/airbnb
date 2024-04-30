import axios from "axios";

export const validateLogin = {
    validateSession: async () => {

        let role
        if (!localStorage.hasOwnProperty("role")) {
           console.log("user not logged in")
           return false;
        }

        role = localStorage.getItem("role")
        try {
            let res = await axios.get(`http://localhost:8080/${role}s/session`, {
                withCredentials: true
            });
            
            if (res.status === 200) {
                console.log("Session is valid");
                return true;
            } else{
                console.log("Session is invalid");
                return false;
            }
        } catch (error) {
            console.log("Session is invalid");
            return false;
        }
    },
}