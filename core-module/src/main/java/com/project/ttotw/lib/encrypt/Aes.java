package com.project.ttotw.lib.encrypt;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class Aes implements Encrypt {

  private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
  private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";
  private static final String KEY_TYPE = "AES";
  private final SecretKeySpec secretKeySpec;
  private final IvParameterSpec ivParameterSpec;
  private final Cipher cipher;

  public Aes(final String key, final String iv) {
    keyValidate(key);
    ivValidate(iv);

    byte[] keyBytes = key.getBytes(ENCODING_TYPE);
    secretKeySpec = new SecretKeySpec(keyBytes, KEY_TYPE);
    byte[] ivBytes = iv.getBytes(ENCODING_TYPE);
    ivParameterSpec = new IvParameterSpec(ivBytes);

    try {
      cipher = Cipher.getInstance(INSTANCE_TYPE);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("failed.init.encrypt");
    }
  }

  private void keyValidate(final String key) {
    Optional.ofNullable(key)
        .filter(StringUtils::hasText)
        .filter(s -> s.length() == 16 || s.length() == 24 || s.length() == 32)
        .orElseThrow(() -> new IllegalArgumentException("failed.init.encrypt"));
  }

  private void ivValidate(final String iv) {
    Optional.ofNullable(iv)
        .filter(StringUtils::hasText)
        .filter(s -> s.length() == 16)
        .orElseThrow(() -> new IllegalArgumentException("failed.init.encrypt"));
  }

  /**
   * AES 암호화
   *
   * @param toEncryptText 암호화 할 평문
   *
   * @return 암호화된 문자열
   */
  @Override
  public String encrypt(String toEncryptText) {
    try {
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] encrypted = cipher.doFinal(toEncryptText.getBytes(ENCODING_TYPE));
      return new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
    } catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException |
             InvalidKeyException e) {
      log.error("AES:: encrypt error.");
      e.printStackTrace();
      throw new IllegalArgumentException("failed.encrypt");
    }

  }

  /**
   * AES 복호화
   *
   * @param toDecryptText 암호화 된 문자열
   *
   * @return 복호화된 평문
   */
  @Override
  public String decrypt(String toDecryptText) {
    try {
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] decoded = Base64.getDecoder().decode(toDecryptText.getBytes(ENCODING_TYPE));
      return new String(cipher.doFinal(decoded), ENCODING_TYPE);

    } catch (InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException |
             BadPaddingException e) {
      log.error("AES:: decrypt error.");
      e.printStackTrace();
      throw new IllegalArgumentException("failed.decrypt");
    }
  }
}
