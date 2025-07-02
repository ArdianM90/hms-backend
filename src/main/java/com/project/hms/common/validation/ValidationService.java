package com.project.hms.common.validation;

import com.project.hms.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ValidationService {

    public boolean validateAuthData(final String clientId, final String apiKey) {
        if (StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(apiKey)) {
            return false;
        }
        final String clientSha = getShaByClient(clientId);
        if (clientSha.isEmpty()) {
            return false;
        }
        String requestSha = generateSha(clientId, apiKey);
        return clientSha.equals(requestSha);
    }

    private String getShaByClient(String clientId) {
        return generateSha(Constants.MOCK_CLIENT_ID, Constants.MOCK_API_KEY);
    }


    private String generateSha(String clientId, String apiKey) {
        final MessageDigest digest;
        final String requestAuthString = clientId + apiKey;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[ERROR] Error while creating message digest object.");
            return null;
        }
        final byte[] hashbytes = digest.digest(requestAuthString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
