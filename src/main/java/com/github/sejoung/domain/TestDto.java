package com.github.sejoung.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TestDto {

    @JsonProperty("NO")
    private long no;
    
    @JsonProperty("PCODE")
    private String pcode;

    @JsonProperty("adGubun")
    private String adGubun;
    

}
