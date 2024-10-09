package eoes.gridplay.net;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class UDPServer {
    private final int port;

    public UDPServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
    	NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioDatagramChannel.class)
             .handler(new ChannelInitializer<DatagramChannel>() {
                 @Override
                 protected void initChannel(DatagramChannel ch) throws Exception {
                     ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new UDPServerHandler());
                 }
             });

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }
}

