package com.allencai.mycloud.seata.starter;

import io.seata.common.XID;
import io.seata.common.thread.NamedThreadFactory;
import io.seata.common.util.NetUtil;
import io.seata.core.rpc.netty.RpcServer;
import io.seata.core.rpc.netty.ShutdownHook;
import io.seata.server.UUIDGenerator;
import io.seata.server.coordinator.DefaultCoordinator;
import io.seata.server.metrics.MetricsManager;
import io.seata.server.session.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeataInitiator implements SmartLifecycle {

    private boolean running;
    private SeataInitiatorProperties properties;

    public SeataInitiator(SeataInitiatorProperties properties) {
        this.properties = properties;
    }

    @Override
    public void start() {
        new Thread(this::doStart).start();
    }

    private void doStart() {
        running = true;
        MetricsManager.get().init();

        ThreadPoolExecutor messageExecutor = new ThreadPoolExecutor(properties.getMinServerPoolSize(), properties.getMaxServerPoolSize(),
                properties.getKeepAliveTime(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(properties.getMaxTaskQueueSize()),
                new NamedThreadFactory("ServerHandlerThread", properties.getMaxServerPoolSize()),
                new ThreadPoolExecutor.CallerRunsPolicy());
        RpcServer rpcServer = new RpcServer(messageExecutor);
        rpcServer.setListenPort(properties.getPort());
        UUIDGenerator.init(properties.getNode());
        try {
            SessionHolder.init("");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(-1);
        }

        DefaultCoordinator coordinator = new DefaultCoordinator(rpcServer);
        coordinator.init();
        rpcServer.setHandler(coordinator);
        // register ShutdownHook
        ShutdownHook.getInstance().addDisposable(coordinator);

        //127.0.0.1 and 0.0.0.0 are not valid here.
        if (NetUtil.isValidIp(properties.getHost(), false)) {
            XID.setIpAddress(properties.getHost());
        } else {
            XID.setIpAddress(NetUtil.getLocalIp());
        }
        XID.setPort(rpcServer.getListenPort());

        try {
            rpcServer.init();
        } catch (Throwable e) {
            log.error("rpcServer init error:{}", e.getMessage(), e);
            System.exit(-1);
        }

        System.exit(0);
    }


    @Override
    public void stop() {
        ShutdownHook.getInstance().destroyAll();
        running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
    }

    @Override
    public int getPhase() {
        return 0;
    }

}
