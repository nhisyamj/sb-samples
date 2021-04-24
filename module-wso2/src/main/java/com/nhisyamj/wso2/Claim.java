package com.nhisyamj.wso2;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@XmlType(name ="", propOrder = {
        "claimURI",
        "value"
})
public class Claim {

    private String claimURI;
    private String value;

    public Claim(String claimURI, String value) {
        this.claimURI = claimURI;
        this.value = value;
    }
}
