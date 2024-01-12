package com.samdabbous.springreact.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {

    public Long vendorId;
    public String vendorName;
    public String contact;
    public String phoneNumber;
    public String email;
    public String address;

}
