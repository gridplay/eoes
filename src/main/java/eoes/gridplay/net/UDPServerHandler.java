package eoes.gridplay.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> implements ChannelHandler {
	
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("Received UDP: " + msg);

        // Process the message and prepare a response
        String response = msg;

        // Send the response back to the client
        DatagramPacket responsePacket = new DatagramPacket(
                Unpooled.copiedBuffer(response, CharsetUtil.UTF_8), packet.sender());
        ctx.writeAndFlush(responsePacket);
    }
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub

	}

}
