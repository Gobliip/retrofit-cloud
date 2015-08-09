package com.gobliip.retrofit.cloud.mime;

import org.apache.commons.codec.digest.DigestUtils;
import retrofit.mime.TypedByteArray;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lsamayoa on 9/08/15.
 */
public class SHATypedByteArray extends TypedByteArray {
    private final String filename;

    public SHATypedByteArray(String mimeType, byte[] bytes) {
        super(mimeType, bytes);
        filename = DigestUtils.sha256Hex(bytes);
    }

    @Override
    public String fileName() {
        return filename;
    }
}
