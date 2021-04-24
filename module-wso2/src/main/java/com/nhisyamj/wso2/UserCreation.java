package com.nhisyamj.wso2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonTypeName(value = "user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class UserCreation {

    private String userName;
    private String realm;
    private String password;
    private List<Claim> claimList = new ArrayList<>();

    public UserCreation addClaim(Claim claim) {
        if (claim!=null) {
            this.claimList.add(claim);
        }
        return this;
    }
}
