import { useEffect, useState } from "react";

/* returns null OR returns an array of objects -> [{}, {}, {}...] */
export default function useFetch(uri : string): null | [] {
  /* data: local state variable to house the fetched data */
  /* setData: mutative function provided by React to set data's value */
  const [data, setData] = useState(null)

  /* useEffect is a hook that runs after the first render of the component */
  useEffect(() => {
    let ignore = false; /* ignore is a flag to prevent setting state after the component has unmounted */
    if (!uri) return /* if there is no uri, return */
    setData(null) /* set data to null to indicate that the fetch is in progress */
    fetch(uri)
      .then((result) => result.json())
      .then((data) => {
        if (!ignore) setData(data)
      })
      .catch((e) => console.error(e)) /* if there is an error, log it to the dev console */
    /* cleanup function to prevent setting state after the component has unmounted */
    return () => {
      ignore = true; /* set ignore to true to prevent setting state after the component has unmounted */
    };
  }, [uri]) /* useEffect will run every time the uri changes */
  
  return data
}