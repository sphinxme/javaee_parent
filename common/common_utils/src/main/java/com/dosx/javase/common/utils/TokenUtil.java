package com.dosx.javase.common.utils;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author lucky us
 */
public class TokenUtil
{
    private static TokenUtil tokenUtil = null;

    private static final String HEX_KEY = "0477d707890e73588dce6731365d5d56";
    private final Key KEY;
    private final Cipher cipher;

    public static TokenUtil getInstance()
            throws
            InvalidKeyException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            DecoderException
    {
        if (tokenUtil == null) {
            tokenUtil = new TokenUtil();
        }
        return tokenUtil;
    }

    private TokenUtil()
            throws
            DecoderException,
            NoSuchPaddingException,
            NoSuchAlgorithmException
    {
        KEY = new SecretKeySpec(Hex.decodeHex(HEX_KEY), "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5padding");
    }

    /**
     * makeToken
     * 给个值 给加密成token并返回
     * */
    public String encodeToken(String raw)
            throws
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException
    {
        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, KEY);
        byte[] rawResult = cipher.doFinal(raw.getBytes());

        // 十六进制编码
        return Hex.encodeHexString(rawResult);
    }

    public String decodeToken(String token)
            throws
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException,
            DecoderException
    {
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, KEY);
        byte[] rawToken = Hex.decodeHex(token);
        return new String(cipher.doFinal(rawToken));
    }
}
