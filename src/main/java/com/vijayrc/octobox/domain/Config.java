package com.vijayrc.octobox.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("#{configBean['db.dir']}")
    private String dataDir;

    public String dataDir() {
        return dataDir;
    }
}
