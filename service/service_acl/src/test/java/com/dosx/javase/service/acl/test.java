package com.dosx.javase.service.acl;

import com.dosx.javase.common.utils.TokenUtil;

import org.apache.commons.codec.DecoderException;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class test
{
    @Test
    public void case3()
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, DecoderException, BadPaddingException, NoSuchProviderException, IllegalBlockSizeException
    {

        TokenUtil tokenUtil = TokenUtil.getInstance();
        String src = "here you are";
        String encoded = tokenUtil.encodeToken(src);
        String decoded = tokenUtil.decodeToken(encoded);

        System.out.println("encoded: " + encoded);
        System.out.println("decoded: " + decoded);
    }
}
