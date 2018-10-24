package com.github.sejoung.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TestDto {

    @JsonAlias({ "PCODE", "pCode" })
    private String pcode;

    @JsonAlias({ "adGubun", "gb" })
    private String adGubun;

    @JsonProperty("product")
    private String advrtsPrdtCode;

    @JsonProperty("platform")
    private String pltfomTpCode;

    @JsonAlias({ "userid", "u" })
    private String adverId;

    @JsonProperty("viewcnt1")
    private String totEprsCnt;

    @JsonProperty("viewcnt3")
    private String parEprsCnt;

    @JsonProperty("dumpType")
    private String dumpType;

    @JsonProperty("yyyymmdd")
    private String statsDttm;

    @JsonProperty("className")
    private String className;

    @JsonProperty("frameId")
    private String frameId;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age")
    private String age;

    @JsonProperty("type")
    private String type;
}
