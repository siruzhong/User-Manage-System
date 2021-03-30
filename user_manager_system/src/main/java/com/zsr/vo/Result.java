package com.zsr.vo;

import ch.qos.logback.classic.turbo.TurboFilter;
import lombok.Data;

@Data
public class Result {
    public boolean status = true;
    private String msg;
}
