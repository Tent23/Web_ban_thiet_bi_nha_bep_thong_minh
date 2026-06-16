package com.webthietbibep.dao;

import com.webthietbibep.model.UserKey;

import java.time.LocalDateTime;

public class UserKeyDAO extends BaseDao {
    public void insert(UserKey key){
        get().useHandle(h ->
                h.createUpdate("""
            INSERT INTO user_keys
            (key_id,user_id,public_key,created_at)
            VALUES
            (:kid,:uid,:pub,:created)
        """)
                        .bind("kid", key.getKeyId())
                        .bind("uid", key.getUserId())
                        .bind("pub", key.getPublicKey())
                        .bind("created", key.getCreatedAt())
                        .execute()
        );
    }
    public UserKey findByKeyId(String keyId){
        return get().withHandle(h ->
                h.createQuery("""
            SELECT *
            FROM user_keys
            WHERE key_id = :kid
        """)
                        .bind("kid", keyId)
                        .mapToBean(UserKey.class)
                        .findOne()
                        .orElse(null)
        );
    }
    public void revokeKey(String keyId, LocalDateTime revokedAt){
        get().useHandle(h ->
                h.createUpdate("""
            UPDATE user_keys
            SET revoked_at = :revoked
            WHERE key_id = :kid
        """)
                        .bind("revoked", revokedAt)
                        .bind("kid", keyId)
                        .execute()
        );
    }
}