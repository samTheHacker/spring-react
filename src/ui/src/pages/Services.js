import React, { useLayoutEffect, useState } from "react";
import { getCurrency } from "../Utils";

const Services = ()=> {


    const [services, setServices] = useState([])

    useLayoutEffect( ()=> {
        const getServices = async() => {
            const res = await fetch('/api/services');
            const services = await res.json();
            setServices(services);
        }

        getServices().catch(e => {
            console.log('error fetching services: ' + e);
        })
    })
    return(
        <table>
            <thead>
                <th>ID</th>
                <th>Service Name</th>
                <th>Price</th>
            </thead>
            <tbody>
                {services.map(service => {
                    const { serviceId, 
                            serviceName, 
                            price } = service;
                 return(
                    <tr key={serviceId}>
                        <td>{serviceId}</td>
                        <td>{serviceName}</td>
                        <td>{getCurrency(price)}</td>
                    </tr>
                 );
                 })}
            </tbody>
        </table>
    )


}

export default Services;