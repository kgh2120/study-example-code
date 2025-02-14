package org.kk.cachesync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CacheEvictMessage {
    private String cacheName;
    private long key;
}
