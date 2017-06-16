package com.zuehlke.neverforget.config;

import com.zuehlke.neverforget.domain.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null)
            return "";
        return ((User) authentication.getPrincipal()).getUsername();
    }
}
