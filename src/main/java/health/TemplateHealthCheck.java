package net.oebs.namo.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    public TemplateHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        if (false) {
            return Result.unhealthy("false is true!");
        }
        return Result.healthy();
    }
}