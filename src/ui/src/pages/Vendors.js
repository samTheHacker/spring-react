import React, { useLayoutEffect, useState } from "react";

const Vendors = () => {

    const [vendors, setVendors] = useState([])

    useLayoutEffect( ()=> {
        const getVendors = async() => {
            const res = await fetch('/api/vendors');
            const vendors = await res.json();
            setVendors(vendors);
        }

        getVendors().catch(e => {
            console.log('error fetching vendors: ' + e);
        })
    })
    return(
        <table>
            <thead>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Address</th>
                <th>Phone Number</th>
                <th>Address</th>
            </thead>
            <tbody>
                {vendors.map(vendor => {
                    const { vendorId, 
                            vendorName, 
                            contact, 
                            phoneNumber, 
                            email, 
                            address} = vendor;
                 return(
                    <tr key={vendorId}>
                        <td>{vendorId}</td>
                        <td>{vendorName}</td>
                        <td>{contact}</td>
                        <td>{phoneNumber}</td>
                        <td>{email}</td>
                        <td>{address}</td>
                    </tr>
                 );
                 })}
            </tbody>
        </table>
    )
}

export default Vendors;