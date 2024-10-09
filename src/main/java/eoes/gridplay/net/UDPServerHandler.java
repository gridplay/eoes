package eoes.gridplay.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final int MAX_PACKET_SIZE = 1024; // Set a maximum packet size

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        if (packet.content().readableBytes() > MAX_PACKET_SIZE) {
            System.out.println("Packet too large, discarding.");
            return;
        }

        String msg = packet.content().toString(CharsetUtil.UTF_8);
        if (!isValidMessage(msg)) {
            System.out.println("Invalid message, discarding.");
            return;
        }

        if (msg.equals("Test")) {
        	System.out.println("Connected i hope");
            // Send confirmation message back to the client
            String confirmation = "Pong";
            DatagramPacket confirmationPacket = new DatagramPacket(
                    Unpooled.copiedBuffer(confirmation, CharsetUtil.UTF_8),
                    packet.sender()
            );
            ctx.writeAndFlush(confirmationPacket);
        }
    }

    private boolean isValidMessage(String msg) {
        // Implement your validation logic here
        return msg != null && !msg.trim().isEmpty();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

