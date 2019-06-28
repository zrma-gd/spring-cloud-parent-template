package org.spring.cloud.zuul.route.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ZuulRefreshListener implements ApplicationListener<ApplicationEvent> {

	@Autowired
    private ZuulHandlerMapping zuulHandlerMapping;

    private HeartbeatMonitor heartbeatMonitor = new HeartbeatMonitor();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent
                || event instanceof RefreshScopeRefreshedEvent
                || event instanceof RoutesRefreshedEvent) {
            //设置为脏,下一次匹配到路径时，如果发现为脏，则会去刷新路由信息
            this.zuulHandlerMapping.setDirty(true);
        }else if (event instanceof HeartbeatEvent) {
            if (this.heartbeatMonitor.update(((HeartbeatEvent) event).getValue())) {
                this.zuulHandlerMapping.setDirty(true);
            }
        }
    }
}
