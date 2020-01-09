package com.eshipper.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.eshipper.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.eshipper.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.eshipper.domain.User.class.getName());
            createCache(cm, com.eshipper.domain.Authority.class.getName());
            createCache(cm, com.eshipper.domain.User.class.getName() + ".authorities");
            createCache(cm, com.eshipper.domain.Region.class.getName());
            createCache(cm, com.eshipper.domain.Country.class.getName());
            createCache(cm, com.eshipper.domain.Location.class.getName());
            createCache(cm, com.eshipper.domain.Department.class.getName());
            createCache(cm, com.eshipper.domain.Department.class.getName() + ".employees");
            createCache(cm, com.eshipper.domain.Task.class.getName());
            createCache(cm, com.eshipper.domain.Task.class.getName() + ".jobs");
            createCache(cm, com.eshipper.domain.Employee.class.getName());
            createCache(cm, com.eshipper.domain.Employee.class.getName() + ".jobs");
            createCache(cm, com.eshipper.domain.Job.class.getName());
            createCache(cm, com.eshipper.domain.Job.class.getName() + ".tasks");
            createCache(cm, com.eshipper.domain.JobHistory.class.getName());
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName());
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".shippingOrders");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".ticketReasons");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".claimStatuses");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".claimSolutions");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".claimAssignees");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".claimComments");
            createCache(cm, com.eshipper.domain.ShippingClaim.class.getName() + ".contactPreferences");
            createCache(cm, com.eshipper.domain.ClaimStatus.class.getName());
            createCache(cm, com.eshipper.domain.ContactPreference.class.getName());
            createCache(cm, com.eshipper.domain.TicketReason.class.getName());
            createCache(cm, com.eshipper.domain.ShippingOrder.class.getName());
            createCache(cm, com.eshipper.domain.ClaimAttachment.class.getName());
            createCache(cm, com.eshipper.domain.ClaimAttachment.class.getName() + ".shippingClaims");
            createCache(cm, com.eshipper.domain.ClaimMissingDocument.class.getName());
            createCache(cm, com.eshipper.domain.ClaimMissingDocument.class.getName() + ".claimDocumentTypes");
            createCache(cm, com.eshipper.domain.ClaimMissingDocument.class.getName() + ".shippingClaims");
            createCache(cm, com.eshipper.domain.ClaimDocumentType.class.getName());
            createCache(cm, com.eshipper.domain.ClaimSolution.class.getName());
            createCache(cm, com.eshipper.domain.ClaimAssignee.class.getName());
            createCache(cm, com.eshipper.domain.ClaimComment.class.getName());
            createCache(cm, com.eshipper.domain.ClaimComment.class.getName() + ".users");
            createCache(cm, com.eshipper.domain.Currency.class.getName());
            createCache(cm, com.eshipper.domain.ClaimCarrierRefund.class.getName());
            createCache(cm, com.eshipper.domain.ClaimCarrierRefund.class.getName() + ".currencies");
            createCache(cm, com.eshipper.domain.ClaimCarrierRefund.class.getName() + ".refundStatuses");
            createCache(cm, com.eshipper.domain.ClaimCarrierRefundStatus.class.getName());
            createCache(cm, com.eshipper.domain.ClaimEshipperRefund.class.getName());
            createCache(cm, com.eshipper.domain.ClaimEshipperRefund.class.getName() + ".currencies");
            createCache(cm, com.eshipper.domain.ElasticShippingClaim.class.getName());
            createCache(cm, com.eshipper.domain.ElasticShippingClaim.class.getName() + ".elasticStatuses");
            createCache(cm, com.eshipper.domain.ElasticsearchStatus.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
