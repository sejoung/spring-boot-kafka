package com.github.sejoung.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TestDto {

    @JsonProperty("PCODE")
    private String pcode;

    @JsonProperty("adGubun")
    private String adGubun;

    @JsonProperty("product")
    private String advrtsPrdtCode;

    @JsonProperty("platform")
    private String pltfomTpCode;

    @JsonProperty("userid")
    private String adverId;

    @JsonProperty("viewcnt1")
    private String totEprsCnt;

    @JsonProperty("viewcnt3")
    private String parEprsCnt;

    @JsonProperty("dumpType")
    private String dumpType;
    
    @JsonProperty("yyyymmdd")
    private String statsDttm;
}
