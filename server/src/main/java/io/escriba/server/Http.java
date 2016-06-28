package io.escriba.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.*;

public class Http {
	public static HttpResponse chunked(HttpResponse response) {
		response.headers().set(HttpHeaderNames.TRANSFER_ENCODING, "chunked");
		return response;
	}

	public static HttpResponse created(String message) {
		return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(CREATED.code(), message));
	}

	public static HttpResponse noContent(String message) {
		return new DefaultHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(NO_CONTENT.code(), message));
	}

	public static HttpResponse ok(String contenType) {
		DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, OK);
		response.headers().set(CONTENT_TYPE, contenType);
		return response;
	}

	public static void response(ChannelHandlerContext ctx, HttpResponse httpResponse) {
		ctx.writeAndFlush(httpResponse);
	}

	public static void responseAndClose(ChannelHandlerContext ctx, HttpResponse httpResponse) {
		ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
	}
}
