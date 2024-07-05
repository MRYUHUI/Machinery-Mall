package com.mall.mechmall.controller;

import com.alipay.easysdk.factory.Factory;
import com.mall.mechmall.config.AliPayConfig;
import com.mall.mechmall.domain.AliPay;
import com.mall.mechmall.domain.Order;
import com.mall.mechmall.service.OrderService;
import com.mall.mechmall.utils.DateUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("alipay")
@Transactional(rollbackFor = Exception.class)
public class AliPayController {

    @Resource
    AliPayConfig aliPayConfig;

    @Resource
    private OrderService orderService;
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "utf-8";
    private static final String SIGN_TYPE = "RSA2";

    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setBizContent("{\"out_trade_no\":\"" + aliPay.getTraceNo() + "\","
                + "\"total_amount\":\"" + aliPay.getTotalAmount() + "\","
                + "\"subject\":\"" + aliPay.getSubject() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        // 获取支付宝异步通知参数
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }

        // 验证支付宝异步通知的签名
        if (Factory.Payment.Common().verifyNotify(params)) {
            // 验签通过，处理订单支付成功的逻辑
            if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
                String tradeNo = params.get("out_trade_no"); // 订单编号，trade_no是支付宝的编号
                // 更新订单状态为已支付
                Order order = new Order();
                order.setOrderNo(tradeNo);
                order.setStatus(2); // 2 表示已支付
                order.setPaymentTime(DateUtils.getCurrentDate());
                orderService.updateByOrderNo(order);

                // 输出支付信息
                System.out.println("=========支付宝异步回调========");
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
            }
        }

        // 返回给支付宝一个成功的响应，告知支付宝已经成功接收到通知
        response.getWriter().write("success");

        // 构造重定向到前端页面的逻辑
        String returnUrl = "http://localhost:8081/order-user"; // 替换为你的前端页面地址
        response.sendRedirect(returnUrl);
    }

}
