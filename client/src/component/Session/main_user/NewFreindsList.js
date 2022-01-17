import React, {useLayoutEffect} from "react";

const NewFreindsList = (params) => {


    return (
        <div>
            {
                params.map(friend=>(

                    <ul>{friend}</ul>
                ))
            }
        </div>
    );
}

export default NewFreindsList; 