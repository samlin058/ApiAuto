package Service.Pojo;

import lombok.Data;

@Data
public class UserJointLoginConfigDTO {
    /**
     * 分配给外部引流渠道的公钥
     */
    private String publicKey;
    /**
     * 挖财解密外部引流渠道数据的私钥
     */
    private String privateKey;
    /**
     * 系统内部流量渠道code（满足外部引流渠道可能为自营渠道，也可能不是自营需求）
     */
    private String channelCode;
    private String platform;

    /**
     * 联登方式
     * JWT/RSA
     */
    private String jointLoginMethod;

    /**
     * JWT密钥
     */
    private String jwtSecret;
}
