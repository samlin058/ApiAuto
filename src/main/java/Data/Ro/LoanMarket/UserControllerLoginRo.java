package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.UserController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import lombok.Data;

import java.util.Date;


@lombok.Data
public class UserControllerLoginRo extends UserController {

    @Body
    public String verifyCode = "888888";//测试环境默认校验码为888888
    @Body
    public String mobile;
    @Body
    public String channel;

    static {
        DenpendencesProcessor.roDendenpence(UserControllerLoginRo.class);
    }

    public UserControllerLoginRo(String phone, String channelCode) {
        this.mobile = phone;
        this.channel = channelCode;
    }

    public UserControllerLoginRo(String phone, String channelCode, String verifyCode) {
        this.mobile = phone;
        this.channel = channelCode;
        this.verifyCode = verifyCode;
    }

    @Data
    public class Response {
        public Integer code;
        public String message;
        public UserVo data;
        public String error;
    }

    @Data
    public class UserVo {
        private Long id;

        private String mobile;
        /**
         * 头像
         * Note: 头像字段存储的是对应照片在文件服务中的唯一文件名
         * Note: 关于文件服务可查看 http://git.caimi-inc.com/torch/ezalor
         */
        private String avatar;
        /**
         * 用户开放ID
         * Note: 该ID用于在传递给三方时唯一辨识内部用户. (禁止向三方传递内部 id)
         * Note: 该字段必须保证用户唯一
         */
        private String openid;

        /**
         * 用户注册平台
         */
        private String platform;

        /**
         * 状态
         * Note: 该字段主要描述账号是否可用, 有别与注销
         */
        private String status;

        private Date createdTime;
        /**
         * 注册类型
         * Note：该字段主要描述用户的注册类型
         *
         * @See
         */
        private Boolean isRegister;
    }
}


