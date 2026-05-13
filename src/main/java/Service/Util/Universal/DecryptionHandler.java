package Service.Util.Universal;

import Service.Pojo.UserJointLoginConfigDTO;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class DecryptionHandler {

    public static String generateJointLoginToken(String mobile, UserJointLoginConfigDTO configDTO) {
        // 构建原始token字符串，格式为：key1=value1&key2=value2
        long currentTimeMillis = System.currentTimeMillis();
        // token 有效期设置为1小时
        long expireAt = currentTimeMillis + 3600000;
        String originalToken = "channelCode=ZY_FL&expireAt=" + expireAt + "&mobile=" + mobile + "&ts=1739760521841";
        // 使用RSA加密
        RSA rsa = new RSA(configDTO.getPrivateKey(), configDTO.getPublicKey());
        // Base64编码
        return Base64Encoder.encode(rsa.encrypt(originalToken.getBytes(), KeyType.PublicKey));
    }

    public static String getToken(String mobile) {
        UserJointLoginConfigDTO configDTO = new UserJointLoginConfigDTO();
        configDTO.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC17kXg8RkAH4aCDjjZFWqJNwuVbPTlzW2Jd9IZZu1GzHsjEdFJeHN7DmkxRIZTeAQl+a6D32rHtdLFcGdRUHz+gU4bLs0bGC/Yog0RaLT3r0qpY/5KQEVrQ6v1d6gkIFN485kfH3QBUggb0ftZIIbNf2Yt9ZBxXmL++yo418RBiwIDAQAB");
        configDTO.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXuReDxGQAfhoIOONkVaok3C5Vs9OXNbYl30hlm7UbMeyMR0Ul4c3sOaTFEhlN4BCX5roPfase10sVwZ1FQfP6BThsuzRsYL9iiDRFotPevSqlj/kpARWtDq/V3qCQgU3jzmR8fdAFSCBvR+1kghs1/Zi31kHFeYv77KjjXxEGLAgMBAAECgYBRKzsbFoZQc0BIUz8Sv4bWLGxrIP8MbkBiNkbTFYRkc4O2zFLDRE3Fh9Lv68JAiEnVhZXSeUIDU0Bz3WgDM/Vntwohrcb1r8q/bXstjt1KwjkwW0lk9DgdiZfQAQwEZeAuqpPXpOCJBfzsQjO2VaCov0chw7lAEA8thiNDwa4awQJBAPbXYx50TI4nf4gt+nQ2Hrmy2raqB6mXUbyhu1WLaJDy7eowu9pLnB+UUeLEq9phcqGi56u1MpDtQbLReVnCpssCQQC8rlXCp9znhsSa6iJVCXJmXadwxETH4lcY4MHA9qXN28TYeFVlPowKKBan5dARotzsD6w6PKQTmcncdiqrorhBAkEAoCkS1CDLx3TTZ+3FUwAGoaDuDpV2UVCwpT8fSRsiZuJpAopKaRKkI01ecFAB3bla4ovL2/q+65A2D6uhHF+TTwJAIjp6Ilrzbyj6p6PL4KNbFojs5D8mvyAnbsLvQ0M7ZvH0btS9oSVMoFcPbRv6uAhUjZp2QwZXOZsPjKf/K63UQQJAQKsDW+0peKcbIQs0cTptaFkDle2l5c1Ljdv4pHawUbCbUDf8ze6HahErVsl7n2ylu8TCkppXy3n/OG7qjoe+4A==");
        return generateJointLoginToken(mobile, configDTO);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        UserJointLoginConfigDTO configDTO = new UserJointLoginConfigDTO();
        configDTO.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC17kXg8RkAH4aCDjjZFWqJNwuVbPTlzW2Jd9IZZu1GzHsjEdFJeHN7DmkxRIZTeAQl+a6D32rHtdLFcGdRUHz+gU4bLs0bGC/Yog0RaLT3r0qpY/5KQEVrQ6v1d6gkIFN485kfH3QBUggb0ftZIIbNf2Yt9ZBxXmL++yo418RBiwIDAQAB");
        configDTO.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXuReDxGQAfhoIOONkVaok3C5Vs9OXNbYl30hlm7UbMeyMR0Ul4c3sOaTFEhlN4BCX5roPfase10sVwZ1FQfP6BThsuzRsYL9iiDRFotPevSqlj/kpARWtDq/V3qCQgU3jzmR8fdAFSCBvR+1kghs1/Zi31kHFeYv77KjjXxEGLAgMBAAECgYBRKzsbFoZQc0BIUz8Sv4bWLGxrIP8MbkBiNkbTFYRkc4O2zFLDRE3Fh9Lv68JAiEnVhZXSeUIDU0Bz3WgDM/Vntwohrcb1r8q/bXstjt1KwjkwW0lk9DgdiZfQAQwEZeAuqpPXpOCJBfzsQjO2VaCov0chw7lAEA8thiNDwa4awQJBAPbXYx50TI4nf4gt+nQ2Hrmy2raqB6mXUbyhu1WLaJDy7eowu9pLnB+UUeLEq9phcqGi56u1MpDtQbLReVnCpssCQQC8rlXCp9znhsSa6iJVCXJmXadwxETH4lcY4MHA9qXN28TYeFVlPowKKBan5dARotzsD6w6PKQTmcncdiqrorhBAkEAoCkS1CDLx3TTZ+3FUwAGoaDuDpV2UVCwpT8fSRsiZuJpAopKaRKkI01ecFAB3bla4ovL2/q+65A2D6uhHF+TTwJAIjp6Ilrzbyj6p6PL4KNbFojs5D8mvyAnbsLvQ0M7ZvH0btS9oSVMoFcPbRv6uAhUjZp2QwZXOZsPjKf/K63UQQJAQKsDW+0peKcbIQs0cTptaFkDle2l5c1Ljdv4pHawUbCbUDf8ze6HahErVsl7n2ylu8TCkppXy3n/OG7qjoe+4A==");
        String mobile = "13420250311";
        String token = generateJointLoginToken(mobile, configDTO);
        // System.out.println("生成的token: " + token);
        System.out.println(URLEncoder.encode(token, "UTF-8"));
    }

}
