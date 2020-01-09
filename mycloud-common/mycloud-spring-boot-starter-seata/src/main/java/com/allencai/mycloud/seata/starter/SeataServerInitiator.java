package com.allencai.mycloud.seata.starter;

import com.allencai.mycloud.seata.starter.config.SeataServer;
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
public class SeataServerInitiator implements SmartLifecycle {

    private boolean running;
    private SeataServer seataServer;

    public SeataServerInitiator(SeataServer seataServer) {
        this.seataServer = seataServer;
    }

    @Override
    public void start() {
        new Thread(this::doStart).start();
    }

    private void doStart() {
        running = true;
        MetricsManager.get().init();

        ThreadPoolExecutor messageExecutor = new ThreadPoolExecutor(seataServer.getMinServerPoolSize(), seataServer.getMaxServerPoolSize(),
                seataServer.getKeepAliveTime(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(seataServer.getMaxTaskQueueSize()),
                new NamedThreadFactory("ServerHandlerThread", seataServer.getMaxServerPoolSize()),
                new ThreadPoolExecutor.CallerRunsPolicy());
        RpcServer rpcServer = new RpcServer(messageExecutor);
        rpcServer.setListenPort(seataServer.getPort());
        UUIDGenerator.init(seataServer.getNode());
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
        if (NetUtil.isValidIp(seataServer.getHost(), false)) {
            XID.setIpAddress(seataServer.getHost());
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
